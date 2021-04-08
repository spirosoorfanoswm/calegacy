package eu.ark.creditark.services.creditarkservices.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDetailsDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private CustomerDetailsSnapshotDto customerDetailsSnapshotDto;
	private CustomerDetailsStatisticsDto customerDetailsStatisticsDto;
	private CustomerDetailsBehavioralDto customerDetailsBehavioralDto;
	private CustomerDetailsQualitiveDto customerDetailsQualitiveDto;
	private CustomerDetailsExternalDataDto customerDetailsExternalDataDto;
	private CustomerDetailsIFRSDto customerDetailsIFRSDto;
	private CustomerScenarioStatisticsDto customerScenarioStatisticsDto;

	public CustomerScenarioStatisticsDto getCustomerScenarioStatisticsDto() {
		if(null == this.customerScenarioStatisticsDto) this.customerScenarioStatisticsDto = new CustomerScenarioStatisticsDto();
		return customerScenarioStatisticsDto;
	}

	public void setCustomerScenarioStatisticsDto(CustomerScenarioStatisticsDto customerScenarioStatisticsDto) {
		this.customerScenarioStatisticsDto = customerScenarioStatisticsDto;
	}

	public CustomerDetailsSnapshotDto getCustomerDetailsSnapshotDto() {
		return customerDetailsSnapshotDto;
	}

	public void setCustomerDetailsSnapshotDto(CustomerDetailsSnapshotDto customerDetailsSnapshotDto) {
		this.customerDetailsSnapshotDto = customerDetailsSnapshotDto;
	}

	public CustomerDetailsStatisticsDto getCustomerDetailsStatisticsDto() {
		return customerDetailsStatisticsDto;
	}

	public void setCustomerDetailsStatisticsDto(CustomerDetailsStatisticsDto customerDetailsStatisticsDto) {
		this.customerDetailsStatisticsDto = customerDetailsStatisticsDto;
	}

	public CustomerDetailsBehavioralDto getCustomerDetailsBehavioralDto() {
		return customerDetailsBehavioralDto;
	}

	public void setCustomerDetailsBehavioralDto(CustomerDetailsBehavioralDto customerDetailsBehavioralDto) {
		this.customerDetailsBehavioralDto = customerDetailsBehavioralDto;
	}

	public CustomerDetailsQualitiveDto getCustomerDetailsQualitiveDto() {
		return customerDetailsQualitiveDto;
	}

	public void setCustomerDetailsQualitiveDto(CustomerDetailsQualitiveDto customerDetailsQualitiveDto) {
		this.customerDetailsQualitiveDto = customerDetailsQualitiveDto;
	}

	public CustomerDetailsExternalDataDto getCustomerDetailsExternalDataDto() {
		return customerDetailsExternalDataDto;
	}

	public void setCustomerDetailsExternalDataDto(CustomerDetailsExternalDataDto customerDetailsExternalDataDto) {
		this.customerDetailsExternalDataDto = customerDetailsExternalDataDto;
	}

	public CustomerDetailsIFRSDto getCustomerDetailsIFRSDto() {
		return customerDetailsIFRSDto;
	}

	public void setCustomerDetailsIFRSDto(CustomerDetailsIFRSDto customerDetailsIFRSDto) {
		this.customerDetailsIFRSDto = customerDetailsIFRSDto;
	}
/*private String customerStatus;
	private double balance;
	private double creditLimit;
	private double proposedLimit;
	private double profitMargin;
	private String score;	
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
	private List<KeyValueDto> exposures;
	private List<KeyValueDto> activities;
	private String behavioralScore;
	private String externalScore;
	private String qualitativeScore;
	private String overallScore;
	private KeyValuesDto buckets;
	private KeyValuesDto mitigants;
	private List<KeyValueMultipleDto> statistics;
	private KeyValuesDto behavioral;
	private Date externalAssesmentDate;
	private String externalAssesment;
	private KeyValuesDto externalData;	
	private Date qualitiveSnapshotDate;
	private String qualitiveScore;
	private KeyValuesDto qualitiveData;	
	private KeyValuesDto ifrs;


	
	public KeyValuesDto getIfrs() {
		if(null == this.ifrs) 
			this.ifrs = new KeyValuesDto();
		return ifrs;
	}
	public void setIfrs(KeyValuesDto ifrs) {
		this.ifrs = ifrs;
	}
	public Date getQualitiveSnapshotDate() {
		return qualitiveSnapshotDate;
	}
	public void setQualitiveSnapshotDate(Date qualitiveSnapshotDate) {
		this.qualitiveSnapshotDate = qualitiveSnapshotDate;
	}
	public String getQualitiveScore() {
		return qualitiveScore;
	}
	public void setQualitiveScore(String qualitiveScore) {
		this.qualitiveScore = qualitiveScore;
	}
	public KeyValuesDto getQualitiveData() {
		if(null == this.qualitiveData) 
			this.qualitiveData = new KeyValuesDto();
		return qualitiveData;
	}
	public void setQualitiveData(KeyValuesDto qualitiveData) {
		this.qualitiveData = qualitiveData;
	}
	public Date getExternalAssesmentDate() {
		return externalAssesmentDate;
	}
	public void setExternalAssesmentDate(Date externalAssesmentDate) {
		this.externalAssesmentDate = externalAssesmentDate;
	}
	public String getExternalAssesment() {
		return externalAssesment;
	}
	public void setExternalAssesment(String externalAssesment) {
		this.externalAssesment = externalAssesment;
	}
	public KeyValuesDto getExternalData() {
		if(null == this.externalData) 
			this.externalData = new KeyValuesDto();
		return externalData;
	}
	public void setExternalData(KeyValuesDto externalData) {
		this.externalData = externalData;
	}
	public KeyValuesDto getBehavioral() {
		if(null == this.behavioral) 
			this.behavioral = new KeyValuesDto();
		return behavioral;
	}
	public void setBehavioral(KeyValuesDto behavioral) {
		this.behavioral = behavioral;
	}
	public List<KeyValueMultipleDto> getStatistics() {
		if(null == this.statistics) 
			this.statistics = new ArrayList<>();
		return statistics;
	}
	public void setStatistics(List<KeyValueMultipleDto> statistics) {
		this.statistics = statistics;
	}
	public KeyValuesDto getMitigants() {
		if(null == this.mitigants) 
			this.mitigants = new KeyValuesDto();
		return mitigants;
	}
	public void setMitigants(KeyValuesDto mitigants) {
		this.mitigants = mitigants;
	}
	public KeyValuesDto getBuckets() {
		if(null == this.buckets) 
			this.buckets = new KeyValuesDto();
		return buckets;
	}
	public void setBuckets(KeyValuesDto buckets) {
		this.buckets = buckets;
	}
	public List<KeyValueDto> getActivities() {
		if(null == this.activities)
			this.activities = new ArrayList<KeyValueDto>();
		return activities;
	}
	public void setActivities(List<KeyValueDto> activities) {
		this.activities = activities;
	}
	public String getBehavioralScore() {
		return behavioralScore;
	}
	public void setBehavioralScore(String behavioralScore) {
		this.behavioralScore = behavioralScore;
	}
	public String getExternalScore() {
		return externalScore;
	}
	public void setExternalScore(String externalScore) {
		this.externalScore = externalScore;
	}
	public String getQualitativeScore() {
		return qualitativeScore;
	}
	public void setQualitativeScore(String qualitativeScore) {
		this.qualitativeScore = qualitativeScore;
	}
	public String getOverallScore() {
		return overallScore;
	}
	public void setOverallScore(String overallScore) {
		this.overallScore = overallScore;
	}
	public double getProfitMargin() {
		return profitMargin;
	}
	public void setProfitMargin(double profitMargin) {
		this.profitMargin = profitMargin;
	}
	public List<KeyValueDto> getExposures() {
		if(null == this.exposures)
			this.exposures = new ArrayList<>();
		return exposures;
	}
	public void setExposures(List<KeyValueDto> exposures) {
		this.exposures = exposures;
	}
	public String getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	public double getProposedLimit() {
		return proposedLimit;
	}
	public void setProposedLimit(double proposedLimit) {
		this.proposedLimit = proposedLimit;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
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
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getIndustryId() {
		return industryId;
	}
	public void setIndustryId(String industryId) {
		this.industryId = industryId;
	}
	public String getAreaId() {
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
	}*/
}
