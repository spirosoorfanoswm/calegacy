package eu.ark.creditark.services.creditarkservices.shared;

import java.util.LinkedHashMap;

public class SnapshotBus extends StatisticsBus {
	private static final long serialVersionUID = 5619679550394615655L;
	private LinkedHashMap<Integer, Scenario> scenarios;

	public LinkedHashMap<Integer, Scenario> getScenarios() {
		return scenarios;
	}

	public void setScenarios(LinkedHashMap<Integer, Scenario> scenarios) {
		this.scenarios = scenarios;
	}
}
