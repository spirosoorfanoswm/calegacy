package eu.ark.creditark.services.creditarkservices.services.validation;

import eu.ark.creditark.services.creditarkservices.dto.ScenarioThresholdsDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUI;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;

public interface BusinessValidator {
    public <T extends Object> T validate(Object input, ScenarioThresholdsDto contextInfo) throws ScenarioException;
}
