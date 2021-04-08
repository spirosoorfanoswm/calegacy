package eu.ark.creditark.services.creditarkservices.dto.scenario;

import java.io.Serializable;

public class ScenarioProspectParametersDto implements Serializable {

	private static final long serialVersionUID = 1196427764017407246L;
	private int prospectId;
	private String description;
	private int customersNum = 1;
	private double purchases;
	private Integer maxDso;
	private int gradeInx;
	private double profitMargin;
	private double[] mitigants;
	private double minAcceptedRwMargin;
	private String comments;
	
	public ScenarioProspectParametersDto(int prospectId, String description, int customersNum, double purchases,
			Integer maxDso, int gradeInx, double profitMargin, double[] mitigants, double minAcceptedRwMargin,
			String comments) {
		this.prospectId = prospectId;
		this.description = description;
		this.customersNum = customersNum;
		this.purchases = purchases;
		this.maxDso = maxDso;
		this.gradeInx = gradeInx;
		this.profitMargin = profitMargin;
		this.mitigants = mitigants;
		this.minAcceptedRwMargin = minAcceptedRwMargin;
		this.comments = comments;
	}
	public int getProspectId() {
		return prospectId;
	}
	public void setProspectId(int prospectId) {
		this.prospectId = prospectId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCustomersNum() {
		return customersNum;
	}
	public void setCustomersNum(int customersNum) {
		this.customersNum = customersNum;
	}
	public double getPurchases() {
		return purchases;
	}
	public void setPurchases(double purchases) {
		this.purchases = purchases;
	}
	public Integer getMaxDso() {
		return maxDso;
	}
	public void setMaxDso(Integer maxDso) {
		this.maxDso = maxDso;
	}
	public int getGradeInx() {
		return gradeInx;
	}
	public void setGradeInx(int gradeInx) {
		this.gradeInx = gradeInx;
	}
	public double getProfitMargin() {
		return profitMargin;
	}
	public void setProfitMargin(double profitMargin) {
		this.profitMargin = profitMargin;
	}
	public double[] getMitigants() {
		return mitigants;
	}
	public void setMitigants(double[] mitigants) {
		this.mitigants = mitigants;
	}
	public double getMinAcceptedRwMargin() {
		return minAcceptedRwMargin;
	}
	public void setMinAcceptedRwMargin(double minAcceptedRwMargin) {
		this.minAcceptedRwMargin = minAcceptedRwMargin;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	
}
