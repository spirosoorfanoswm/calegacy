package eu.ark.creditark.services.creditarkservices.dto.scenario;

import eu.ark.creditark.services.creditarkservices.dto.KeyValueDto;
import eu.ark.creditark.services.creditarkservices.dto.KeyValuesDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScenarioClienteleParametersDto extends ScenarioPortfolioParametersDto implements Serializable{

	private static final long serialVersionUID = -5005191099503292607L;
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
	}
	public double getRaroc() {
		return raroc;
	}
	public void setRaroc(double raroc) {
		this.raroc = raroc;
	}
	public double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
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

}
