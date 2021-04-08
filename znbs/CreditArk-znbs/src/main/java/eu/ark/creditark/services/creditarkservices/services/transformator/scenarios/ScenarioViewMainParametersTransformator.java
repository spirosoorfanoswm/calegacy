package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenariosDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUIDto;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;
import eu.ark.creditark.services.creditarkservices.utils.ScenarioDtoUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("SCENARIOS_MAIN_PARAMETERS")
public class ScenarioViewMainParametersTransformator implements ScenarioUITransformator {

	@SuppressWarnings("unchecked")
	@Override
	public List<ScenarioUIDto> transform(ScenariosDto input, ContextInfo contextInfo) {

		List<ScenarioUIDto> response = new ArrayList<>();


		for(ScenarioDto scenario:input.getScenarios()) {
			ScenarioUIDto scenarioUIDto = new ScenarioUIDto();
			ScenarioDtoUtils.attachScenarioMainParameters(scenarioUIDto, scenario);
			ScenarioDtoUtils.attachScenarioMainStatistics(scenarioUIDto, scenario, contextInfo);
			ScenarioDtoUtils.attachScenarioPortfolioParameters(scenarioUIDto, scenario, contextInfo);
			ScenarioDtoUtils.attachProspectParameters(scenarioUIDto, scenario, contextInfo);
			ScenarioDtoUtils.attachCustomerParameters(scenarioUIDto, scenario);
			response.add(scenarioUIDto);
		}

		return response;
	}


}
