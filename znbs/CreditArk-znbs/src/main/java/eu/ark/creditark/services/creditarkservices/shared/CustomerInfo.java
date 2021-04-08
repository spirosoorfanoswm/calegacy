package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class    CustomerInfo implements Cloneable, Serializable {
	public String customerId;
	public int prospectId = -1;
	public int num = 1;
	public double turnover;
	public double projTurnover;
	public double creditLimit;
	public double meanLimit = 0;
	public double meanBalance = 0;
	public double maxBalance = 0;
	public double maxPurchases;
	public double pd;
	public double profitMargin;					// Input - output
	public double[] creditMitigants;			//(zero index is reserved for uncovered)
	public double maxLimitGrowth = 1;
	public double maxLimitReduction = 0;
	public double minLimitGrowth = 0;
	public double minLimitReduction = 0;
	public double worstAcceptedPd;
	public double maxAcceptedDso;
	public double minAcceptedLimitUse;
	public double minAcceptedRwMargin;
	public double maxRestrLimit = 0;            // intermediate
	public double minRestrLimit = 0;            // intermediate
	public double maxCalcLimit = 0;             // output
	public double proposedLimit = -1;           // output
	public double lgd;                          // output
	public double fundingCost;                  // output
	public double capitalCushion;				// output
	public double rwMargin;                     // output

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public int getProspectId() {
		return prospectId;
	}

	public void setProspectId(int prospectId) {
		this.prospectId = prospectId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	public double getProjTurnover() {
		return projTurnover;
	}

	public void setProjTurnover(double projTurnover) {
		this.projTurnover = projTurnover;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public double getMeanLimit() {
		return meanLimit;
	}

	public void setMeanLimit(double meanLimit) {
		this.meanLimit = meanLimit;
	}

	public double getMeanBalance() {
		return meanBalance;
	}

	public void setMeanBalance(double meanBalance) {
		this.meanBalance = meanBalance;
	}

	public double getMaxBalance() {
		return maxBalance;
	}

	public void setMaxBalance(double maxBalance) {
		this.maxBalance = maxBalance;
	}

	public double getMaxPurchases() {
		return maxPurchases;
	}

	public void setMaxPurchases(double maxPurchases) {
		this.maxPurchases = maxPurchases;
	}

	public double getPd() {
		return pd;
	}

	public void setPd(double pd) {
		this.pd = pd;
	}

	public double getProfitMargin() {
		return profitMargin;
	}

	public void setProfitMargin(double profitMargin) {
		this.profitMargin = profitMargin;
	}

	public double[] getCreditMitigants() {
		return creditMitigants;
	}

	public void setCreditMitigants(double[] creditMitigants) {
		this.creditMitigants = creditMitigants;
	}

	public double getMaxLimitGrowth() {
		return maxLimitGrowth;
	}

	public void setMaxLimitGrowth(double maxLimitGrowth) {
		this.maxLimitGrowth = maxLimitGrowth;
	}

	public double getMaxLimitReduction() {
		return maxLimitReduction;
	}

	public void setMaxLimitReduction(double maxLimitReduction) {
		this.maxLimitReduction = maxLimitReduction;
	}

	public double getMinLimitGrowth() {
		return minLimitGrowth;
	}

	public void setMinLimitGrowth(double minLimitGrowth) {
		this.minLimitGrowth = minLimitGrowth;
	}

	public double getMinLimitReduction() {
		return minLimitReduction;
	}

	public void setMinLimitReduction(double minLimitReduction) {
		this.minLimitReduction = minLimitReduction;
	}

	public double getWorstAcceptedPd() {
		return worstAcceptedPd;
	}

	public void setWorstAcceptedPd(double worstAcceptedPd) {
		this.worstAcceptedPd = worstAcceptedPd;
	}

	public double getMaxAcceptedDso() {
		return maxAcceptedDso;
	}

	public void setMaxAcceptedDso(double maxAcceptedDso) {
		this.maxAcceptedDso = maxAcceptedDso;
	}

	public double getMinAcceptedLimitUse() {
		return minAcceptedLimitUse;
	}

	public void setMinAcceptedLimitUse(double minAcceptedLimitUse) {
		this.minAcceptedLimitUse = minAcceptedLimitUse;
	}

	public double getMinAcceptedRwMargin() {
		return minAcceptedRwMargin;
	}

	public void setMinAcceptedRwMargin(double minAcceptedRwMargin) {
		this.minAcceptedRwMargin = minAcceptedRwMargin;
	}

	public double getMaxRestrLimit() {
		return maxRestrLimit;
	}

	public void setMaxRestrLimit(double maxRestrLimit) {
		this.maxRestrLimit = maxRestrLimit;
	}

	public double getMinRestrLimit() {
		return minRestrLimit;
	}

	public void setMinRestrLimit(double minRestrLimit) {
		this.minRestrLimit = minRestrLimit;
	}

	public double getMaxCalcLimit() {
		return maxCalcLimit;
	}

	public void setMaxCalcLimit(double maxCalcLimit) {
		this.maxCalcLimit = maxCalcLimit;
	}

	public double getProposedLimit() {
		return proposedLimit;
	}

	public void setProposedLimit(double proposedLimit) {
		this.proposedLimit = proposedLimit;
	}

	public double getLgd() {
		return lgd;
	}

	public void setLgd(double lgd) {
		this.lgd = lgd;
	}

	public double getFundingCost() {
		return fundingCost;
	}

	public void setFundingCost(double fundingCost) {
		this.fundingCost = fundingCost;
	}

	public double getCapitalCushion() {
		return capitalCushion;
	}

	public void setCapitalCushion(double capitalCushion) {
		this.capitalCushion = capitalCushion;
	}

	public double getRwMargin() {
		return rwMargin;
	}

	public void setRwMargin(double rwMargin) {
		this.rwMargin = rwMargin;
	}

	public CustomerInfo getCopy() throws CloneNotSupportedException {
		return (CustomerInfo)clone();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CustomerInfo{");
		sb.append("customerId='").append(customerId).append('\'');
		sb.append(", prospectId=").append(prospectId);
		sb.append(", num=").append(num);
		sb.append(", turnover=").append(turnover);
		sb.append(", projTurnover=").append(projTurnover);
		sb.append(", creditLimit=").append(creditLimit);
		sb.append(", meanLimit=").append(meanLimit);
		sb.append(", meanBalance=").append(meanBalance);
		sb.append(", maxBalance=").append(maxBalance);
		sb.append(", maxPurchases=").append(maxPurchases);
		sb.append(", pd=").append(pd);
		sb.append(", profitMargin=").append(profitMargin);
		sb.append(", creditMitigants=").append(Arrays.toString(creditMitigants));
		sb.append(", maxLimitGrowth=").append(maxLimitGrowth);
		sb.append(", maxLimitReduction=").append(maxLimitReduction);
		sb.append(", minLimitGrowth=").append(minLimitGrowth);
		sb.append(", minLimitReduction=").append(minLimitReduction);
		sb.append(", worstAcceptedPd=").append(worstAcceptedPd);
		sb.append(", maxAcceptedDso=").append(maxAcceptedDso);
		sb.append(", minAcceptedLimitUse=").append(minAcceptedLimitUse);
		sb.append(", minAcceptedRwMargin=").append(minAcceptedRwMargin);
		sb.append(", maxRestrLimit=").append(maxRestrLimit);
		sb.append(", minRestrLimit=").append(minRestrLimit);
		sb.append(", maxCalcLimit=").append(maxCalcLimit);
		sb.append(", proposedLimit=").append(proposedLimit);
		sb.append(", lgd=").append(lgd);
		sb.append(", fundingCost=").append(fundingCost);
		sb.append(", capitalCushion=").append(capitalCushion);
		sb.append(", rwMargin=").append(rwMargin);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CustomerInfo)) return false;
		CustomerInfo that = (CustomerInfo) o;
		return prospectId == that.prospectId &&
				customerId.equals(that.customerId) ;
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(prospectId, customerId);
		return result;
	}
}
