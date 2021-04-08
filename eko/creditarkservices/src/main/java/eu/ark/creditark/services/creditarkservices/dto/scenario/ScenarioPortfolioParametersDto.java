package eu.ark.creditark.services.creditarkservices.dto.scenario;

import java.io.Serializable;

public class ScenarioPortfolioParametersDto implements Serializable {

	private static final long serialVersionUID = -4972949427306264252L;
	private int portfolioId;
	private Double maxLimitGrowth;
	private Double maxLimitReduction;
	private Double minLimitGrowth;
	private Double minLimitReduction;
	private Double minAcceptedLimitUse;
	private Integer maxDso;
	private double minAcceptedRwMargin;
	private Double salesChange;
	private Double marginChange;
	private Double pdChange;
	private String worstAcceptableScore;
	private String userComments;
	
	public ScenarioPortfolioParametersDto() {
	}
	public ScenarioPortfolioParametersDto(int portfolioId, Double maxLimitGrowth, Double maxLimitReduction,
			Double minLimitGrowth, Double minLimitReduction, Double minAcceptedLimitUse, Integer maxDso,
			double minAcceptedRwMargin, Double salesChange, Double marginChange, Double pdChange,
			String worstAcceptableScore, String userComments) {
		this.portfolioId = portfolioId;
		this.maxLimitGrowth = maxLimitGrowth;
		this.maxLimitReduction = maxLimitReduction;
		this.minLimitGrowth = minLimitGrowth;
		this.minLimitReduction = minLimitReduction;
		this.minAcceptedLimitUse = minAcceptedLimitUse;
		this.maxDso = maxDso;
		this.minAcceptedRwMargin = minAcceptedRwMargin;
		this.salesChange = salesChange;
		this.marginChange = marginChange;
		this.pdChange = pdChange;
		this.worstAcceptableScore = worstAcceptableScore;
		this.userComments = userComments;
	}
	public int getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
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
	public Double getMinAcceptedLimitUse() {
		return minAcceptedLimitUse;
	}
	public void setMinAcceptedLimitUse(Double minAcceptedLimitUse) {
		this.minAcceptedLimitUse = minAcceptedLimitUse;
	}
	public Integer getMaxDso() {
		return maxDso;
	}
	public void setMaxDso(Integer maxDso) {
		this.maxDso = maxDso;
	}
	public double getMinAcceptedRwMargin() {
		return minAcceptedRwMargin;
	}
	public void setMinAcceptedRwMargin(double minAcceptedRwMargin) {
		this.minAcceptedRwMargin = minAcceptedRwMargin;
	}
	public Double getSalesChange() {
		return salesChange;
	}
	public void setSalesChange(Double salesChange) {
		this.salesChange = salesChange;
	}
	public Double getMarginChange() {
		return marginChange;
	}
	public void setMarginChange(Double marginChange) {
		this.marginChange = marginChange;
	}
	public Double getPdChange() {
		return pdChange;
	}
	public void setPdChange(Double pdChange) {
		this.pdChange = pdChange;
	}
	public String getWorstAcceptableScore() {
		return worstAcceptableScore;
	}
	public void setWorstAcceptableScore(String worstAcceptableScore) {
		this.worstAcceptableScore = worstAcceptableScore;
	}
	public String getUserComments() {
		return userComments;
	}
	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}
	
}
