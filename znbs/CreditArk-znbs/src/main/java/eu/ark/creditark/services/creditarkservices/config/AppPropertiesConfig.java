package eu.ark.creditark.services.creditarkservices.config;

import java.util.ArrayList;
import java.util.List;

import eu.ark.creditark.services.creditarkservices.dto.ScenarioThresholdsDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import eu.ark.creditark.services.creditarkservices.dto.ComparisonPeriodDto;

@Component
@ConfigurationProperties("app")
public class AppPropertiesConfig {
	private List<ComparisonPeriodDto> comparisonPeriods = new ArrayList<>();
	private List<String> statistics = new ArrayList<>();
	private List<String> clientStatistics = new ArrayList<>();
	private List<String> region = new ArrayList<>();
	private String responseType;
	private String scopeType;
	private String logoutUrl;
	private String redirectUrl;
	private ScenarioThresholdsDto scenarioThresholdsDto;
	private List<String> customerSnapshotData;

	public ScenarioThresholdsDto getScenarioThresholdsDto() {
		return scenarioThresholdsDto;
	}

	public void setScenarioThresholdsDto(ScenarioThresholdsDto scenarioThresholdsDto) {
		this.scenarioThresholdsDto = scenarioThresholdsDto;
	}

	public List<String> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<String> statistics) {
		this.statistics = statistics;
	}

	public List<ComparisonPeriodDto> getComparisonPeriods() {
		return comparisonPeriods;
	}

	public void setComparisonPeriods(List<ComparisonPeriodDto> comparisonPeriods) {
		this.comparisonPeriods = comparisonPeriods;
	}

	public List<String> getClientStatistics() {
		return clientStatistics;
	}

	public void setClientStatistics(List<String> clientStatistics) {
		this.clientStatistics = clientStatistics;
	}

	public List<String> getRegion() {
		return region;
	}

	public void setRegion(List<String> region) {
		this.region = region;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getScopeType() {
		return scopeType;
	}

	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public List<String> getCustomerSnapshotData() {
		return customerSnapshotData;
	}

	public void setCustomerSnapshotData(List<String> customerSnapshotData) {
		this.customerSnapshotData = customerSnapshotData;
	}
}
