package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioContainerUiDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioMainParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioPortfolioParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUIDto;
import eu.ark.creditark.services.creditarkservices.shared.*;
import eu.ark.creditark.services.creditarkservices.utils.DtoGenUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("SCENARIOS_TEMPLATE")
public class ScenarioTemplateTransformator
		implements ScenarioUIToBusTransformator {


	@Override
	public ScenarioContainerUiDto transform(ScenarioContainerUiDto input,
											ContextInfo contextInfo) {
		ScenarioContainerUiDto response = new ScenarioContainerUiDto();




		return response;
	}
}
