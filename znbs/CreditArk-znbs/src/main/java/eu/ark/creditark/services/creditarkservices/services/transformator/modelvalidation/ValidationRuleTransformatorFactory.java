package eu.ark.creditark.services.creditarkservices.services.transformator.modelvalidation;

import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;
import org.springframework.stereotype.Component;

@Component
public interface ValidationRuleTransformatorFactory {
    public ValidationRuleTransformator getTransformatorFactory(DtoTransformationType dtoTransformationType);
}
