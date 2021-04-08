package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;

public class ScenarioProspectParameters implements Serializable {
	private static final long serialVersionUID = -3251189094650216324L;
	
	private transient boolean newObject = false;
	protected transient boolean dirty = false;
	protected transient boolean deleted = false;
	
	private String description;
	private int customersNum = 1;
	private double purchases;
	private Integer maxDso;
	private int gradeInx;
	private double profitMargin;
	private double[] mitigants;
	private double minAcceptedRwMargin;
	private String comments;
	
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

	public String getDescription() {
		return description;
	}
	
	public void setInitialDescription(String description) {
		this.description = description;
	}

	public void setDescription(String description) {
		this.description = description;
		this.dirty = true;
	}

	public int getCustomersNum() {
		return customersNum;
	}

	public void setCustomersNum(int customersNum) {
		this.customersNum = customersNum;
		this.dirty = true;
	}

	public double getPurchases() {
		return purchases;
	}

	public void setPurchases(double purchases) {
		this.purchases = purchases;
		this.dirty = true;
	}

	public Integer getMaxDso() {
		return maxDso;
	}

	public void setMaxDso(Integer maxDso) {
		this.maxDso = maxDso;
		this.dirty = true;
	}

	public int getGradeInx() {
		return gradeInx;
	}

	public void setGradeInx(int gradeInx) {
		this.gradeInx = gradeInx;
		this.dirty = true;
	}

	public double getProfitMargin() {
		return profitMargin;
	}

	public void setProfitMargin(double profitMargin) {
		this.profitMargin = profitMargin;
		this.dirty = true;
	}

	public double[] getMitigants() {
		return mitigants;
	}

	public void setMitigants(double[] mitigants) {
		this.mitigants = mitigants;
		this.dirty = true;
	}

	public double getMinAcceptedRwMargin() {
		return minAcceptedRwMargin;
	}

	public void setMinAcceptedRwMargin(double minAcceptedRwMargin) {
		this.minAcceptedRwMargin = minAcceptedRwMargin;
		this.dirty = true;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
