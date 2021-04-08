package eu.ark.creditark.services.creditarkservices.services.validation;

import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioContainerUiDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioMainParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioProspectParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUI;
import org.springframework.stereotype.Component;

@Component("SCENARIOS_MAIN_PROSPECT_VALIDATION")
public class ScenarioProspectParametersValidator implements Validator{
    @Override
    public Boolean validate(ScenarioUI input) {
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
