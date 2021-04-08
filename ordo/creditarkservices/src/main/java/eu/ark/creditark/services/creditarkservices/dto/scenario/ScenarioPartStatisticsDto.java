package eu.ark.creditark.services.creditarkservices.dto.scenario;

import java.io.Serializable;

public class ScenarioPartStatisticsDto implements Serializable {
	

	private static final long serialVersionUID = -319523427656500663L;
	private int customersNum;
	private int scenarioCustomersNum;
	private double turnover;
	private double projectedTurnover;
	private double currentLimits;
	private double proposedLimits;
	private double fundingCost;
	private double profitMargin;
	private double meanRwm;
	private double proposedProvisions;
	private double proposedWc;
	public int getCustomersNum() {
		return customersNum;
	}
	public void setCustomersNum(int customersNum) {
		this.customersNum = customersNum;
	}
	public int getScenarioCustomersNum() {
		return scenarioCustomersNum;
	}
	public void setScenarioCustomersNum(int scenarioCustomersNum) {
		this.scenarioCustomersNum = scenarioCustomersNum;
	}
	public double getTurnover() {
		return turnover;
	}
	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}
	public double getProjectedTurnover() {
		return projectedTurnover;
	}
	public void setProjectedTurnover(double projectedTurnover) {
		this.projectedTurnover = projectedTurnover;
	}
	public double getCurrentLimits() {
		return currentLimits;
	}
	public void setCurrentLimits(double currentLimits) {
		this.currentLimits = currentLimits;
	}
	public double getProposedLimits() {
		return proposedLimits;
	}
	public void setProposedLimits(double proposedLimits) {
		this.proposedLimits = proposedLimits;
	}
	public double getFundingCost() {
		return fundingCost;
	}
	public void setFundingCost(double fundingCost) {
		this.fundingCost = fundingCost;
	}
	public double getProfitMargin() {
		return profitMargin;
	}
	public void setProfitMargin(double profitMargin) {
		this.profitMargin = profitMargin;
	}
	public double getMeanRwm() {
		return meanRwm;
	}
	public void setMeanRwm(double meanRwm) {
		this.meanRwm = meanRwm;
	}
	public double getProposedProvisions() {
		return proposedProvisions;
	}
	public void setProposedProvisions(double proposedProvisions) {
		this.proposedProvisions = proposedProvisions;
	}
	public double getProposedWc() {
		return proposedWc;
	}
	public void setProposedWc(double proposedWc) {
		this.proposedWc = proposedWc;
	}

}
