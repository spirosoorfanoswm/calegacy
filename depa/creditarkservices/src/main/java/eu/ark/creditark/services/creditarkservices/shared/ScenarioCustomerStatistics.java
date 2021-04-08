package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class ScenarioCustomerStatistics implements Serializable {
	private static final long serialVersionUID = 3958886235980612512L;
	
	private double projectedPurchases;
	private double maxLimit;
	private double proposedLimit;
	private double pd;
	private double lgd;
	private double fundingCost;
	private double workingCapital;
	private double rwMargin;
	
	public double getProjectedPurchases() {
		return projectedPurchases;
	}

	public void setProjectedPurchases(double projectedPurchases) {
		this.projectedPurchases = projectedPurchases;
	}

	public double getMaxLimit() {
		return maxLimit;
	}
	
	public void setMaxLimit(double maxLimit) {
		this.maxLimit = maxLimit;
	}

	public double getProposedLimit() {
		return proposedLimit;
	}

	public void setProposedLimit(double proposedLimit) {
		this.proposedLimit = proposedLimit;
	}

	public double getPd() {
		return pd;
	}

	public void setPd(double pd) {
		this.pd = pd;
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


	public double getWorkingCapital() {
		return workingCapital;
	}

	public void setWorkingCapital(double workingCapital) {
		this.workingCapital = workingCapital;
	}

	public double getRwMargin() {
		return rwMargin;
	}

	public void setRwMargin(double rwMargin) {
		this.rwMargin = rwMargin;
	}
	
	public double getEffectedDso() {
		return projectedPurchases == 0 ? 0 : proposedLimit * 360 / projectedPurchases;
	}
}
