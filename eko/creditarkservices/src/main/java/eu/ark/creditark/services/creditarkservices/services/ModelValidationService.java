package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.validationmodel.ModelValidationDto;
import eu.ark.creditark.services.creditarkservices.dto.validationmodel.ModelValidationPortfolioDto;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;

import java.util.List;

public interface ModelValidationService {
    public ModelValidationDto getAvailableModels(int contextId) throws DatabaseConnectionException;
}
