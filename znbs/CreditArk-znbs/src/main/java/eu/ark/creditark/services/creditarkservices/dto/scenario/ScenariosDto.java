package eu.ark.creditark.services.creditarkservices.dto.scenario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScenariosDto implements Serializable{

	private static final long serialVersionUID = -3789121079751949244L;
	private List<ScenarioDto> scenarios;

	public List<ScenarioDto> getScenarios() {
		if(null == this.scenarios) 
			this.scenarios = new ArrayList<>();
		return scenarios;
	}

	public void setScenarios(List<ScenarioDto> scenarios) {
		this.scenarios = scenarios;
	}
	

}
