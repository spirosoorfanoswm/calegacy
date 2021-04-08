package eu.ark.creditark.services.creditarkservices.services.optimizer;

import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import eu.ark.creditark.services.creditarkservices.optimizer.LimitsOptimizer;
import eu.ark.creditark.services.creditarkservices.shared.CustomerInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OptimizerServiceImpl implements OptimizerService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void calcPortfolio(LimitsOptimizer limitsOptimizer) throws ScenarioException, CloneNotSupportedException, InterruptedException {
        logger.info("LimitsOptimizer start : {}", limitsOptimizer.getScenarioInfo().customersInfo.length);
        // Get portfolio's risk moments
        double portfolioUl2 = 0;
        double portfolioUl;
        int countL1 = 0;
        int countL2 = 0;
        int countL3 = 0;
        // Check validity and calculate risk moments
        //List<SeverityCallable> severityCallables = new ArrayList<>();
        for (int i = 0; i < limitsOptimizer.getScenarioInfo().customersInfo.length; i++) {
            CustomerInfo cust = limitsOptimizer.getScenarioInfo().customersInfo[i];
            //logger.info("Optimize : calculate risk moments on {}", cust);
            String id;
            if (cust.customerId == null)
                id = "Prospect:" + Integer.toString(cust.prospectId);
            else
                id = "Customer:" + cust.customerId;

            for (int j = 0; j < cust.creditMitigants.length; j++) {
                if (cust.creditMitigants[j] < 0) {
                    logger.info("Mitigant cannot have a negative value {} {} {} ", id, cust.creditMitigants[j], cust);
                    throw new ScenarioException(id + ". Mitigant cannot have a negative value.");
                }

            }
            //severityCallables.add(new SeverityCallable(cust, limitsOptimizer));
            // Calculate risk moments
            double severity = cust.meanBalance * getWeightedLgd(cust.meanBalance, cust.creditMitigants, limitsOptimizer);
            portfolioUl2 += cust.num * cust.pd * severity * severity;
            countL1++;
        }

        logger.info("Finish creditMitigants check ");
        //SeverityExecutor severityExecutor = new SeverityExecutor();
        //portfolioUl2 = severityExecutor.accept(severityCallables);
        portfolioUl = Math.sqrt(portfolioUl2);

        //CustomerInfoExecutor customerInfoExecutor = new CustomerInfoExecutor();
       // List<CustomerInfoCallable> customerInfoCallables = new ArrayList<>();
        for (int i = 0; i < limitsOptimizer.getScenarioInfo().customersInfo.length; i++) {

            try {
                CustomerInfo customer = limitsOptimizer.getScenarioInfo().customersInfo[i];

                // Set initial values
                if (customer.meanBalance < 0) customer.meanBalance = 0;
                if (customer.projTurnover < 0) customer.projTurnover = 0;
                if (customer.pd <= customer.worstAcceptedPd && customer.projTurnover > 0) {
                    customer.maxRestrLimit = customer.maxAcceptedDso * customer.maxPurchases * 12 / limitsOptimizer.getPar().turnoverDays;
                    if (customer.creditLimit > 0) {
                        if (customer.customerId != null) { // do not apply to prospects
                            customer.maxRestrLimit = Math.min(customer.maxRestrLimit, customer.creditLimit * (1 + customer.maxLimitGrowth));
                            customer.minRestrLimit = Math.min(customer.maxRestrLimit, customer.creditLimit * (1 - customer.maxLimitReduction));
                        }
                        if (customer.minAcceptedLimitUse > 0) {
                            customer.maxRestrLimit = Math.min(customer.maxRestrLimit, customer.maxBalance / (customer.minAcceptedLimitUse));

                        }
                    }

                    if (customer.maxRestrLimit <= customer.minRestrLimit) {
                        customer.maxCalcLimit = customer.minRestrLimit;
                        customer.proposedLimit = customer.minRestrLimit;
                    }
                    else {
                        // Get the maximum accepted limit so that it does not exceed the MinAcceptedRwMargin
                        double d = customer.profitMargin - customer.minAcceptedRwMargin;
                        if (d <= 0 || customer.projTurnover == 0) {
                            customer.maxCalcLimit = customer.minRestrLimit;
                            customer.maxRestrLimit = customer.minRestrLimit;
                        }
                        else {

                            CustomerInfo cust = customer.getCopy();
                            // Set initial value for MaxCalcLimit
                            double annualTurnover = cust.projTurnover * 360 / limitsOptimizer.getPar().turnoverDays;
                            double lo = 0;
                            cust.pd = cust.pd<0?0.001:cust.pd;
                            double hi = (annualTurnover * d) / (cust.pd * limitsOptimizer.getPar().mitLgds[0]); // Rough initial max

                            // Compute the maximum limit
                            while (hi >= lo + Math.max(0.5 * limitsOptimizer.getScenarioInfo().minLimit, 100)) {
                                cust.proposedLimit = (hi + lo) * 0.5;
                                CalcCustomer(cust, portfolioUl, limitsOptimizer.getPar().varMultiplier, limitsOptimizer);
                                if (cust.rwMargin < cust.minAcceptedRwMargin)
                                    hi = cust.proposedLimit;
                                else
                                    lo = cust.proposedLimit;
                            }
                            customer.maxCalcLimit = cust.proposedLimit;
                            cust = null;
                        }
                    }
                }
                else {
                    customer.minRestrLimit = 0;
                    customer.maxRestrLimit = 0;
                    customer.proposedLimit = 0;
                }

                countL2++;
            } catch (Exception e) {
                logger.error("limitsOptimizer.getScenarioInfo", e);
            }

        }

        logger.info("Finish new customer check check ");
        //List<CustomerInfo> custs = customerInfoExecutor.accept(customerInfoCallables);


        //limitsOptimizer.getScenarioInfo().customersInfo = custs.toArray(new CustomerInfo[custs.size()]);

        // Main loop
        double loFactor = 0;
        double hiFactor = 1;
        double proposedLimit;

        do {
            proposedLimit = 0;
            double curFactor = (loFactor + hiFactor) * 0.5;

            for (int i = 0; i < limitsOptimizer.getScenarioInfo().customersInfo.length; i++) {
                CustomerInfo customer = limitsOptimizer.getScenarioInfo().customersInfo[i];
                double propLimit = customer.maxCalcLimit * curFactor;

                if (propLimit < customer.minRestrLimit)
                    propLimit = customer.minRestrLimit;
                else if (propLimit > customer.maxRestrLimit)
                    propLimit = customer.maxRestrLimit;

                if ((propLimit > customer.creditLimit && propLimit < customer.creditLimit * (1 + customer.minLimitGrowth)) ||
                        (propLimit < customer.creditLimit && propLimit > customer.creditLimit * (1 - customer.minLimitReduction)))
                    propLimit = customer.creditLimit;

                if (propLimit != customer.proposedLimit) {
                    customer.proposedLimit = propLimit;
                    CalcCustomer(customer, portfolioUl, limitsOptimizer.getPar().varMultiplier, limitsOptimizer);
                }
                proposedLimit += customer.num * customer.proposedLimit;
            }
            if (proposedLimit > limitsOptimizer.getScenarioInfo().creditAmount)
                hiFactor = curFactor;
            else
                loFactor = curFactor;
            countL3++;
        } while (hiFactor - loFactor > 0.005);
        logger.info("Finish new optimizer check check ");
        // Apply rounding
        for (CustomerInfo c : limitsOptimizer.getScenarioInfo().customersInfo) {
            c.maxCalcLimit =  applyRoundRules(c.maxCalcLimit, c.projTurnover, limitsOptimizer);
            c.proposedLimit = applyRoundRules(c.proposedLimit, c.projTurnover, limitsOptimizer);
            CalcCustomer(c, portfolioUl, limitsOptimizer.getPar().varMultiplier, limitsOptimizer);
        }

        logger.info("LimitsOptimizer finish : {} {} {}", countL1, countL2, countL3);
    }


    /**
     * Calculates outputs for the specific customer.
     * @param c Structure defining the customer's data
     */
    private void CalcCustomer(CustomerInfo c, double portfolioUl, double multiplier, LimitsOptimizer limitsOptimizer) {
        c.lgd = getWeightedLgd(c.proposedLimit, c.creditMitigants, limitsOptimizer);
        if (c.proposedLimit == 0) {
            c.capitalCushion = 0;
            c.fundingCost = 0;
            c.rwMargin = c.profitMargin;
            return;
        }
        double severity = c.proposedLimit * c.lgd;
        double annualTurnover = c.projTurnover * 360 / limitsOptimizer.getPar().turnoverDays;
        c.capitalCushion = c.pd * severity * severity * multiplier / portfolioUl;
        double projectedBalance = c.meanLimit == 0 || c.turnover == 0
                ? c.proposedLimit : c.meanLimit < c.proposedLimit
                ? c.proposedLimit * Math.min(c.meanBalance / c.meanLimit, 1)
                : Math.min(c.meanBalance * c.projTurnover / c.turnover, c.proposedLimit);
        c.fundingCost = projectedBalance * limitsOptimizer.getScenarioInfo().wacc / annualTurnover;
        double concentrationCost = (c.pd * severity + c.capitalCushion) * limitsOptimizer.getScenarioInfo().raroc / annualTurnover;
        c.rwMargin = c.profitMargin - c.pd * severity / annualTurnover - concentrationCost - c.fundingCost;
    }

    private int[] getArrayOrder(double[] source, boolean desc) {
        int[] order = new int[source.length];
        double[] s = source.clone();
        boolean[] b = new boolean[source.length];
        Arrays.sort(s);
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s.length; j++) {
                if (source[j] == s[i] && !b[j]) {
                    order[i] = j;
                    b[j] = true;
                    break;
                }
            }
        }
        if (desc) ArrayUtils.reverse(order);
        return order;
    }

    private double getWeightedLgd(double exposure, double[] mitigants, LimitsOptimizer limitsOptimizer) {
        if (exposure <= 0) {
            double minLgd = 1;
            for (int i = 0; i < limitsOptimizer.getMitSortOrder().length; i++) {
                if (limitsOptimizer.getMitSortOrder()[i] == 0 || mitigants[limitsOptimizer.getMitSortOrder()[i] - 1] > 0)
                    minLgd = Math.min(minLgd, limitsOptimizer.getPar().mitLgds[limitsOptimizer.getMitSortOrder()[i]]);
            }
            return minLgd;
        }

        // Get uncovered limit and weighted average lgd
        double uncovered = exposure;
        double loss = 0;
        for (int i = 0; i < limitsOptimizer.getMitSortOrder().length; i++) {
            if (limitsOptimizer.getMitSortOrder()[i] == 0) continue; // If uncovered then do not take into account
            double cover = mitigants[limitsOptimizer.getMitSortOrder()[i] - 1];
            if (uncovered >= cover) {
                loss += cover * limitsOptimizer.getPar().mitLgds[limitsOptimizer.getMitSortOrder()[i]];
                uncovered -= cover;
            } else {
                loss += uncovered * limitsOptimizer.getPar().mitLgds[limitsOptimizer.getMitSortOrder()[i]];
                uncovered = 0;
                break;
            }
        }
        if (uncovered > 0) loss += uncovered * limitsOptimizer.getPar().mitLgds[0];
        return loss / exposure;
    }

    private double applyRoundRules(double amount, double turnover, LimitsOptimizer limitsOptimizer) {
        double d = roundToSignificantDigits(amount, limitsOptimizer.getScenarioInfo().significantDigits);
        if (d < limitsOptimizer.getScenarioInfo().minLimitPct * turnover || d < limitsOptimizer.getScenarioInfo().minLimit * 0.5) return 0;
        return (Math.max(d, limitsOptimizer.getScenarioInfo().minLimit));
    }

    public static double roundToSignificantDigits(double num, int n) {
        if(num == 0) return 0;
        final double d = Math.ceil(Math.log10(num < 0 ? -num: num));
        final double magnitude = Math.pow(10, n - (int)d);
        return Math.round(num * magnitude) / magnitude;
    }
}
