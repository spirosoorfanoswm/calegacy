package eu.ark.creditark.services.creditarkservices.services.validation;

import eu.ark.creditark.services.creditarkservices.dto.ScenarioThresholdsDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioPortfolioParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUI;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import org.springframework.stereotype.Component;

@Component("SCENARIOS_MAIN_PORTFOLIO_BUSINESS_VALIDATION")
public class ScenarioPortfolioParametersBusinessValidator implements BusinessValidator{
    @Override
    public Boolean validate(Object input, ScenarioThresholdsDto thresholds) throws ScenarioException {
        ScenarioPortfolioParametersUIDto scParams = (ScenarioPortfolioParametersUIDto)input;
        if(null == scParams)
            return false;

        return true;
    }
}
