package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioContainerUiDto;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;

public interface ScenarioUIToBusTransformator {

    public <T extends Object> T transform(
            ScenarioContainerUiDto input,
            ContextInfo contextInfo) throws ScenarioException;
}
