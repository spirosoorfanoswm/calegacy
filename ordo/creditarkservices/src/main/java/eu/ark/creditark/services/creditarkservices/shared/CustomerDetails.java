package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class CustomerDetails implements Serializable {
	private static final long serialVersionUID = 3149040385605261918L;

	// Customer static data
	private int contextId;
	private String customerId;
	private String portfolio;
	private String vatNumber;
	private String customerName;
	private boolean affiliate;
	private String groupName;
	private String industryId;
	private String areaId;
	private Date customerSince;
	private boolean optimizationExcluded;
	private boolean extAssessmentExcluded;
	
	// Snapshot data
	private Date snapshotDate;
	private String customerStatus;
	private double profitMargin;
	private double[] activity;
	private double[] exposure;
	private double[] pastDueAmount;
	private double[] creditMitigants;
	private Double[] criteria;
	private String behavioralScore;
	private String creditArkScore;
	private double pd;
	private double[] provisions;
	
	// Historical data
	private Date prevDate;
	private int periods0;
	private Double maxBalance0;
	private Date maxBalanceDate0;
	private Double minBalance0;
	private Date minBalanceDate0;
	private Double maxPastDue0;
	private Date maxPastDueDate0;
	private Double avgBalance0;
	private Double avgTurnover0;
	private Double avgPayments0;
	private String worstBehavioralScore0;
	private Date worstBehavioralScoreDate0;
	private String worstCreditArkScore0;
	private Date worstCreditArkScoreDate0;
	private int periods1;
	private Double maxBalance1;
	private Date maxBalanceDate1;
	private Double minBalance1;
	private Date minBalanceDate1;
	private Double maxPastDue1;
	private Date maxPastDueDate1;
	private Double avgBalance1;
	private Double avgTurnover1;
	private Double avgPayments1;
	private String worstBehavioralScore1;
	private Date worstBehavioralScoreDate1;
	private String worstCreditArkScore1;
	private Date worstCreditArkScoreDate1;
	
	// Qualitative data
	private Date qualitativeSnapshotDate;
	private Double[] qualitativeData;
	private String qualitativeScore;
	
	// External assessment data
	private Date extEssessmentDate;
	private String extAssessment;
	private String[] entityInfo;

	private double averageDaysToPay;
	private double averageDaysUnpaid;
	
	public int getContextId() {
		return contextId;
	}
	
	public void setContextId(int contextId) {
		this.contextId = contextId;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

	public String getVatNumber() {
		return vatNumber;
	}
	
	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public boolean isAffiliate() {
		return affiliate;
	}
	
	public void setAffiliate(boolean affiliate) {
		this.affiliate = affiliate;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupId) {
		this.groupName = groupId;
	}
	
	public String getIndustryId() {
		return industryId;
	}
	
	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}
	
	public String getAreaID() {
		return areaId;
	}
	
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public Date getCustomerSince() {
		return customerSince;
	}
	public void setCustomerSince(Date customerSince) {
		this.customerSince = customerSince;
	}
	
	public boolean isOptimizationExcluded() {
		return optimizationExcluded;
	}
	public void setOptimizationExcluded(boolean optimizationExcluded) {
		this.optimizationExcluded = optimizationExcluded;
	}
	
	public boolean isExtAssessmentExcluded() {
		return extAssessmentExcluded;
	}
	
	public void setExtAssessmentExcluded(boolean extAssessmentExcluded) {
		this.extAssessmentExcluded = extAssessmentExcluded;
	}

	public Date getSnapshotDate() {
		return snapshotDate;
	}

	public void setSnapshotDate(Date snapshotDate) {
		this.snapshotDate = snapshotDate;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public double getProfitMargin() {
		return profitMargin;
	}

	public void setProfitMargin(double profitMargin) {
		this.profitMargin = profitMargin;
	}

	public double[] getActivity() {
		return activity;
	}

	public void setActivity(double[] activity) {
		this.activity = activity;
	}

	public double[] getExposure() {
		return exposure;
	}

	public void setExposure(double[] exposure) {
		this.exposure = exposure;
	}

	public double[] getPastDueAmount() {
		return pastDueAmount;
	}

	public void setPastDueAmount(double[] pastDueAmount) {
		this.pastDueAmount = pastDueAmount;
	}

	public double[] getCreditMitigants() {
		return creditMitigants;
	}

	public void setCreditMitigants(double[] creditMitigants) {
		this.creditMitigants = creditMitigants;
	}

	public Double[] getCriteria() {
		return criteria;
	}

	public void setCriteria(Double[] criteria) {
		this.criteria = criteria;
	}

	public String getBehavioralScore() {
		return behavioralScore;
	}

	public void setBehavioralScore(String behavioralScore) {
		this.behavioralScore = behavioralScore;
	}

	public String getCreditArkScore() {
		return creditArkScore;
	}

	public void setCreditArkScore(String creditArkScore) {
		this.creditArkScore = creditArkScore;
	}
	
	public double getPd() {
		return pd;
	}
	
	public void setPd(double pd) {
		this.pd = pd;
	}
	
	public double[] getProvisions() {
		return provisions;
	}
	
	public void setProvisions(double[] provisions) {
		this.provisions = provisions;
	}

	public Date getPrevDate() {
		return prevDate;
	}

	public void setPrevDate(Date prevDate) {
		this.prevDate = prevDate;
	}

	public int getPeriods0() {
		return periods0;
	}

	public void setPeriods0(int periods0) {
		this.periods0 = periods0;
	}

	public Double getMaxBalance0() {
		return maxBalance0;
	}

	public void setMaxBalance0(Double maxBalance0) {
		this.maxBalance0 = maxBalance0;
	}

	public Date getMaxBalanceDate0() {
		return maxBalanceDate0;
	}

	public void setMaxBalanceDate0(Date maxBalanceDate0) {
		this.maxBalanceDate0 = maxBalanceDate0;
	}

	public Double getMinBalance0() {
		return minBalance0;
	}

	public void setMinBalance0(Double minBalance0) {
		this.minBalance0 = minBalance0;
	}

	public Date getMinBalanceDate0() {
		return minBalanceDate0;
	}

	public void setMinBalanceDate0(Date minBalanceDate0) {
		this.minBalanceDate0 = minBalanceDate0;
	}

	public Double getMaxPastDue0() {
		return maxPastDue0;
	}

	public void setMaxPastDue0(Double maxPastDue0) {
		this.maxPastDue0 = maxPastDue0;
	}

	public Date getMaxPastDueDate0() {
		return maxPastDueDate0;
	}

	public void setMaxPastDueDate0(Date maxPastDueDate0) {
		this.maxPastDueDate0 = maxPastDueDate0;
	}

	public Double getAvgBalance0() {
		return avgBalance0;
	}

	public void setAvgBalance0(Double avgBalance0) {
		this.avgBalance0 = avgBalance0;
	}

	public Double getAvgTurnover0() {
		return avgTurnover0;
	}

	public void setAvgTurnover0(Double avgPurchases0) {
		this.avgTurnover0 = avgPurchases0;
	}

	public Double getAvgPayments0() {
		return avgPayments0;
	}

	public void setAvgPayments0(Double avgPayments0) {
		this.avgPayments0 = avgPayments0;
	}

	public String getWorstBehavioralScore0() {
		return worstBehavioralScore0;
	}

	public void setWorstBehavioralScore0(String worstBehavioralScore0) {
		this.worstBehavioralScore0 = worstBehavioralScore0;
	}

	public Date getWorstBehavioralScoreDate0() {
		return worstBehavioralScoreDate0;
	}

	public void setWorstBehavioralScoreDate0(Date worstBehavioralScoreDate0) {
		this.worstBehavioralScoreDate0 = worstBehavioralScoreDate0;
	}

	public String getWorstCreditArkScore0() {
		return worstCreditArkScore0;
	}

	public void setWorstCreditArkScore0(String worstCreditArkScore0) {
		this.worstCreditArkScore0 = worstCreditArkScore0;
	}

	public Date getWorstCreditArkScoreDate0() {
		return worstCreditArkScoreDate0;
	}

	public void setWorstCreditArkScoreDate0(Date worstCreditArkScoreDate0) {
		this.worstCreditArkScoreDate0 = worstCreditArkScoreDate0;
	}

	public int getPeriods1() {
		return periods1;
	}

	public void setPeriods1(int periods1) {
		this.periods1 = periods1;
	}

	public Double getMaxBalance1() {
		return maxBalance1;
	}

	public void setMaxBalance1(Double maxBalance1) {
		this.maxBalance1 = maxBalance1;
	}

	public Date getMaxBalanceDate1() {
		return maxBalanceDate1;
	}

	public void setMaxBalanceDate1(Date maxBalanceDate1) {
		this.maxBalanceDate1 = maxBalanceDate1;
	}

	public Double getMinBalance1() {
		return minBalance1;
	}

	public void setMinBalance1(Double minBalance1) {
		this.minBalance1 = minBalance1;
	}

	public Date getMinBalanceDate1() {
		return minBalanceDate1;
	}

	public void setMinBalanceDate1(Date minBalanceDate1) {
		this.minBalanceDate1 = minBalanceDate1;
	}

	public Double getMaxPastDue1() {
		return maxPastDue1;
	}

	public void setMaxPastDue1(Double maxPastDue1) {
		this.maxPastDue1 = maxPastDue1;
	}

	public Date getMaxPastDueDate1() {
		return maxPastDueDate1;
	}

	public void setMaxPastDueDate1(Date maxPastDueDate1) {
		this.maxPastDueDate1 = maxPastDueDate1;
	}

	public Double getAvgBalance1() {
		return avgBalance1;
	}

	public void setAvgBalance1(Double avgBalance1) {
		this.avgBalance1 = avgBalance1;
	}

	public Double getAvgTurnover1() {
		return avgTurnover1;
	}

	public void setAvgTurnover1(Double avgPurchases1) {
		this.avgTurnover1 = avgPurchases1;
	}

	public Double getAvgPayments1() {
		return avgPayments1;
	}

	public void setAvgPayments1(Double avgPayments1) {
		this.avgPayments1 = avgPayments1;
	}

	public String getWorstBehavioralScore1() {
		return worstBehavioralScore1;
	}

	public void setWorstBehavioralScore1(String worstBehavioralScore1) {
		this.worstBehavioralScore1 = worstBehavioralScore1;
	}

	public Date getWorstBehavioralScoreDate1() {
		return worstBehavioralScoreDate1;
	}

	public void setWorstBehavioralScoreDate1(Date worstBehavioralScoreDate1) {
		this.worstBehavioralScoreDate1 = worstBehavioralScoreDate1;
	}

	public String getWorstCreditArkScore1() {
		return worstCreditArkScore1;
	}

	public void setWorstCreditArkScore1(String worstCreditArkScore1) {
		this.worstCreditArkScore1 = worstCreditArkScore1;
	}

	public Date getWorstCreditArkScoreDate1() {
		return worstCreditArkScoreDate1;
	}

	public void setWorstCreditArkScoreDate1(Date worstCreditArkScoreDate1) {
		this.worstCreditArkScoreDate1 = worstCreditArkScoreDate1;
	}

	public Date getQualitativeSnapshotDate() {
		return qualitativeSnapshotDate;
	}

	public void setQualitativeSnapshotDate(Date qualitativeSnapshotDate) {
		this.qualitativeSnapshotDate = qualitativeSnapshotDate;
	}

	public Double[] getQualitativeData() {
		return qualitativeData;
	}

	public void setQualitativeData(Double[] qualitativeData) {
		this.qualitativeData = qualitativeData;
	}

	public String getQualitativeScore() {
		return qualitativeScore;
	}

	public void setQualitativeScore(String qualitativeScore) {
		this.qualitativeScore = qualitativeScore;
	}

	public Date getExtAssessmentDate() {
		return extEssessmentDate;
	}

	public void setExtAssessmentDate(Date extAssessmentDate) {
		this.extEssessmentDate = extAssessmentDate;
	}

	public String getExtAssessment() {
		return extAssessment;
	}

	public void setExtAssessment(String extAssessment) {
		this.extAssessment = extAssessment;
	}

	public String[] getEntityInfo() {
		return entityInfo;
	}

	public void setEntityInfo(String[] entityInfo) {
		this.entityInfo = entityInfo;
	}

	public String getAreaId() {
		return areaId;
	}

	public Date getExtEssessmentDate() {
		return extEssessmentDate;
	}

	public void setExtEssessmentDate(Date extEssessmentDate) {
		this.extEssessmentDate = extEssessmentDate;
	}

	public double getAverageDaysToPay() {
		return averageDaysToPay;
	}

	public void setAverageDaysToPay(double averageDaysToPay) {
		this.averageDaysToPay = averageDaysToPay;
	}

	public double getAverageDaysUnpaid() {
		return averageDaysUnpaid;
	}

	public void setAverageDaysUnpaid(double averageDaysUnpaid) {
		this.averageDaysUnpaid = averageDaysUnpaid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDetails other = (CustomerDetails) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CustomerDetails{");
		sb.append("contextId=").append(contextId);
		sb.append(", customerId='").append(customerId).append('\'');
		sb.append(", portfolio='").append(portfolio).append('\'');
		sb.append(", vatNumber='").append(vatNumber).append('\'');
		sb.append(", customerName='").append(customerName).append('\'');
		sb.append(", affiliate=").append(affiliate);
		sb.append(", groupName='").append(groupName).append('\'');
		sb.append(", industryId='").append(industryId).append('\'');
		sb.append(", areaId='").append(areaId).append('\'');
		sb.append(", customerSince=").append(customerSince);
		sb.append(", optimizationExcluded=").append(optimizationExcluded);
		sb.append(", extAssessmentExcluded=").append(extAssessmentExcluded);
		sb.append(", snapshotDate=").append(snapshotDate);
		sb.append(", customerStatus='").append(customerStatus).append('\'');
		sb.append(", profitMargin=").append(profitMargin);
		sb.append(", activity=").append(Arrays.toString(activity));
		sb.append(", exposure=").append(Arrays.toString(exposure));
		sb.append(", pastDueAmount=").append(Arrays.toString(pastDueAmount));
		sb.append(", creditMitigants=").append(Arrays.toString(creditMitigants));
		sb.append(", criteria=").append(Arrays.toString(criteria));
		sb.append(", behavioralScore='").append(behavioralScore).append('\'');
		sb.append(", creditArkScore='").append(creditArkScore).append('\'');
		sb.append(", pd=").append(pd);
		sb.append(", provisions=").append(Arrays.toString(provisions));
		sb.append(", prevDate=").append(prevDate);
		sb.append(", periods0=").append(periods0);
		sb.append(", maxBalance0=").append(maxBalance0);
		sb.append(", maxBalanceDate0=").append(maxBalanceDate0);
		sb.append(", minBalance0=").append(minBalance0);
		sb.append(", minBalanceDate0=").append(minBalanceDate0);
		sb.append(", maxPastDue0=").append(maxPastDue0);
		sb.append(", maxPastDueDate0=").append(maxPastDueDate0);
		sb.append(", avgBalance0=").append(avgBalance0);
		sb.append(", avgTurnover0=").append(avgTurnover0);
		sb.append(", avgPayments0=").append(avgPayments0);
		sb.append(", worstBehavioralScore0='").append(worstBehavioralScore0).append('\'');
		sb.append(", worstBehavioralScoreDate0=").append(worstBehavioralScoreDate0);
		sb.append(", worstCreditArkScore0='").append(worstCreditArkScore0).append('\'');
		sb.append(", worstCreditArkScoreDate0=").append(worstCreditArkScoreDate0);
		sb.append(", periods1=").append(periods1);
		sb.append(", maxBalance1=").append(maxBalance1);
		sb.append(", maxBalanceDate1=").append(maxBalanceDate1);
		sb.append(", minBalance1=").append(minBalance1);
		sb.append(", minBalanceDate1=").append(minBalanceDate1);
		sb.append(", maxPastDue1=").append(maxPastDue1);
		sb.append(", maxPastDueDate1=").append(maxPastDueDate1);
		sb.append(", avgBalance1=").append(avgBalance1);
		sb.append(", avgTurnover1=").append(avgTurnover1);
		sb.append(", avgPayments1=").append(avgPayments1);
		sb.append(", worstBehavioralScore1='").append(worstBehavioralScore1).append('\'');
		sb.append(", worstBehavioralScoreDate1=").append(worstBehavioralScoreDate1);
		sb.append(", worstCreditArkScore1='").append(worstCreditArkScore1).append('\'');
		sb.append(", worstCreditArkScoreDate1=").append(worstCreditArkScoreDate1);
		sb.append(", qualitativeSnapshotDate=").append(qualitativeSnapshotDate);
		sb.append(", qualitativeData=").append(Arrays.toString(qualitativeData));
		sb.append(", qualitativeScore='").append(qualitativeScore).append('\'');
		sb.append(", extEssessmentDate=").append(extEssessmentDate);
		sb.append(", extAssessment='").append(extAssessment).append('\'');
		sb.append(", entityInfo=").append(Arrays.toString(entityInfo));
		sb.append('}');
		return sb.toString();
	}
}
