package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import org.springframework.stereotype.Component;

import eu.ark.creditark.services.creditarkservices.dto.input.ScenarioTransformatorInputDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenariosDto;
import eu.ark.creditark.services.creditarkservices.utils.ScenarioDtoUtils;

@Component("SCENARIOS")
public class ScenarioViewTransformator implements ScenarioTransformator {

	@SuppressWarnings("unchecked")
	@Override
	public ScenariosDto transform(ScenarioTransformatorInputDto input) {
		ScenariosDto response = new ScenariosDto();
		
		input.getScenarios().entrySet().forEach(entry -> {
			ScenarioDto scenario = new ScenarioDto();
			ScenarioDtoUtils.createBasicScenarioDetails(scenario, entry);
			response.getScenarios().add(scenario);
		});
		
		return response;
	}

}
