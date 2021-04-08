package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioContainerUiDto;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;
import eu.ark.creditark.services.creditarkservices.shared.ScenarioStatistics;

public interface ScenarioStatisticsToUIStatisticsTransformator {

    public <T extends Object> T transform(
            ScenarioContainerUiDto input,
            ContextInfo contextInfo,
            ScenarioStatistics scenarioStatistics);

}
