package eu.ark.creditark.services.creditarkservices.dto.input;

import java.util.Date;
import java.util.Map;

import eu.ark.creditark.services.creditarkservices.shared.Scenario;

public class ScenarioTransformatorInputDto {
	private Date snapshotDate;
	private Map<Integer, Scenario> scenarios;

	public ScenarioTransformatorInputDto(Date snapshotDate, Map<Integer, Scenario> scenarios) {
		this.snapshotDate = snapshotDate;
		this.scenarios = scenarios;
	}

	public Date getSnapshotDate() {
		return snapshotDate;
	}

	public void setSnapshotDate(Date snapshotDate) {
		this.snapshotDate = snapshotDate;
	}

	public Map<Integer, Scenario> getScenarios() {
		return scenarios;
	}

	public void setScenarios(Map<Integer, Scenario> scenarios) {
		this.scenarios = scenarios;
	}
	
}
