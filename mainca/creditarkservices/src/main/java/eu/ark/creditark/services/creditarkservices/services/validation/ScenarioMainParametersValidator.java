package eu.ark.creditark.services.creditarkservices.services.validation;

import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioContainerUiDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioMainParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUI;
import org.springframework.stereotype.Component;

@Component("SCENARIOS_MAIN_PARAMS_VALIDATION")
public class ScenarioMainParametersValidator implements Validator{
    @Override
    public Boolean validate(ScenarioUI input) {
        ScenarioMainParametersUIDto mainParams = (ScenarioMainParametersUIDto)input;
        if(null == mainParams )
            return false;

        return !(
                null == mainParams.getWacc() ||
                null == mainParams.getRaroc() ||
                null == mainParams.getCreditAmount() ||
                null == mainParams.getSignificantDigits() ||
                null == mainParams.getMinLimit() ||
                null == mainParams.getMinLimitPct() ||
                null == mainParams.getWorstAcceptableScore() ||
                null == mainParams.getMaxLimitGrowth() ||
                null == mainParams.getMaxLimitReduction() ||
                null == mainParams.getMinAcceptedLimitUse() ||
                null == mainParams.getMinAcceptedRwMargin() ||
                null == mainParams.getMinLimitGrowth() ||
                null == mainParams.getMinLimitReduction()) ;
    }
}
