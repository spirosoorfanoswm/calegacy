package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.validationmodel.ModelValidationDto;
import eu.ark.creditark.services.creditarkservices.dto.validationmodel.ModelValidationPortfolioDto;
import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.repository.ModelValidationRepository;
import eu.ark.creditark.services.creditarkservices.services.transformator.clientele.ClienteleTransformatorFactory;
import eu.ark.creditark.services.creditarkservices.services.transformator.modelvalidation.ValidationRuleTransformatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelValidationServiceImpl implements ModelValidationService {
    @Autowired
    ValidationRuleTransformatorFactory validationRuleTransformatorFactory;

    @Autowired
    ModelValidationRepository modelValidationRepository;

    @Autowired
    private UtilService utilService;

    @Override
    public ModelValidationDto getAvailableModels(int contextId) throws DatabaseConnectionException {
        String schema = utilService.getSchema((short)contextId);

        return  validationRuleTransformatorFactory.getTransformatorFactory(DtoTransformationType.VALIDATION_RULE_LIST)
                .transform(modelValidationRepository.getAvailableModels(contextId, schema));
    }
}
