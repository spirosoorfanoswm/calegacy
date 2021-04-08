package eu.ark.creditark.services.creditarkservices.services.transformator.scenarios;

import com.google.common.base.Throwables;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioContainerUiDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioMainParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioPortfolioParametersUIDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUIDto;
import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioRuntimeException;
import eu.ark.creditark.services.creditarkservices.services.validation.BusinessValidatorFactory;
import eu.ark.creditark.services.creditarkservices.services.validation.ValidatorFactory;
import eu.ark.creditark.services.creditarkservices.shared.*;
import eu.ark.creditark.services.creditarkservices.utils.DtoGenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("SCENARIOS_TO_BUS")
public class ScenarioUIToBusParamsTransformator
		implements ScenarioUIToBusTransformator {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ValidatorFactory validatorFactory;

	@Autowired
	private BusinessValidatorFactory businessValidatorFactory;

	@Override
	public ScenarioBus transform(ScenarioContainerUiDto input,
								 ContextInfo contextInfo) throws ScenarioException {
		ScenarioBus response = new ScenarioBus();
		ScenarioUIDto scenarioUIDto = input.getScenarios().get(0);

		ScenarioMainParametersUIDto mainParams = scenarioUIDto.getParameters();
		List<String> grades = (List<String>)Arrays.asList(contextInfo.getGrades());
		try {
			if(mainParams!=null && null!=mainParams.getCreditAmount()) {
				if(((Boolean)validatorFactory.getFactory(DtoTransformationType.SCENARIOS_MAIN_PARAMS_VALIDATION)
						.validate(mainParams)).booleanValue() == true) {
					ScenarioClienteleParameters scenarioClienteleParameters = new ScenarioClienteleParameters();
					scenarioClienteleParameters.setWacc(DtoGenUtils.percentageToNumber(mainParams.getWacc()));
					scenarioClienteleParameters.setRaroc(DtoGenUtils.percentageToNumber(mainParams.getRaroc()));
					scenarioClienteleParameters.setCreditAmount(DtoGenUtils.stringToInteger(mainParams.getCreditAmount()));
					scenarioClienteleParameters.setSignificantDigits(DtoGenUtils.stringToInteger(mainParams.getSignificantDigits()));
					scenarioClienteleParameters.setMinLimit(DtoGenUtils.stringToDouble(mainParams.getMinLimit()));
					scenarioClienteleParameters.setMinLimitPct(DtoGenUtils.percentageToNumber(mainParams.getMinLimitPct()));
					scenarioClienteleParameters.setWorstAcceptableScore(mainParams.getWorstAcceptableScore());
					scenarioClienteleParameters.setMaxLimitGrowth(DtoGenUtils.percentageToNumber(mainParams.getMaxLimitGrowth()));
					scenarioClienteleParameters.setMaxLimitReduction(DtoGenUtils.percentageToNumber(mainParams.getMaxLimitReduction()));
					scenarioClienteleParameters.setMinAcceptedLimitUse(DtoGenUtils.percentageToNumber(mainParams.getMinAcceptedLimitUse()));
					scenarioClienteleParameters.setMaxDso(DtoGenUtils.stringToIntegerObj(mainParams.getMaxDso()));
					scenarioClienteleParameters.setMinAcceptedRwMargin(DtoGenUtils.percentageToNumber(mainParams.getMinAcceptedRwMargin()));
					scenarioClienteleParameters.setMinLimitGrowth(DtoGenUtils.percentageToNumber(mainParams.getMinLimitGrowth()));
					scenarioClienteleParameters.setMinLimitReduction(DtoGenUtils.percentageToNumber(mainParams.getMinLimitReduction()));
					scenarioClienteleParameters.setSalesChange(DtoGenUtils.percentageToNumber(mainParams.getSalesChange()));
					scenarioClienteleParameters.setPdChange(DtoGenUtils.percentageToNumber(mainParams.getPdChange()));
					scenarioClienteleParameters.setMarginChange(DtoGenUtils.percentageToNumber(mainParams.getMarginChange()));

					try {
						businessValidatorFactory
								.getFactory(DtoTransformationType.SCENARIOS_MAIN_PARAMS_BUSINESS_VALIDATION)
								.validate(scenarioClienteleParameters, contextInfo.getScenarioThresholdsDto());
					} catch (ScenarioException e) {
						logger.info("{}", e);
						throw e;
					}


					response.scenarioParameters = scenarioClienteleParameters;
				}

			}

			if(null!=scenarioUIDto.getPortfolios()) {
				List<ScenarioPortfolioParameters> scenarioPortfolioParametersList = new ArrayList<>();
				List<Integer> scenarioPortfolioParameterIdList = new ArrayList<>();
				scenarioUIDto.getPortfolios().stream().forEach(scenarioPortfolioUIDto ->  {
					if(null != scenarioPortfolioUIDto.getParameters() && null!=scenarioPortfolioUIDto.getParameters().getMaxLimitGrowth()) {
						ScenarioPortfolioParametersUIDto scParams= scenarioPortfolioUIDto.getParameters();
						if(((Boolean)validatorFactory.getFactory(DtoTransformationType.SCENARIOS_MAIN_PORTFOLIO_VALIDATION)
								.validate(scParams)).booleanValue() == true) {
							try {
								ScenarioPortfolioParameters parameters = new ScenarioClienteleParameters();
								parameters.setMaxLimitGrowth(DtoGenUtils.percentageToNumber(scParams.getMaxLimitGrowth()));
								parameters.setMaxLimitReduction(DtoGenUtils.percentageToNumber(scParams.getMaxLimitReduction()));
								parameters.setMinLimitGrowth(DtoGenUtils.percentageToNumber(scParams.getMinLimitGrowth()));
								parameters.setMinLimitReduction(DtoGenUtils.percentageToNumber(scParams.getMinLimitReduction()));
								parameters.setMinAcceptedLimitUse(DtoGenUtils.percentageToNumber(scParams.getMinAcceptedLimitUse()));
								parameters.setMaxDso(DtoGenUtils.stringToIntegerObj(scParams.getMaxDso()));
								parameters.setMinAcceptedRwMargin(DtoGenUtils.percentageToNumber(scParams.getMinAcceptedRwMargin()));
								parameters.setSalesChange(DtoGenUtils.percentageToNumber(scParams.getSalesChange()));
								parameters.setMarginChange(DtoGenUtils.percentageToNumber(scParams.getMarginChange()));

								parameters.setPdChange(DtoGenUtils.percentageToNumber(scParams.getPdChange()));
								scenarioPortfolioParametersList.add(parameters);
								scenarioPortfolioParameterIdList.add(DtoGenUtils.stringToInteger(scenarioPortfolioUIDto.getPortfolioId()));
							} catch (Exception e) {
								Throwables.throwIfUnchecked(e);
								throw new ScenarioRuntimeException(e.getMessage());
							}


						}


					}
				});
				if(scenarioPortfolioParameterIdList.size()>0) {
					response.portfoliosParameters = scenarioPortfolioParametersList.toArray(new ScenarioPortfolioParameters[scenarioPortfolioParametersList.size()]);
					response.portfoliosIds = scenarioPortfolioParameterIdList.stream().mapToInt(x -> x.intValue()).toArray();
				}
			}

			if(null!=scenarioUIDto.getProspects()) {

				List<ScenarioProspectParameters> scenarioPortfolioParametersList = new ArrayList<>();
				List<Integer> scenarioProspectIds = new ArrayList<>();
				scenarioUIDto.getProspects().stream().forEach(scenarioProspectUIDto -> {
					if(null != scenarioProspectUIDto.getParameters() && null != scenarioProspectUIDto.getParameters().getProfitMargin()) {
						ScenarioProspectParameters parameters = new ScenarioProspectParameters();
						if(((Boolean)validatorFactory.getFactory(DtoTransformationType.SCENARIOS_MAIN_PROSPECT_VALIDATION)
								.validate(scenarioProspectUIDto.getParameters())).booleanValue() == true){
							try {
								parameters.setDescription(scenarioProspectUIDto.getParameters().getDescription());
								parameters.setCustomersNum(DtoGenUtils.stringToInteger(scenarioProspectUIDto.getParameters().getCustomersNum()));
								parameters.setPurchases(DtoGenUtils.stringToInteger(scenarioProspectUIDto.getParameters().getPurchases()));
								parameters.setMaxDso(DtoGenUtils.stringToIntegerObj(scenarioProspectUIDto.getParameters().getMaxDso()));
								if(null!=scenarioProspectUIDto.getParameters().getGrade()) {
									parameters.setGradeInx(
											grades.indexOf(scenarioProspectUIDto.getParameters().getGrade()) + 1);
								}
								parameters.setProfitMargin(DtoGenUtils.percentageToNumber(scenarioProspectUIDto.getParameters().getProfitMargin()));
								parameters.setMitigants(DtoGenUtils.stringArrayToDoubleArray(scenarioProspectUIDto.getParameters().getMitigantsValues()));
								parameters.setMinAcceptedRwMargin(DtoGenUtils.percentageToNumber(scenarioProspectUIDto.getParameters().getMinAcceptedRwMargin()));
								scenarioProspectIds.add(DtoGenUtils.stringToInteger(scenarioProspectUIDto.getParameters().getProspectId()));
								scenarioPortfolioParametersList.add(parameters);
							} catch (Exception e) {
								Throwables.throwIfUnchecked(e);
								throw new ScenarioRuntimeException(e.getMessage());
							}
						}
					}
				});

				if(scenarioProspectIds.size()>0) {
					response.prospectsParameters = scenarioPortfolioParametersList.toArray(new ScenarioProspectParameters[scenarioPortfolioParametersList.size()]);
					response.prospectsIds = scenarioProspectIds.stream().mapToInt(x -> x.intValue()).toArray();
				}
			}


			if(null != scenarioUIDto.getCustomers()) {
				List<ScenarioPortfolioParameters> scenarioCutomerParametersList = new ArrayList<>();
				List<String> customerIds = new ArrayList<>();
				scenarioUIDto.getCustomers().stream().forEach(scenarioCustomerUIDto -> {
					if(null != scenarioCustomerUIDto.getParameters() && null != scenarioCustomerUIDto.getParameters().getSalesChange()) {
						if(((Boolean)validatorFactory.getFactory(DtoTransformationType.SCENARIOS_MAIN_CUSTOMER_VALIDATION)
								.validate(scenarioCustomerUIDto.getParameters())).booleanValue() == true){
							try {
								ScenarioPortfolioParameters parameters = new ScenarioClienteleParameters();
								parameters.setMaxLimitGrowth(DtoGenUtils.percentageToNumber(scenarioCustomerUIDto.getParameters().getMaxLimitGrowth()));
								parameters.setMaxLimitReduction(DtoGenUtils.percentageToNumber(scenarioCustomerUIDto.getParameters().getMaxLimitReduction()));
								parameters.setMinLimitGrowth(DtoGenUtils.percentageToNumber(scenarioCustomerUIDto.getParameters().getMinLimitGrowth()));
								parameters.setMinLimitReduction(DtoGenUtils.percentageToNumber(scenarioCustomerUIDto.getParameters().getMinLimitReduction()));
								parameters.setMinAcceptedLimitUse(DtoGenUtils.percentageToNumber(scenarioCustomerUIDto.getParameters().getMinAcceptedLimitUse()));
								parameters.setMaxDso(DtoGenUtils.stringToIntegerObj(scenarioCustomerUIDto.getParameters().getMaxDso()));
								parameters.setMinAcceptedRwMargin(DtoGenUtils.percentageToNumber(scenarioCustomerUIDto.getParameters().getMinAcceptedRwMargin()));
								parameters.setSalesChange(DtoGenUtils.percentageToNumber(scenarioCustomerUIDto.getParameters().getSalesChange()));
								parameters.setMarginChange(DtoGenUtils.percentageToNumber(scenarioCustomerUIDto.getParameters().getMarginChange()));
								parameters.setPdChange(DtoGenUtils.percentageToNumber(scenarioCustomerUIDto.getParameters().getPdChange()));
								scenarioCutomerParametersList.add(parameters);
								customerIds.add(scenarioCustomerUIDto.getCustomerId());
							} catch (Exception e) {
								Throwables.throwIfUnchecked(e);
								throw new ScenarioRuntimeException(e.getMessage());
							}

						}

					}
				});

				if(customerIds.size()>0) {
					response.customersParameters = scenarioCutomerParametersList.toArray(new ScenarioPortfolioParameters[scenarioCutomerParametersList.size()]);
					response.customersIds = customerIds.toArray(new String[customerIds.size()]);
				}
			}

			if(null!= scenarioUIDto.getDeletedCustomers())
				response.deletedCustomersIds = scenarioUIDto.getDeletedCustomers().toArray(new String[scenarioUIDto.getDeletedCustomers().size()]);
			if(null!= scenarioUIDto.getDeletedProspects())
				response.deletedProspectsIds = scenarioUIDto.getDeletedProspects().stream().mapToInt( x -> Integer.parseInt(x)).toArray();
			response.description = scenarioUIDto.getScenarioName();
		} catch (Exception e) {
			throw  e;
		}



		return response;
	}
}
