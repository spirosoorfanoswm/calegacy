package eu.ark.creditark.services.creditarkservices.dto.scenario;

import java.io.Serializable;

public class ScenarioCustomerStatisticsDto implements Serializable {

	private static final long serialVersionUID = -8884506760140098809L;
	
	private int prospectId;
	private double projectedPurchases;
	private double maxLimit;
	private double proposedLimit;
	private double pd;
	private double lgd;
	private double fundingCost;
	private double workingCapital;
	private double rwMargin;
	private double effectiveDso;
	
	
	public int getProspectId() {
		return prospectId;
	}
	public void setProspectId(int prospectId) {
		this.prospectId = prospectId;
	}
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

	public double getEffectiveDso() {
		return effectiveDso;
	}

	public void setEffectiveDso(double effectiveDso) {
		this.effectiveDso = effectiveDso;
	}
}
