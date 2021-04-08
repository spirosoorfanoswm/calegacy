package eu.ark.creditark.services.creditarkservices.services.validation;

import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;
import org.springframework.stereotype.Component;

@Component
public interface BusinessValidatorFactory {
    public BusinessValidator getFactory(DtoTransformationType dtoTransformationType);
}
