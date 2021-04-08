package eu.ark.creditark.services.creditarkservices.services.validation;

import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.*;
import org.springframework.stereotype.Component;

@Component("SCENARIOS_MAIN_PORTFOLIO_VALIDATION")
public class ScenarioPortfolioParametersValidator implements Validator{
    @Override
    public Boolean validate(ScenarioUI input) {
        ScenarioPortfolioParametersUIDto scParams = (ScenarioPortfolioParametersUIDto)input;
        if(null == scParams)
            return false;
        return !(null == scParams.getMaxLimitGrowth() ||
                    null == scParams.getMaxLimitReduction() ||
                    null == scParams.getMinLimitGrowth() ||
                    null == scParams.getMinLimitReduction() ||
                    null == scParams.getMinAcceptedLimitUse() ||
                    null == scParams.getMinAcceptedRwMargin()
        );
    }
}
