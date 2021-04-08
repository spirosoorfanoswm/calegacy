package eu.ark.creditark.services.creditarkservices.api;

import eu.ark.creditark.services.creditarkservices.dto.validationmodel.ModelValidationDto;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.services.ModelValidationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/model")
@Api(value = "/model", description = "Operations on modeling")
@PreAuthorize("hasAnyAuthority('ROLE_MONITOR')")
@CrossOrigin(origins = "*")
public class ModelValidationController {

    @Autowired
    private ModelValidationService modelValidationService;
    @GetMapping

    @ApiOperation(value = "Retrieves all available model validation",
            response = ModelValidationDto.class,
            responseContainer = "ResponseEntity")
    public ResponseEntity<ModelValidationDto> getAvailableModels(
            Authentication authentication,
            @RequestParam(name = "contextId") int contextId)
            throws DatabaseConnectionException {

        return ResponseEntity.ok(modelValidationService.getAvailableModels(contextId));
    }
}
