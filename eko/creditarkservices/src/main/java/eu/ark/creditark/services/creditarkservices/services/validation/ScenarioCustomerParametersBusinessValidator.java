package eu.ark.creditark.services.creditarkservices.services.validation;

import eu.ark.creditark.services.creditarkservices.dto.ScenarioThresholdsDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioCustomerParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUI;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;
import org.springframework.stereotype.Component;

@Component("SCENARIOS_MAIN_CUSTOMER_BUSINESS_VALIDATION")
public class ScenarioCustomerParametersBusinessValidator implements BusinessValidator{
    @Override
    public Boolean validate(Object input, ScenarioThresholdsDto thresholds) throws ScenarioException {
        ScenarioCustomerParametersUIDto params = (ScenarioCustomerParametersUIDto)input;
        if(null == params)
            return false;
        return !(null == params.getMaxLimitGrowth() ||
                null == params.getMaxLimitReduction() ||
                null == params.getMinLimitGrowth() ||
                null == params.getMinLimitReduction() ||
                null == params.getMinAcceptedLimitUse() ||
                null == params.getMaxDso() ||
                null == params.getMinAcceptedRwMargin()); //||
               // null == params.getSalesChange() ||
               // null == params.getMarginChange() ||
               // null == params.getPdChange();
    }
}
