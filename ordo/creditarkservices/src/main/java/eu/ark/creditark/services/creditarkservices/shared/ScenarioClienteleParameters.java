package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class ScenarioClienteleParameters extends ScenarioPortfolioParameters implements Serializable {
	private static final long serialVersionUID = -6393541023478616035L;
	
	private double wacc;
	private double raroc;
	private double creditAmount;
	private Integer significantDigits;
	private Double minLimit;
	private Double minLimitPct;

	public double getWacc() {
		return wacc;
	}

	public void setWacc(double wacc) {
		this.wacc = wacc;
		dirty = true;
	}

	public double getRaroc() {
		return raroc;
	}

	public void setRaroc(double raroc) {
		this.raroc = raroc;
		dirty = true;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
		dirty = true;
	}

	public Integer getSignificantDigits() {
		return significantDigits;
	}

	public void setSignificantDigits(Integer significantDigits) {
		this.significantDigits = significantDigits;
		dirty = true;
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
}
