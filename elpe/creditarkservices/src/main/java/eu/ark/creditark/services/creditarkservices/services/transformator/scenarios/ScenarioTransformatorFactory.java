package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import org.springframework.stereotype.Component;

import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;

@Component
public interface ScenarioTransformatorFactory {
    public ScenarioTransformator getTransformatorFactory(DtoTransformationType dtoTransformationType);
}
