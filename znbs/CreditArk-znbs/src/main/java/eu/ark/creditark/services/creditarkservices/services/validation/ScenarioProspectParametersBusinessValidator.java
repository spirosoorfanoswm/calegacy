package eu.ark.creditark.services.creditarkservices.services.validation;

import eu.ark.creditark.services.creditarkservices.dto.ScenarioThresholdsDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioProspectParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUI;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import org.springframework.stereotype.Component;

@Component("SCENARIOS_MAIN_PROSPECT_BUSINESS_VALIDATION")
public class ScenarioProspectParametersBusinessValidator implements BusinessValidator{
    @Override
    public Boolean validate(Object input, ScenarioThresholdsDto thresholds) throws ScenarioException {
        ScenarioProspectParametersUIDto params = (ScenarioProspectParametersUIDto)input;
        if(null == params)
            return false;
        return !(null == params.getDescription() ||
                    null == params.getCustomersNum() ||
                    null == params.getPurchases() ||
                    null == params.getGradeInx() ||
                    null == params.getProfitMargin() ||
                    null == params.getMitigantsValues() ||
                    null == params.getMinAcceptedRwMargin());
    }
}
