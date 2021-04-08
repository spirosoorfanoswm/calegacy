package eu.ark.creditark.services.creditarkservices.shared;

import java.io.Serializable;
import java.util.*;

import eu.ark.creditark.services.creditarkservices.dto.ComparisonPeriodDto;
import eu.ark.creditark.services.creditarkservices.dto.ScenarioThresholdsDto;

public class ContextInfo implements Serializable {
	private static final long serialVersionUID = -5467835552979965529L;
	private Portfolio[] portfolios;
	private CustomDataMap[] qualitativeData;
	private String[] activityDescriptions;
	private String[] exposureDescriptions;
	private String[] bucketDescriptions;
	private CustomDataMap[] criteria;
	private String[] customerStatuses;
	private String[] entityInfoDescriptions;
	private String[] entityInfoFormat;
	private String[] mitigantDescriptions;
	private double[] mitigantRecoveryRates;
	private Date[] snapshotDates;
	private String[] scores;
	private String[] grades;
	private ScenarioDefaultParams defaultScenarioParams;
	private HashMap<Integer, ReportDescriptor> reportDescriptors;
	private int comparisonPeriod;
	private List<ComparisonPeriodDto> comparisonPeriods;
	private double amountDivident;
	private ScenarioThresholdsDto scenarioThresholdsDto;

	public ScenarioThresholdsDto getScenarioThresholdsDto() {
		return scenarioThresholdsDto;
	}

	public void setScenarioThresholdsDto(ScenarioThresholdsDto scenarioThresholdsDto) {
		this.scenarioThresholdsDto = scenarioThresholdsDto;
	}

	public Portfolio[] getPortfolios() {
		return portfolios;
	}

	public void setPortfolios(Portfolio[] portfolios) {
		this.portfolios = portfolios;
	}

	public CustomDataMap[] getQualitativeData() {
		return qualitativeData;
	}
	
	public void setQualitativeData(CustomDataMap[] qualitativeData) {
		this.qualitativeData = qualitativeData;
	}

	public String[] getActivityDescriptions() {
		return activityDescriptions;
	}

	public void setActivityDescriptions(String[] activityDescriptions) {
		this.activityDescriptions = activityDescriptions;
	}

	public String[] getExposureDescriptions() {
		return exposureDescriptions;
	}

	public void setExposureDescriptions(String[] exposureDescriptions) {
		this.exposureDescriptions = exposureDescriptions;
	}

	public String[] getBucketDescriptions() {
		return bucketDescriptions;
	}

	public void setBucketDescriptions(String[] bucketDescriptions) {
		this.bucketDescriptions = bucketDescriptions;
	}

	public CustomDataMap[] getCriteria() {
		return criteria;
	}

	public void setCriteria(CustomDataMap[] criteria) {
		this.criteria = criteria;
	}

	public String[] getCustomerStatuses() {
		return customerStatuses;
	}

	public void setCustomerStatuses(String[] customerStatuses) {
		this.customerStatuses = customerStatuses;
	}

	public String[] getEntityInfoDescriptions() {
		return entityInfoDescriptions;
	}

	public void setEntityInfoDescriptions(String[] entityInfoDescriptions) {
		this.entityInfoDescriptions = entityInfoDescriptions;
	}

	public String[] getEntityFormat() {
		return entityInfoFormat;
	}

	public void setEntityFormat(String[] entityInfoRightAligned) {
		this.entityInfoFormat = entityInfoRightAligned;
	}

	public String[] getMitigantDescriptions() {
		return mitigantDescriptions;
	}

	public void setMitigantDescriptions(String[] mitigantDescriptions) {
		this.mitigantDescriptions = mitigantDescriptions;
	}

	public double[] getMitigantRecoveryRates() {
		return mitigantRecoveryRates;
	}

	public void setMitigantRecoveryRates(double[] mitigantRecoveryRates) {
		this.mitigantRecoveryRates = mitigantRecoveryRates;
	}
	
	public Date[] getSnapshotDates() {
		return snapshotDates;
	}

	public void setSnapshotDates(Date[] snapshotDates) {
		this.snapshotDates = snapshotDates;
	}

	public String[] getScores() {
		return scores;
	}

	public void setScores(String[] scores) {
		this.scores = scores;
	}

	public String[] getGrades() {
		return grades;
	}

	public void setGrades(String[] grades) {
		this.grades = grades;
	}

	public ScenarioDefaultParams getDefaultScenarioParams() {
		return defaultScenarioParams;
	}

	public void setDefaultScenarioParams(ScenarioDefaultParams defaultScenarioParams) {
		this.defaultScenarioParams = defaultScenarioParams;
	}

	public  HashMap<Integer, ReportDescriptor> getReportDescriptors() {
		return reportDescriptors;
	}

	public void setReportDescriptors( HashMap<Integer, ReportDescriptor> reportDescriptors) {
		this.reportDescriptors = reportDescriptors;
	}

	public int getComparisonPeriod() {
		return comparisonPeriod;
	}

	public void setComparisonPeriod(int comparisonPeriod) {
		this.comparisonPeriod = comparisonPeriod;
	}

	public double getAmountDivident() {
		return amountDivident;
	}

	public void setAmountDivident(double amountDivident) {
		this.amountDivident = amountDivident;
	}

	public String[] getEntityInfoFormat() {
		return entityInfoFormat;
	}

	public void setEntityInfoFormat(String[] entityInfoFormat) {
		this.entityInfoFormat = entityInfoFormat;
	}

	public List<ComparisonPeriodDto> getComparisonPeriods() {
		if(Objects.isNull(this.comparisonPeriods))
			comparisonPeriods = new ArrayList<ComparisonPeriodDto>();
		return comparisonPeriods;
	}

	public void setComparisonPeriods(List<ComparisonPeriodDto> comparisonPeriods) {
		this.comparisonPeriods = comparisonPeriods;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("ContextInfo{");
		sb.append("portfolios=").append(Arrays.toString(portfolios));
		sb.append(", qualitativeData=").append(Arrays.toString(qualitativeData));
		sb.append(", activityDescriptions=").append(Arrays.toString(activityDescriptions));
		sb.append(", exposureDescriptions=").append(Arrays.toString(exposureDescriptions));
		sb.append(", bucketDescriptions=").append(Arrays.toString(bucketDescriptions));
		sb.append(", criteria=").append(Arrays.toString(criteria));
		sb.append(", customerStatuses=").append(Arrays.toString(customerStatuses));
		sb.append(", entityInfoDescriptions=").append(Arrays.toString(entityInfoDescriptions));
		sb.append(", entityInfoFormat=").append(Arrays.toString(entityInfoFormat));
		sb.append(", mitigantDescriptions=").append(Arrays.toString(mitigantDescriptions));
		sb.append(", mitigantRecoveryRates=").append(Arrays.toString(mitigantRecoveryRates));
		sb.append(", snapshotDates=").append(Arrays.toString(snapshotDates));
		sb.append(", scores=").append(Arrays.toString(scores));
		sb.append(", grades=").append(Arrays.toString(grades));
		sb.append(", defaultScenarioParams=").append(defaultScenarioParams);
		sb.append(", reportDescriptors=").append(reportDescriptors);
		sb.append(", comparisonPeriod=").append(comparisonPeriod);
		sb.append(", comparisonPeriods=").append(comparisonPeriods);
		sb.append(", amountDivident=").append(amountDivident);
		sb.append('}');
		return sb.toString();
	}
}
