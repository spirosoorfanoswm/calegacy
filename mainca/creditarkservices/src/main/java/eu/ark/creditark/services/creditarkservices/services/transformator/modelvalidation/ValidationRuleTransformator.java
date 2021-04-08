package eu.ark.creditark.services.creditarkservices.services.transformator.modelvalidation;

import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleTransformatorInputDto;
import eu.ark.creditark.services.creditarkservices.model.ValidationRule;
import eu.ark.creditark.services.creditarkservices.model.ValidationRules;

import java.util.List;

public interface ValidationRuleTransformator {

    public <T extends Object> T transform(ValidationRules input);
}
