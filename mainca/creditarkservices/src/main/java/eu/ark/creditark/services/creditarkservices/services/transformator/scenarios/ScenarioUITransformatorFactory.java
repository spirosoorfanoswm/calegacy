package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;
import org.springframework.stereotype.Component;

@Component
public interface ScenarioUITransformatorFactory {
    public ScenarioUITransformator getTransformatorFactory(DtoTransformationType dtoTransformationType);
}
