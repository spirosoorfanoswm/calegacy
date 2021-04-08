package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import eu.ark.creditark.services.creditarkservices.dto.input.ScenarioTransformatorInputDto;

public interface ScenarioTransformator {

    public <T extends Object> T transform(ScenarioTransformatorInputDto input);
}
