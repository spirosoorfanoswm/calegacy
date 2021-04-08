package eu.ark.creditark.services.creditarkservices.services.validation;

import eu.ark.creditark.services.creditarkservices.dto.ScenarioThresholdsDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioMainParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUI;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import eu.ark.creditark.services.creditarkservices.services.ErrorMessageService;
import eu.ark.creditark.services.creditarkservices.shared.ScenarioClienteleParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("SCENARIOS_MAIN_PARAMS_BUSINESS_VALIDATION")
public class ScenarioMainParametersBusinessValidator implements BusinessValidator{
    @Autowired
    ErrorMessageService errorMessageService;
    @Override
    public Boolean validate(Object input, ScenarioThresholdsDto thresholds) throws ScenarioException {
        ScenarioClienteleParameters mainParams = (ScenarioClienteleParameters)input;
        List<String> errors = new ArrayList();
        if(null == mainParams )
            errors.add(errorMessageService.getMessage("scenario.parameters.empty", new String[]{}));

        if((mainParams.getCreditAmount() < thresholds.getTargetTotalCreditLimit().getMin()))
            errors.add(errorMessageService.getMessage("targetTotalCreditLimit.min", new String[]{Double.toString(thresholds.getTargetTotalCreditLimit().getMin())}));

        if((mainParams.getMinAcceptedRwMargin() < thresholds.getMinimumAcceptableRiskWeightedMargin().getMin()))
            errors.add(errorMessageService.getMessage("minimumAcceptableRiskWeightedMargin.min", new String[]{Double.toString(thresholds.getMinimumAcceptableRiskWeightedMargin().getMin()*100)+"%"}));
        if((mainParams.getMinAcceptedRwMargin() > thresholds.getMinimumAcceptableRiskWeightedMargin().getMax()))
            errors.add(errorMessageService.getMessage("minimumAcceptableRiskWeightedMargin.max", new String[]{Double.toString(thresholds.getMinimumAcceptableRiskWeightedMargin().getMax()*100)+"%"}));

        if(null!=mainParams.getSalesChange()) {
            if((mainParams.getSalesChange() < thresholds.getSalesChange().getMin()))
                errors.add(errorMessageService.getMessage("salesChange.min", new String[]{Double.toString(thresholds.getSalesChange().getMin()*100)+"%"}));
            if((mainParams.getSalesChange() > thresholds.getSalesChange().getMax()))
                errors.add(errorMessageService.getMessage("salesChange.max", new String[]{Double.toString(thresholds.getSalesChange().getMax()*100)+"%"}));
        }
        if(null!=mainParams.getMarginChange()) {
            if((mainParams.getMarginChange() < thresholds.getChangeInProfitMargins().getMin()))
                errors.add(errorMessageService.getMessage("changeInProfitMargins.min", new String[]{Double.toString(thresholds.getChangeInProfitMargins().getMin()*100)+"%"}));
            if((mainParams.getMarginChange() > thresholds.getChangeInProfitMargins().getMax()))
                errors.add(errorMessageService.getMessage("changeInProfitMargins.max", new String[]{Double.toString(thresholds.getChangeInProfitMargins().getMax()*100)+"%"}));
        }

        if(null!=mainParams.getPdChange()) {
            if((mainParams.getPdChange() < thresholds.getChangeInDefaultProbabilities().getMin()))
                errors.add(errorMessageService.getMessage("changeInDefaultProbabilities.min", new String[]{Double.toString(thresholds.getChangeInDefaultProbabilities().getMin()*100)+"%"}));
            if((mainParams.getPdChange() > thresholds.getChangeInDefaultProbabilities().getMax()))
                errors.add(errorMessageService.getMessage("changeInDefaultProbabilities.max", new String[]{Double.toString(thresholds.getChangeInDefaultProbabilities().getMax()*100)+"%"}));
        }

        if(null!=mainParams.getMaxDso()) {
            if((mainParams.getMaxDso() < thresholds.getMaxDso().getMin()))
                errors.add(errorMessageService.getMessage("maxDso.min", new String[]{Double.toString(thresholds.getMaxDso().getMin())}));
        }

        if(null!=mainParams.getMinAcceptedLimitUse()) {
            if((mainParams.getMinAcceptedLimitUse() < thresholds.getMinimumAcceptedLimitUse().getMin()))
                errors.add(errorMessageService.getMessage("minimumAcceptedLimitUse.min", new String[]{Double.toString(thresholds.getMinimumAcceptedLimitUse().getMin()*100)+"%"}));
            if((mainParams.getMinAcceptedLimitUse() > thresholds.getMinimumAcceptedLimitUse().getMax()))
                errors.add(errorMessageService.getMessage("minimumAcceptedLimitUse.max", new String[]{Double.toString(thresholds.getMinimumAcceptedLimitUse().getMax()*100)+"%"}));
        }

        if(null!=mainParams.getMaxLimitGrowth()) {
            if((mainParams.getMaxLimitGrowth() < thresholds.getMaximumLimitGrowth().getMin()))
                errors.add(errorMessageService.getMessage("minimumAcceptedLimitUse.min", new String[]{Double.toString(thresholds.getMaximumLimitGrowth().getMin()*100)+"%"}));
        }

        if(null!=mainParams.getMaxLimitReduction()) {
            if((mainParams.getMaxLimitReduction() < thresholds.getMaximumLimitReduction().getMin()))
                errors.add(errorMessageService.getMessage("maximumLimitReduction.min", new String[]{Double.toString(thresholds.getMaximumLimitReduction().getMin()*100)+"%"}));
            if((mainParams.getMaxLimitReduction() > thresholds.getMaximumLimitReduction().getMax()))
                errors.add(errorMessageService.getMessage("maximumLimitReduction.max", new String[]{Double.toString(thresholds.getMaximumLimitReduction().getMax()*100)+"%"}));
        }

        if(null!=mainParams.getMinLimitGrowth()) {
            if((mainParams.getMinLimitGrowth() < thresholds.getMinimumLimitGrowth().getMin()))
                errors.add(errorMessageService.getMessage("minimumLimitGrowth.min", new String[]{Double.toString(thresholds.getMinimumLimitGrowth().getMin()*100)+"%"}));
        }

        if((mainParams.getRaroc() < thresholds.getReturnOnEquity().getMin()))
                errors.add(errorMessageService.getMessage("returnOnEquity.min", new String[]{Double.toString(thresholds.getReturnOnEquity().getMin()*100)+"%"}));

        if((mainParams.getWacc() < thresholds.getWacc().getMin()))
            errors.add(errorMessageService.getMessage("wacc.min", new String[]{Double.toString(thresholds.getWacc().getMin()*100)+"%"}));
        if((mainParams.getWacc() > thresholds.getWacc().getMax()))
            errors.add(errorMessageService.getMessage("wacc.max", new String[]{Double.toString(thresholds.getWacc().getMax()*100)+"%"}));

        if(null!=mainParams.getMinLimit())
            if((mainParams.getMinLimit() < thresholds.getMinAcceptedLimitAmount().getMin()))
                errors.add(errorMessageService.getMessage("minAcceptedLimitAmount.min", new String[]{Double.toString(thresholds.getMinAcceptedLimitAmount().getMin())}));

        if(null!=mainParams.getSignificantDigits()) {
            if((mainParams.getSignificantDigits() < thresholds.getSingn().getMin()))
                errors.add(errorMessageService.getMessage("singn.min", new String[]{Double.toString(thresholds.getTargetTotalCreditLimit().getMin())}));
            if((mainParams.getSignificantDigits() > thresholds.getSingn().getMax()))
                errors.add(errorMessageService.getMessage("singn.max", new String[]{Double.toString(thresholds.getSingn().getMax())}));
        }
        if(errors.size()>0) {
            String error = errors.stream().map( n -> n.toString() )
                    .collect( Collectors.joining( " " ) );
            throw new ScenarioException(error);
        }
        return true;
    }
}
