package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class ScenarioPortfolioParameters implements Serializable {
	private static final long serialVersionUID = -8951425779047864450L;
	
	protected transient boolean newObject = false;
	protected transient boolean dirty = false;
	protected transient boolean deleted = false;
	int portfolioId;
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
	
	
	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public ScenarioPortfolioParameters getCopy() {
		ScenarioPortfolioParameters p = new ScenarioPortfolioParameters();
		p.maxLimitGrowth = this.maxLimitGrowth;
		p.maxLimitReduction = this.maxLimitReduction;
		p.minLimitGrowth = this.minLimitGrowth;
		p.minLimitReduction = this.minLimitReduction;
		p.minAcceptedLimitUse = this.minAcceptedLimitUse;
		p.maxDso = this.maxDso;
		p.minAcceptedRwMargin = this.minAcceptedRwMargin;
		p.salesChange = this.salesChange;
		p.marginChange = this.marginChange;
		p.pdChange = this.pdChange;
		p.worstAcceptableScore = this.worstAcceptableScore;
		return p;
	}
	
	public boolean isNew() {
		return newObject;
	}

	public void setNew(boolean newObject) {
		this.newObject = newObject;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDirty() {
		return dirty;
	}
	
	public void clearDirty() {
		dirty = false;
	}
	
	public Double getMaxLimitGrowth() {
		return maxLimitGrowth;
	}
	
	public void setMaxLimitGrowth(Double maxLimitGrowth) {
		this.maxLimitGrowth = maxLimitGrowth;
		dirty = true;
	}

	public Double getMaxLimitReduction() {
		return maxLimitReduction;
	}

	public void setMaxLimitReduction(Double maxLimitReduction) {
		this.maxLimitReduction = maxLimitReduction;
		dirty = true;
	}

	public Double getMinLimitGrowth() {
		return minLimitGrowth;
	}

	public void setMinLimitGrowth(Double minLimitGrowth) {
		this.minLimitGrowth = minLimitGrowth;
		dirty = true;
	}

	public Double getMinLimitReduction() {
		return minLimitReduction;
	}

	public void setMinLimitReduction(Double minLimitReduction) {
		this.minLimitReduction = minLimitReduction;
		dirty = true;
	}

	public Double getMinAcceptedLimitUse() {
		return minAcceptedLimitUse;
	}

	public void setMinAcceptedLimitUse(Double minAcceptedLimitUse) {
		this.minAcceptedLimitUse = minAcceptedLimitUse;
		dirty = true;
	}

	public Integer getMaxDso() {
		return maxDso;
	}

	public void setMaxDso(Integer maxDso) {
		this.maxDso = maxDso;
		dirty = true;
	}

	public double getMinAcceptedRwMargin() {
		return minAcceptedRwMargin;
	}

	public void setMinAcceptedRwMargin(double minAcceptedRwMargin) {
		this.minAcceptedRwMargin = minAcceptedRwMargin;
		dirty = true;
	}

	public Double getSalesChange() {
		return salesChange;
	}

	public void setSalesChange(Double salesChange) {
		this.salesChange = salesChange;
		dirty = true;
	}

	public Double getMarginChange() {
		return marginChange;
	}

	public void setMarginChange(Double marginChange) {
		this.marginChange = marginChange;
		dirty = true;
	}

	public Double getPdChange() {
		return pdChange;
	}

	public void setPdChange(Double pdChange) {
		this.pdChange = pdChange;
		dirty = true;
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
		dirty = true;
	}
}
