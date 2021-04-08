package eu.ark.creditark.services.creditarkservices.dto.scenario;

import java.io.Serializable;

public class ScenarioDto implements Serializable{

	private static final long serialVersionUID = -7497896936107528519L;
	private int scenarioId;
	private String ownerLogin;
	private String ownerName;
	private String description;
	private boolean locked;
	private boolean pending;
	private ScenarioParametersDto parameters;
	private ScenarioStatisticsDto statistics;
	
	
	public ScenarioStatisticsDto getStatistics() {
		if(null == this.statistics)
			this.statistics = new ScenarioStatisticsDto();
		return statistics;
	}
	public void setStatistics(ScenarioStatisticsDto statistics) {
		this.statistics = statistics;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public int getScenarioId() {
		return scenarioId;
	}
	public void setScenarioId(int scenarioId) {
		this.scenarioId = scenarioId;
	}
	public String getOwnerLogin() {
		return ownerLogin;
	}
	public void setOwnerLogin(String ownerLogin) {
		this.ownerLogin = ownerLogin;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ScenarioParametersDto getParameters() {
		if(null == this.parameters) 
			this.parameters = new ScenarioParametersDto();
		return parameters;
	}
	public void setParameters(ScenarioParametersDto parameters) {
		this.parameters = parameters;
	}

	public boolean isPending() {
		return pending;
	}

	public void setPending(boolean pending) {
		this.pending = pending;
	}
}
