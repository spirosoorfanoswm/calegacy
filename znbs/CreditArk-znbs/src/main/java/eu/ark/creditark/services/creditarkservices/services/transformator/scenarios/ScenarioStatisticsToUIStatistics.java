package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioPortfolioParametersDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioPortfolioStatisticsDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenariosDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioContainerUiDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioMainParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioPortfolioParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUIDto;
import eu.ark.creditark.services.creditarkservices.shared.*;
import eu.ark.creditark.services.creditarkservices.utils.DtoGenUtils;
import eu.ark.creditark.services.creditarkservices.utils.ScenarioDtoUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component("SCENARIOS_STATISTICS_TO_UI_STATISTICS")
public class ScenarioStatisticsToUIStatistics
		implements ScenarioStatisticsToUIStatisticsTransformator {




	@Override
	public ScenarioContainerUiDto transform(ScenarioContainerUiDto input,
											ContextInfo contextInfo,
											ScenarioStatistics scenarioStatistics) {
		ScenarioDto scenarioDto = new ScenarioDto();
		ScenarioUIDto scenarioUIDto = input.getScenarios().get(0);
		ScenarioDtoUtils.attachStatistics(scenarioDto, scenarioStatistics);
		ScenariosDto scenariosDto = new ScenariosDto();
		scenariosDto.getScenarios().add(scenarioDto);

		ScenarioDtoUtils.attachScenarioMainStatistics(scenarioUIDto, scenarioDto, contextInfo);


		return null;
	}
}
