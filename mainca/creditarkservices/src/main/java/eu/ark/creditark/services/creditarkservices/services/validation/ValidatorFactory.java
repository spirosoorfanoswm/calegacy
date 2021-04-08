package eu.ark.creditark.services.creditarkservices.services.validation;

import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioContainerUiDto;
import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import org.springframework.stereotype.Component;

@Component
public interface ValidatorFactory {
    public Validator getFactory(DtoTransformationType dtoTransformationType);
}
