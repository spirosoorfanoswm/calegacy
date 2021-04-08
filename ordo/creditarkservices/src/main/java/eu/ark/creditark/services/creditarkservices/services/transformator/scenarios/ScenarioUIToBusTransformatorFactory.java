package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;
import org.springframework.stereotype.Component;

@Component
public interface ScenarioUIToBusTransformatorFactory {
    public ScenarioUIToBusTransformator getTransformatorFactory(
            DtoTransformationType dtoTransformationType);
}
