package eu.ark.creditark.services.creditarkservices.dto.validationmodel;

import java.io.Serializable;
import java.util.List;

public class ModelValidationDto implements Serializable {
    private List<ModelValidationPortfolioDto> modelValidationRules;

    public List<ModelValidationPortfolioDto> getModelValidationRules() {
        return modelValidationRules;
    }

    public void setModelValidationRules(List<ModelValidationPortfolioDto> modelValidationRules) {
        this.modelValidationRules = modelValidationRules;
    }
}
