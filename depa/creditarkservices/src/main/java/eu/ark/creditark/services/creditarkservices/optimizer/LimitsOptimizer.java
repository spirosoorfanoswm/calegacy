package eu.ark.creditark.services.creditarkservices.optimizer;

import java.util.Arrays;

import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import org.apache.commons.lang3.ArrayUtils;

import eu.ark.creditark.services.creditarkservices.shared.CustomerInfo;
import eu.ark.creditark.services.creditarkservices.shared.Parameters;
import eu.ark.creditark.services.creditarkservices.shared.ScenarioInfo;

public class LimitsOptimizer {
    private ScenarioInfo scenarioInfo;
    private Parameters par;
    private int[] mitSortOrder; // A descendant sorted array of mitigants according to their LGD

    public LimitsOptimizer(
    		ScenarioInfo scenarioInfo,
    		Parameters par) throws ScenarioException {
        if (scenarioInfo.customersInfo == null || scenarioInfo.customersInfo.length == 0)
            throw new ScenarioException("CustomerInfo: Array cannot be empty.");
        if (par.mitLgds == null || par.mitLgds.length == 0)
            throw new ScenarioException("Mitigant LGDs: Array cannot be empty.");

        // Check user input for validity
        if (scenarioInfo.creditAmount <= 0)
            throw new ScenarioException("Credit amount: Value cannot be negative or zero.");
        if (scenarioInfo.wacc < 0 || scenarioInfo.wacc > 0.5)
            throw new ScenarioException("WACC has an invalid value.");
        if (scenarioInfo.raroc < 0 || scenarioInfo.raroc > 1)
            throw new ScenarioException("RAROC has an invalid value.");
        if (scenarioInfo.significantDigits < 1 || scenarioInfo.significantDigits > 20)
        	throw new ScenarioException("The value of Significant Digits is invalid.");
        if (scenarioInfo.minLimit < 0)
            throw new ScenarioException("Minimum Limit has an invalid value.");

        this.scenarioInfo = scenarioInfo;
        this.par = par;
        this.mitSortOrder = getArrayOrder(par.mitLgds, false);
    }

  
    public void calcPortfolio() throws ScenarioException, CloneNotSupportedException {
    
    	// Get portfolio's risk moments
    	double portfolioUl2 = 0;
    	double portfolioUl;
    	
    	// Check validity and calculate risk moments
    	for (int i = 0; i < scenarioInfo.customersInfo.length; i++) {
    		// Initialize customer's data
            CustomerInfo cust = scenarioInfo.customersInfo[i];

            // Check input validity
            String id;
            if (cust.customerId == null)
            	id = "Prospect:" + Integer.toString(cust.prospectId);
            else
            	id = "Customer:" + cust.customerId;
            
            for (int j = 0; j < cust.creditMitigants.length; j++) {
            	if (cust.creditMitigants[j] < 0)
            		 throw new ScenarioException(id + ". Mitigant cannot have a negative value.");
                if (cust.profitMargin < -0.5 || cust.profitMargin > 0.99)
                    throw new ScenarioException(id + ". Invalid profit margin.");
                if (cust.maxLimitGrowth < 0)
                    throw new ScenarioException(id + ". Invalid value for maximum limit growth.");
                if (cust.minLimitGrowth < 0 || cust.minLimitGrowth > 0.5)
                    throw new ScenarioException(id + ". Invalid value for minimum limit growth.");
                if (cust.maxLimitReduction < 0 || cust.maxLimitReduction > 1)
                    throw new ScenarioException(id + ". Invalid value for maximum limit reduction.");
                if (cust.minLimitReduction < 0 || cust.minLimitGrowth > 0.5)
                    throw new ScenarioException(id + ". Invalid value for minimum limit reduction.");
                if (cust.worstAcceptedPd < 0 || cust.worstAcceptedPd > 1)
                    throw new ScenarioException(id + ". Invalid value for worst accepted PD.");
                if (cust.pd < 0 || cust.pd > 1)
                    throw new ScenarioException(id + ". Invalid PD value.");
                if (cust.maxAcceptedDso < 0)
                    throw new ScenarioException(id + ". Maximum accepted DSO cannot have a negative value.");
                if (cust.minAcceptedLimitUse < 0 || cust.minAcceptedLimitUse > 1)
                    throw new ScenarioException(id + ". Invalid value for minimum accepted limit use.");
                if (cust.minAcceptedRwMargin < -0.5 || cust.minAcceptedRwMargin > 0.99)
                    throw new ScenarioException(id + ". Invalid value for minimum accepted profit margin.");
            }
            // Calculate risk moments
            double severity = cust.meanBalance * getWeightedLgd(cust.meanBalance, cust.creditMitigants);
            portfolioUl2 += cust.num * cust.pd * severity * severity;
    	}
    	portfolioUl = Math.sqrt(portfolioUl2);
    	
        // Initialize
        for (int i = 0; i < scenarioInfo.customersInfo.length; i++) {
        	// Initialize customer's data
            CustomerInfo customer = scenarioInfo.customersInfo[i];
            
            // Set initial values
            if (customer.meanBalance < 0) customer.meanBalance = 0;
            if (customer.projTurnover < 0) customer.projTurnover = 0;
            if (customer.pd <= customer.worstAcceptedPd && customer.projTurnover > 0) {
                customer.maxRestrLimit = customer.maxAcceptedDso * customer.maxPurchases * 12 / par.turnoverDays;
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
                        double annualTurnover = cust.projTurnover * 360 / par.turnoverDays;
                        double lo = 0;
                        double hi = (annualTurnover * d) / (cust.pd * par.mitLgds[0]); // Rough initial max
                        
                        // Compute the maximum limit
                        while (hi >= lo + Math.max(0.5 * scenarioInfo.minLimit, 100)) {
                            cust.proposedLimit = (hi + lo) * 0.5;
                            CalcCustomer(cust, portfolioUl, par.varMultiplier);
                            if (cust.rwMargin < cust.minAcceptedRwMargin)
                                hi = cust.proposedLimit;
                            else
                                lo = cust.proposedLimit;
                        }
                        customer.maxCalcLimit = cust.proposedLimit;
                    }
                }
            }
            else {
                customer.minRestrLimit = 0;
                customer.maxRestrLimit = 0;
                customer.proposedLimit = 0;
            }
        }
        

        // Main loop
        double loFactor = 0;
        double hiFactor = 1;
        double proposedLimit;

        do {
            proposedLimit = 0;
            double curFactor = (loFactor + hiFactor) * 0.5;

            for (int i = 0; i < scenarioInfo.customersInfo.length; i++) {
                CustomerInfo customer = scenarioInfo.customersInfo[i];
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
                    CalcCustomer(customer, portfolioUl, par.varMultiplier);
                }
                proposedLimit += customer.num * customer.proposedLimit;
            }
            if (proposedLimit > scenarioInfo.creditAmount) 
                hiFactor = curFactor;
            else
                loFactor = curFactor;
        } while (hiFactor - loFactor > 0.005);
        
        // Apply rounding
        for (CustomerInfo c : scenarioInfo.customersInfo) {
        	c.maxCalcLimit =  applyRoundRules(c.maxCalcLimit, c.projTurnover);
        	c.proposedLimit = applyRoundRules(c.proposedLimit, c.projTurnover);
        	CalcCustomer(c, portfolioUl, par.varMultiplier);
        }
    }


    /**
     * Calculates outputs for the specific customer.
     * @param c Structure defining the customer's data
     */
    private void CalcCustomer(CustomerInfo c, double portfolioUl, double multiplier) {
    	c.lgd = getWeightedLgd(c.proposedLimit, c.creditMitigants);
    	if (c.proposedLimit == 0) {
    		c.capitalCushion = 0;
    		c.fundingCost = 0;
    		c.rwMargin = c.profitMargin;
    		return;
    	}
        double severity = c.proposedLimit * c.lgd;
        double annualTurnover = c.projTurnover * 360 / par.turnoverDays;
        c.capitalCushion = c.pd * severity * severity * multiplier / portfolioUl;
        double projectedBalance = c.meanLimit == 0 || c.turnover == 0
        		? c.proposedLimit : c.meanLimit < c.proposedLimit 
        				? c.proposedLimit * Math.min(c.meanBalance / c.meanLimit, 1)
        				: Math.min(c.meanBalance * c.projTurnover / c.turnover, c.proposedLimit);
        c.fundingCost = projectedBalance * scenarioInfo.wacc / annualTurnover;
        double concentrationCost = (c.pd * severity + c.capitalCushion) * scenarioInfo.raroc / annualTurnover;
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

    private double getWeightedLgd(double exposure, double[] mitigants) {
    	if (exposure <= 0) {
    		double minLgd = 1;
    		for (int i = 0; i < mitSortOrder.length; i++) {
    			if (mitSortOrder[i] == 0 || mitigants[mitSortOrder[i] - 1] > 0)
    				minLgd = Math.min(minLgd, par.mitLgds[mitSortOrder[i]]);
    		}
    		return minLgd;
    	}
    	
    	// Get uncovered limit and weighted average lgd
		double uncovered = exposure;
		double loss = 0;
		for (int i = 0; i < mitSortOrder.length; i++) {
			if (mitSortOrder[i] == 0) continue; // If uncovered then do not take into account
			double cover = mitigants[mitSortOrder[i] - 1];
			 if (uncovered >= cover) {
				 loss += cover * par.mitLgds[mitSortOrder[i]];
				 uncovered -= cover;
			 } else {
				 loss += uncovered * par.mitLgds[mitSortOrder[i]];
				 uncovered = 0;
				 break;
			 }
		}
		if (uncovered > 0) loss += uncovered * par.mitLgds[0];
		return loss / exposure;
    }
    
    private double applyRoundRules(double amount, double turnover) {
    	double d = roundToSignificantDigits(amount, scenarioInfo.significantDigits);
    	if (d < scenarioInfo.minLimitPct * turnover || d < scenarioInfo.minLimit * 0.5) return 0;
    	return (Math.max(d, scenarioInfo.minLimit));
    }
    
    public static double roundToSignificantDigits(double num, int n) {
        if(num == 0) return 0;
        final double d = Math.ceil(Math.log10(num < 0 ? -num: num));
        final double magnitude = Math.pow(10, n - (int)d);
        return Math.round(num * magnitude) / magnitude;
    }


    public ScenarioInfo getScenarioInfo() {
        return scenarioInfo;
    }

    public void setScenarioInfo(ScenarioInfo scenarioInfo) {
        this.scenarioInfo = scenarioInfo;
    }

    public Parameters getPar() {
        return par;
    }

    public void setPar(Parameters par) {
        this.par = par;
    }

    public int[] getMitSortOrder() {
        return mitSortOrder;
    }

    public void setMitSortOrder(int[] mitSortOrder) {
        this.mitSortOrder = mitSortOrder;
    }
}
