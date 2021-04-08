package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import eu.ark.creditark.services.creditarkservices.dto.input.ScenarioTransformatorInputDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenariosDto;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;

public interface ScenarioUITransformator {

    public <T extends Object> T transform(ScenariosDto input, ContextInfo contextInfo);
}
