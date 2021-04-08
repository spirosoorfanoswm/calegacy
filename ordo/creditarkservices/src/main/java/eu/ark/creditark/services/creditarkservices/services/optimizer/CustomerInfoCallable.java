package eu.ark.creditark.services.creditarkservices.services.optimizer;

import eu.ark.creditark.services.creditarkservices.optimizer.LimitsOptimizer;
import eu.ark.creditark.services.creditarkservices.shared.CustomerInfo;

import java.util.concurrent.Callable;

public class CustomerInfoCallable implements Callable<CustomerInfo> {
    private CustomerInfo cust;
    private LimitsOptimizer limitsOptimizer;
    private double portfolioUl;

    public CustomerInfoCallable(CustomerInfo cust, LimitsOptimizer limitsOptimizer, double portfolioUl) {
        this.cust = cust;
        this.limitsOptimizer = limitsOptimizer;
        this.portfolioUl = portfolioUl;
    }

    @Override
    public CustomerInfo call() throws Exception {
        if (this.cust.meanBalance < 0) this.cust.meanBalance = 0;
        if (this.cust.projTurnover < 0) this.cust.projTurnover = 0;
        if (this.cust.pd <= this.cust.worstAcceptedPd && this.cust.projTurnover > 0) {
            this.cust.maxRestrLimit = this.cust.maxAcceptedDso * this.cust.maxPurchases * 12 / limitsOptimizer.getPar().turnoverDays;
            if (this.cust.creditLimit > 0) {
                if (this.cust.customerId != null) { // do not apply to prospects
                    this.cust.maxRestrLimit = Math.min(this.cust.maxRestrLimit, this.cust.creditLimit * (1 + this.cust.maxLimitGrowth));
                    this.cust.minRestrLimit = Math.min(this.cust.maxRestrLimit, this.cust.creditLimit * (1 - this.cust.maxLimitReduction));
                }
                if (this.cust.minAcceptedLimitUse > 0) {
                    this.cust.maxRestrLimit = Math.min(this.cust.maxRestrLimit, this.cust.maxBalance / (this.cust.minAcceptedLimitUse));

                }
            }

            if (this.cust.maxRestrLimit <= this.cust.minRestrLimit) {
                this.cust.maxCalcLimit = this.cust.minRestrLimit;
                this.cust.proposedLimit = this.cust.minRestrLimit;
            }
            else {
                // Get the maximum accepted limit so that it does not exceed the MinAcceptedRwMargin
                double d = this.cust.profitMargin - this.cust.minAcceptedRwMargin;
                if (d <= 0 || this.cust.projTurnover == 0) {
                    this.cust.maxCalcLimit = this.cust.minRestrLimit;
                    this.cust.maxRestrLimit = this.cust.minRestrLimit;
                }
                else {

                    CustomerInfo custClone = this.cust.getCopy();
                    // Set initial value for MaxCalcLimit
                    double annualTurnover = cust.projTurnover * 360 / limitsOptimizer.getPar().turnoverDays;
                    double lo = 0;
                    cust.pd = cust.pd<0?0.001:cust.pd;
                    double hi = (annualTurnover * d) / (cust.pd * limitsOptimizer.getPar().mitLgds[0]); // Rough initial max

                    // Compute the maximum limit
                    while (hi >= lo + Math.max(0.5 * limitsOptimizer.getScenarioInfo().minLimit, 100)) {
                        cust.proposedLimit = (hi + lo) * 0.5;
                        CalcCustomer(custClone, portfolioUl, limitsOptimizer.getPar().varMultiplier, limitsOptimizer);
                        if (cust.rwMargin < cust.minAcceptedRwMargin)
                            hi = cust.proposedLimit;
                        else
                            lo = cust.proposedLimit;
                    }
                    this.cust.maxCalcLimit = cust.proposedLimit;
                }
            }
        }
        else {
            this.cust.minRestrLimit = 0;
            this.cust.maxRestrLimit = 0;
            this.cust.proposedLimit = 0;
        }
        return this.cust;
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

}
