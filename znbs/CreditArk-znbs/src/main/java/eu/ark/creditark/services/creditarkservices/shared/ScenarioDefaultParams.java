package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class ScenarioDefaultParams implements Serializable {
	private static final long serialVersionUID = -6721516829003965522L;
	
	public double raroc;
	public double wacc;
	public String worstAcceptedScore;
	public Integer maxDso;
	public Double minAcceptedLimitUse;
	public Double maxLimitGrowth;
	public Double maxLimitReduction;
	public Double minLimitGrowth;
	public Double minLimitReduction;
	public Integer significantDigits;
	public Double minLimit;
	public Double minLimitPct;
	public Double minAcceptedRwm;
	public double getRaroc() {
		return raroc;
	}
	public void setRaroc(double raroc) {
		this.raroc = raroc;
	}
	public double getWacc() {
		return wacc;
	}
	public void setWacc(double wacc) {
		this.wacc = wacc;
	}
	public String getWorstAcceptedScore() {
		return worstAcceptedScore;
	}
	public void setWorstAcceptedScore(String worstAcceptedScore) {
		this.worstAcceptedScore = worstAcceptedScore;
	}
	public Integer getMaxDso() {
		return maxDso;
	}
	public void setMaxDso(Integer maxDso) {
		this.maxDso = maxDso;
	}
	public Double getMinAcceptedLimitUse() {
		return minAcceptedLimitUse;
	}
	public void setMinAcceptedLimitUse(Double minAcceptedLimitUse) {
		this.minAcceptedLimitUse = minAcceptedLimitUse;
	}
	public Double getMaxLimitGrowth() {
		return maxLimitGrowth;
	}
	public void setMaxLimitGrowth(Double maxLimitGrowth) {
		this.maxLimitGrowth = maxLimitGrowth;
	}
	public Double getMaxLimitReduction() {
		return maxLimitReduction;
	}
	public void setMaxLimitReduction(Double maxLimitReduction) {
		this.maxLimitReduction = maxLimitReduction;
	}
	public Double getMinLimitGrowth() {
		return minLimitGrowth;
	}
	public void setMinLimitGrowth(Double minLimitGrowth) {
		this.minLimitGrowth = minLimitGrowth;
	}
	public Double getMinLimitReduction() {
		return minLimitReduction;
	}
	public void setMinLimitReduction(Double minLimitReduction) {
		this.minLimitReduction = minLimitReduction;
	}
	public Integer getSignificantDigits() {
		return significantDigits;
	}
	public void setSignificantDigits(Integer significantDigits) {
		this.significantDigits = significantDigits;
	}
	public Double getMinLimit() {
		return minLimit;
	}
	public void setMinLimit(Double minLimit) {
		this.minLimit = minLimit;
	}
	public Double getMinLimitPct() {
		return minLimitPct;
	}
	public void setMinLimitPct(Double minLimitPct) {
		this.minLimitPct = minLimitPct;
	}
	public Double getMinAcceptedRwm() {
		return minAcceptedRwm;
	}
	public void setMinAcceptedRwm(Double minAcceptedRwm) {
		this.minAcceptedRwm = minAcceptedRwm;
	}
	
	
}
