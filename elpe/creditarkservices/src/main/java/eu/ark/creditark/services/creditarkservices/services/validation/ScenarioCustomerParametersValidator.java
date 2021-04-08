package eu.ark.creditark.services.creditarkservices.services.validation;

import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.*;
import eu.ark.creditark.services.creditarkservices.shared.ScenarioPortfolioParameters;
import org.springframework.stereotype.Component;

@Component("SCENARIOS_MAIN_CUSTOMER_VALIDATION")
public class ScenarioCustomerParametersValidator implements Validator{
    @Override
    public Boolean validate(ScenarioUI input) {
        ScenarioCustomerParametersUIDto params = (ScenarioCustomerParametersUIDto)input;
        if(null == params)
            return false;
        return !(null == params.getMaxLimitGrowth() ||
                null == params.getMaxLimitReduction() ||
                null == params.getMinLimitGrowth() ||
                null == params.getMinLimitReduction() ||
                null == params.getMinAcceptedLimitUse() ||
                null == params.getMaxDso() ||
                null == params.getMinAcceptedRwMargin());
    }
}
