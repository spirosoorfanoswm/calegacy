package eu.ark.creditark.services.creditarkservices.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import eu.ark.creditark.services.creditarkservices.dto.KeyValueDto;
import eu.ark.creditark.services.creditarkservices.dto.KeyValueMultipleDto;
import eu.ark.creditark.services.creditarkservices.dto.KeyValuesDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioCustomerParametersDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioCustomerStatisticsDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioPartStatisticsDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioPortfolioParametersDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioPortfolioStatisticsDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenarioProspectParametersDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.*;
import eu.ark.creditark.services.creditarkservices.shared.*;

public class ScenarioDtoUtils {
	
	private ScenarioDtoUtils() {}
	
	public static void createBasicScenarioDetails(ScenarioDto input, Entry<Integer, Scenario> entry) {
		Scenario scenario = entry.getValue();
		input.setScenarioId(entry.getKey());
		input.setDescription(scenario.getDescription());
		input.setOwnerName(scenario.getOwnerName());
		input.setOwnerLogin(scenario.getOwnerLogin());
		input.setLocked(scenario.isLocked());
		input.setPending(!scenario.hasValidResult());
		
		if(null!=scenario.getParameters()) {
			if(!Objects.isNull(scenario.getParameters().getClienteleParameters()))
				createClienteleParameters(input, scenario.getParameters().getClienteleParameters());
			
			if(!Objects.isNull(scenario.getParameters().getPortfoliosParams()))
				scenario.getParameters().getPortfoliosParams().entrySet().forEach(portfolioEntry -> createScenarioParameters(input, portfolioEntry.getKey(), portfolioEntry.getValue()));

			if(!Objects.isNull(scenario.getParameters().getProspectsParams()))
				scenario.getParameters().getProspectsParams().entrySet().forEach(prospectParameter -> createScenarioProspectsParameters(input, prospectParameter.getKey(), prospectParameter.getValue()));
			
			if(!Objects.isNull(scenario.getParameters().getCustomersParams()))
				scenario.getParameters().getCustomersParams().entrySet().forEach(customerParemeter -> createCustomersParameters(input, customerParemeter.getKey(), customerParemeter.getValue()));
			if(!Objects.isNull(scenario.getStatistics()))
				attachStatistics(input, scenario.getStatistics());
		}
		
	}
	
	public static void createClienteleParameters(ScenarioDto input, ScenarioClienteleParameters scenarioClienteleParameters) {

		input.getParameters().getClienteleParameters().setCreditAmount(scenarioClienteleParameters.getCreditAmount());
		input.getParameters().getClienteleParameters().setMinLimit(scenarioClienteleParameters.getMinLimit());
		input.getParameters().getClienteleParameters().setMinLimitPct(scenarioClienteleParameters.getMinLimitPct());
		input.getParameters().getClienteleParameters().setRaroc(scenarioClienteleParameters.getRaroc());
		input.getParameters().getClienteleParameters().setSignificantDigits(scenarioClienteleParameters.getSignificantDigits());
		input.getParameters().getClienteleParameters().setWacc(scenarioClienteleParameters.getWacc());
		input.getParameters().getClienteleParameters().setMarginChange(scenarioClienteleParameters.getMarginChange());
		input.getParameters().getClienteleParameters().setMaxDso(scenarioClienteleParameters.getMaxDso());
		input.getParameters().getClienteleParameters().setMaxLimitGrowth(scenarioClienteleParameters.getMaxLimitGrowth());
		input.getParameters().getClienteleParameters().setMaxLimitReduction(scenarioClienteleParameters.getMaxLimitReduction());
		input.getParameters().getClienteleParameters().setWorstAcceptableScore(scenarioClienteleParameters.getWorstAcceptableScore());
		input.getParameters().getClienteleParameters().setUserComments(scenarioClienteleParameters.getUserComments());
		input.getParameters().getClienteleParameters().setSalesChange(scenarioClienteleParameters.getSalesChange());
		input.getParameters().getClienteleParameters().setPortfolioId(scenarioClienteleParameters.getPortfolioId());
		input.getParameters().getClienteleParameters().setPdChange(scenarioClienteleParameters.getPdChange());
		input.getParameters().getClienteleParameters().setMinLimitGrowth(scenarioClienteleParameters.getMinLimitGrowth());
		input.getParameters().getClienteleParameters().setMinAcceptedLimitUse(scenarioClienteleParameters.getMinAcceptedLimitUse());
		input.getParameters().getClienteleParameters().setMinAcceptedRwMargin(scenarioClienteleParameters.getMinAcceptedRwMargin());
		input.getParameters().getClienteleParameters().setMinLimitReduction(scenarioClienteleParameters.getMinLimitReduction());
	}
	
	public static void createScenarioParameters(
			ScenarioDto input, 
			int portfolioId, 
			ScenarioPortfolioParameters portfolioParameter) {
		input.getParameters().getPortfoliosParameters().add(new ScenarioPortfolioParametersDto(
				portfolioId, 
				portfolioParameter.getMaxLimitGrowth(), 
				portfolioParameter.getMaxLimitReduction(), 
				portfolioParameter.getMinLimitGrowth(), 
				portfolioParameter.getMinLimitReduction(), 
				portfolioParameter.getMinAcceptedLimitUse(), 
				portfolioParameter.getMaxDso(), 
				portfolioParameter.getMinAcceptedRwMargin(), 
				portfolioParameter.getSalesChange(), 
				portfolioParameter.getMarginChange(), 
				portfolioParameter.getPdChange(), 
				portfolioParameter.getWorstAcceptableScore(), 
				portfolioParameter.getUserComments()));
	}
	
	public static void createScenarioProspectsParameters(ScenarioDto input, int prospectId, ScenarioProspectParameters scenarioProspectParameter) {
		input.getParameters().getProspectsParameters().add(new ScenarioProspectParametersDto(prospectId, 
				scenarioProspectParameter.getDescription(), 
				scenarioProspectParameter.getCustomersNum(), 
				scenarioProspectParameter.getPurchases(), 
				scenarioProspectParameter.getMaxDso(), 
				scenarioProspectParameter.getGradeInx(), 
				scenarioProspectParameter.getProfitMargin(), 
				scenarioProspectParameter.getMitigants(), 
				scenarioProspectParameter.getMinAcceptedRwMargin(), 
				scenarioProspectParameter.getComments()
		));

	}
	
	public static void createCustomersParameters(ScenarioDto input, String customerId, ScenarioCustomerParameters scenarioCustomerParameter) {
		ScenarioCustomerParametersDto scenarioCustomerParametersDto = new ScenarioCustomerParametersDto(
				customerId, 
				scenarioCustomerParameter.getCustomerName());
		scenarioCustomerParametersDto.setParameters(new ScenarioPortfolioParametersDto(
				scenarioCustomerParameter.getParameters().getPortfolioId(), 
				scenarioCustomerParameter.getParameters().getMaxLimitGrowth(), 
				scenarioCustomerParameter.getParameters().getMaxLimitReduction(), 
				scenarioCustomerParameter.getParameters().getMinLimitGrowth(), 
				scenarioCustomerParameter.getParameters().getMinLimitReduction(), 
				scenarioCustomerParameter.getParameters().getMinAcceptedLimitUse(), 
				scenarioCustomerParameter.getParameters().getMaxDso(), 
				scenarioCustomerParameter.getParameters().getMinAcceptedRwMargin(), 
				scenarioCustomerParameter.getParameters().getSalesChange(), 
				scenarioCustomerParameter.getParameters().getMarginChange(), 
				scenarioCustomerParameter.getParameters().getPdChange(), 
				scenarioCustomerParameter.getParameters().getWorstAcceptableScore(), 
				scenarioCustomerParameter.getParameters().getUserComments()));
		input.getParameters().getCustomersParameters().add(scenarioCustomerParametersDto);
	}
	
	public static void attachStatistics(ScenarioDto input, ScenarioStatistics statistics) {
		if(!Objects.isNull(statistics.getStats()))
			input.getStatistics().setStats(attachScenarioPartStatistics(statistics.getStats()));
		
		if(!Objects.isNull(statistics.getClienteleDistribution()))
			Arrays.stream(statistics.getClienteleDistribution()).forEach(stats -> input.getStatistics().getClienteleDistrib().add(attachScenarioPartStatistics(stats)));
		
		if(!Objects.isNull(statistics.getProspectsDistribution()))
			Arrays.stream(statistics.getProspectsDistribution()).forEach(stats -> input.getStatistics().getProspectsDistrib().add(attachScenarioPartStatistics(stats)));
		
		if(!Objects.isNull(statistics.getPortfoliosStats()))
			statistics.getPortfoliosStats().entrySet().stream().forEach(portfolioStat ->
					input.getStatistics().getPortfoliosStats().add(
							attachScenarioPortfolioStatisticsDto(portfolioStat.getKey(), portfolioStat.getValue())));
		
		if(!Objects.isNull(statistics.getProspectsStats())) {
			statistics.getProspectsStats().entrySet().stream().forEach(prospectStat -> {
				input.getStatistics().getProspectsStats().add(createScenarioCustomerStatisticsDto(prospectStat.getKey(), prospectStat.getValue()));
			});
		}
	}
	
	public static ScenarioCustomerStatisticsDto createScenarioCustomerStatisticsDto(int prospectId, ScenarioCustomerStatistics statistics) {
		ScenarioCustomerStatisticsDto response = new ScenarioCustomerStatisticsDto();
		response.setProspectId(prospectId);
		response.setFundingCost(statistics.getFundingCost());
		response.setLgd(statistics.getLgd());
		response.setMaxLimit(statistics.getMaxLimit());
		response.setPd(statistics.getPd());
		response.setProjectedPurchases(statistics.getProjectedPurchases());
		response.setProposedLimit(statistics.getProposedLimit());
		response.setRwMargin(statistics.getRwMargin());
		response.setWorkingCapital(statistics.getWorkingCapital());
		response.setEffectiveDso(statistics.getProjectedPurchases() == 0 ? 0 : statistics.getProposedLimit() * 360 / statistics.getProjectedPurchases());
		return response;
	}
	private static ScenarioPortfolioStatisticsDto attachScenarioPortfolioStatisticsDto(int portfolioId, ScenarioPortfolioStatistics statistics) {
		ScenarioPortfolioStatisticsDto response = new ScenarioPortfolioStatisticsDto();
		response.setPortfolioId(portfolioId);
		if(!Objects.isNull(statistics.getPartStatistics())) 
			response.setPartStatistics(attachScenarioPartStatistics(statistics.getPartStatistics()));		
		if(!Objects.isNull(statistics.getStatDistribution())) 
			Arrays.stream(statistics.getStatDistribution()).forEach(statDistribution -> response.getStatDistribution().add(attachScenarioPartStatistics(statDistribution)));
		return response;
	}
	
	private static ScenarioPartStatisticsDto attachScenarioPartStatistics(ScenarioPartStatistics statistics) {
		ScenarioPartStatisticsDto response = new ScenarioPartStatisticsDto();
		response.setCurrentLimits(statistics.getCurrentLimits());
		response.setCustomersNum(statistics.getCustomersNum());
		response.setFundingCost(statistics.getFundingCost());
		response.setMeanRwm(statistics.getMeanRwm());
		response.setProfitMargin(statistics.getProfitMargin());
		response.setProjectedTurnover(statistics.getProjectedTurnover());
		response.setProposedLimits(statistics.getProposedLimits());
		response.setProposedProvisions(statistics.getProposedProvisions());
		response.setProposedWc(statistics.getProposedWc());
		response.setScenarioCustomersNum(statistics.getScenarioCustomersNum());
		response.setTurnover(statistics.getTurnover());		
		return response;
	}

	public static void attachScenarioMainParameters(ScenarioUIDto scenarioUIDto,
													ScenarioDto scenario) {
		scenarioUIDto.setScenarioId(scenario.getScenarioId());
		scenarioUIDto.setScenarioName(scenario.getDescription());
		scenarioUIDto.setScenarioOwnerName(scenario.getOwnerName());
		scenarioUIDto.setScenarioOwnerlogin(scenario.getOwnerLogin());
		scenarioUIDto.setParameters(new ScenarioMainParametersUIDto());
		scenario.getParameters().getClienteleParameters().getCreditAmount();
		scenarioUIDto.setPending(scenario.isPending());
		if(null!=scenario.getParameters() && null!=scenario.getParameters().getClienteleParameters()) {
			scenarioUIDto.getParameters().setCreditAmount(
					DtoGenUtils.numberToString(
							scenario.getParameters().getClienteleParameters()
									.getCreditAmount()));

			scenarioUIDto.getParameters().setMarginChange(DtoGenUtils.numberToStringPercentage(
					scenario.getParameters().getClienteleParameters()
							.getMarginChange()));

			scenarioUIDto.getParameters().setMaxDso(DtoGenUtils.numberToStringObj(
					scenario.getParameters().getClienteleParameters()
							.getMaxDso()));

			scenarioUIDto.getParameters().setMaxLimitGrowth(DtoGenUtils.numberToStringPercentage(
					scenario.getParameters().getClienteleParameters()
							.getMaxLimitGrowth()));

			scenarioUIDto.getParameters().setMaxLimitReduction(DtoGenUtils.numberToStringPercentage(
					scenario.getParameters().getClienteleParameters()
							.getMaxLimitReduction()));

			scenarioUIDto.getParameters().setMinAcceptedLimitUse(DtoGenUtils.numberToStringPercentage(
					scenario.getParameters().getClienteleParameters()
							.getMinAcceptedLimitUse()));

			scenarioUIDto.getParameters().setMinAcceptedRwMargin(DtoGenUtils.numberToStringPercentage(
					scenario.getParameters().getClienteleParameters()
							.getMinAcceptedRwMargin()));

			scenarioUIDto.getParameters().setMinLimit(DtoGenUtils.numberToString(
					scenario.getParameters().getClienteleParameters()
							.getMinLimit()));

			scenarioUIDto.getParameters().setMinLimitGrowth(DtoGenUtils.numberToStringPercentage(
					scenario.getParameters().getClienteleParameters()
							.getMinLimitGrowth()));

			scenarioUIDto.getParameters().setMinLimitPct(DtoGenUtils.numberToStringPercentage(
					scenario.getParameters().getClienteleParameters()
							.getMinLimitPct()));

			scenarioUIDto.getParameters().setMinLimitReduction(DtoGenUtils.numberToStringPercentage(
					scenario.getParameters().getClienteleParameters()
							.getMinLimitReduction()));

			scenarioUIDto.getParameters().setPdChange(DtoGenUtils.numberToStringPercentage(
					scenario.getParameters().getClienteleParameters()
							.getPdChange()));

			scenarioUIDto.getParameters().setSalesChange(DtoGenUtils.numberToStringPercentage(
					scenario.getParameters().getClienteleParameters()
							.getSalesChange()));

			scenarioUIDto.getParameters().setRaroc(DtoGenUtils.numberToStringPercentage(
					scenario.getParameters().getClienteleParameters()
							.getRaroc()));

			scenarioUIDto.getParameters().setSignificantDigits(DtoGenUtils.numberToString(
					scenario.getParameters().getClienteleParameters()
							.getSignificantDigits()));

			scenarioUIDto.getParameters().setWacc(DtoGenUtils.numberToStringPercentage(
					scenario.getParameters().getClienteleParameters()
							.getWacc()));

			scenarioUIDto.getParameters().setWorstAcceptableScore(
					scenario.getParameters().getClienteleParameters()
							.getWorstAcceptableScore());
		}

	}

	public static void attachScenarioMainStatistics(ScenarioUIDto scenarioUIDto,
													ScenarioDto scenario,
													ContextInfo contextInfo) {
		if(null!=scenarioUIDto.getStatistics() && null!=scenarioUIDto.getStatistics().getScenarioStatistics()
				&& null!=scenario.getStatistics().getStats()) {
			scenarioUIDto.getStatistics().getScenarioStatistics().add(new KeyValueMultipleDto(
					"Statistic",
					"Statistic", new String[]{"Snapshot","Scenario"}));
			scenarioUIDto.getStatistics().getScenarioStatistics().add(new KeyValueMultipleDto(
					"Number of customers",
					"Number of customers", new String[]{DtoGenUtils.numberToString(scenario.getStatistics().getStats().getCustomersNum()),
					DtoGenUtils.numberToString(scenario.getStatistics().getStats().getScenarioCustomersNum())}));
			scenarioUIDto.getStatistics().getScenarioStatistics().add(new KeyValueMultipleDto(
					"Turnover",
					"Turnover", new String[]{
					DtoGenUtils.numberToString(scenario.getStatistics().getStats().getTurnover()),
					DtoGenUtils.numberToString(scenario.getStatistics().getStats().getProjectedTurnover())}));
			scenarioUIDto.getStatistics().getScenarioStatistics().add(new KeyValueMultipleDto(
					"Limits",
					"Limits", new String[]{
					DtoGenUtils.numberToString(scenario.getStatistics().getStats().getCurrentLimits()),
					DtoGenUtils.numberToString(scenario.getStatistics().getStats().getProposedLimits())}));

			scenarioUIDto.getStatistics().getScenarioStatistics().add(new KeyValueMultipleDto(
					"Funding cost (as a percentage on turnover)",
					"Funding cost (as a percentage on turnover)", new String[]{
					"",
					DtoGenUtils.numberToStringPercentage(scenario.getStatistics().getStats().getFundingCost())}));

			scenarioUIDto.getStatistics().getScenarioStatistics().add(new KeyValueMultipleDto(
					"Mean Profit Margin",
					"Mean Profit Margin", new String[]{
					"",
					DtoGenUtils.numberToStringPercentage(scenario.getStatistics().getStats().getProfitMargin())}));

			scenarioUIDto.getStatistics().getScenarioStatistics().add(new KeyValueMultipleDto(
					"Mean Risk Weighted Profit Margin",
					"Mean Risk Weighted Profit Margin", new String[]{
					"",
					DtoGenUtils.numberToStringPercentage(scenario.getStatistics().getStats().getMeanRwm())}));

			scenarioUIDto.getStatistics().getScenarioStatistics().add(new KeyValueMultipleDto(
					"Proposed provisions",
					"Proposed provisions", new String[]{
					"",
					DtoGenUtils.numberToString(scenario.getStatistics().getStats().getProposedProvisions())}));

			scenarioUIDto.getStatistics().getScenarioStatistics().add(new KeyValueMultipleDto(
					"Proposed capital cushion",
					"Proposed capital cushion", new String[]{
					"",
					DtoGenUtils.numberToString(scenario.getStatistics().getStats().getProposedWc())}));

			IntStream.range(0, contextInfo.getScores().length).forEach(index -> {

				KeyValuesDto kv = new KeyValuesDto();
				kv.setDescription(contextInfo.getScores()[index]);
				kv.getValues().add(new KeyValueDto("Current", DtoGenUtils.numberToString(scenario.getStatistics().getClienteleDistrib().get(index).getCurrentLimits())));
				kv.getValues().add(new KeyValueDto("Proposed", DtoGenUtils.numberToString(scenario.getStatistics().getClienteleDistrib().get(index).getProposedLimits())));
				scenarioUIDto.getStatistics().getLimits().add(kv);

				kv = new KeyValuesDto();
				kv.setDescription(contextInfo.getScores()[index]);
				kv.getValues().add(new KeyValueDto("Provisions", DtoGenUtils.numberToString(scenario.getStatistics().getClienteleDistrib().get(index).getProposedProvisions())));
				kv.getValues().add(new KeyValueDto("Cushion", DtoGenUtils.numberToString(scenario.getStatistics().getClienteleDistrib().get(index).getProposedWc())));
				scenarioUIDto.getStatistics().getAllowances().add(kv);


				kv = new KeyValuesDto();
				kv.setDescription(contextInfo.getScores()[index]);
				kv.getValues().add(new KeyValueDto("Risk Weighted Margin",
						DtoGenUtils.numberToStringPercentageNoPostfix(
								scenario.getStatistics().getClienteleDistrib().get(index).getMeanRwm())));
				kv.getValues().add(new KeyValueDto("Credit Risk Cost",
						DtoGenUtils.numberToStringPercentageNoPostfix(
								scenario.getStatistics().getClienteleDistrib().get(index).getProfitMargin()-
								scenario.getStatistics().getClienteleDistrib().get(index).getMeanRwm())));
				scenarioUIDto.getStatistics().getMargins().add(kv);


			});

		}


	}

	public static void attachScenarioPortfolioParameters(ScenarioUIDto scenarioUIDto,
												 ScenarioDto scenario,
														 ContextInfo contextInfo) {
		if(null== scenario.getParameters() && null == scenario.getParameters().getPortfoliosParameters()) return;

		Map<Integer, ScenarioPortfolioParametersDto> portfolioScenMap = scenario.getParameters()
				.getPortfoliosParameters().stream().collect(Collectors.toMap(
				ScenarioPortfolioParametersDto::getPortfolioId, Function.identity()
		));
		Map<Integer, Portfolio> portfolioMap = Arrays.stream(contextInfo.getPortfolios()).collect(Collectors.toMap(
				Portfolio::getPortfolioId, Function.identity()
		));
		Map<Integer, ScenarioPortfolioStatisticsDto> portfolioStatisticsMap = new HashMap<>();
		if(null!=scenario.getStatistics() && null!=scenario.getStatistics().getPortfoliosStats()) {
			portfolioStatisticsMap = scenario.getStatistics().getPortfoliosStats().stream().collect(Collectors.toMap(
					ScenarioPortfolioStatisticsDto::getPortfolioId, Function.identity())
			);
		}

		Map<Integer, ScenarioPortfolioStatisticsDto> finalPortfolioStatisticsMap = portfolioStatisticsMap;
		portfolioMap.entrySet().stream().forEach(portfolio -> {
			ScenarioPortfolioUIDto portfolioUIDto = new ScenarioPortfolioUIDto();
			portfolioUIDto.setPortfolioId(DtoGenUtils.numberToString(portfolio.getKey()));
			portfolioUIDto.setPortfolioName(portfolio.getValue().getDescription());
			portfolioUIDto.getParameters().setMaxDsoCli(scenarioUIDto.getParameters().getMaxDso());
			portfolioUIDto.getParameters().setMaxLimitGrowthCli(scenarioUIDto.getParameters().getMaxLimitGrowth());
			portfolioUIDto.getParameters().setMaxLimitReductionCli(scenarioUIDto.getParameters().getMaxLimitReduction());
			portfolioUIDto.getParameters().setMinAcceptedLimitUseCli(scenarioUIDto.getParameters().getMinAcceptedLimitUse());
			portfolioUIDto.getParameters().setMinAcceptedRwMarginCli(scenarioUIDto.getParameters().getMinAcceptedRwMargin());
			portfolioUIDto.getParameters().setMarginChangeCli(scenarioUIDto.getParameters().getMarginChange());
			portfolioUIDto.getParameters().setSalesChangeCli(scenarioUIDto.getParameters().getSalesChange());
			portfolioUIDto.getParameters().setPdChangeCli(scenarioUIDto.getParameters().getPdChange());
			portfolioUIDto.getParameters().setMinLimitReductionCli(scenarioUIDto.getParameters().getMinLimitReduction());
			portfolioUIDto.getParameters().setMinLimitGrowthCli(scenarioUIDto.getParameters().getMinLimitReduction());
			portfolioUIDto.getParameters().setWorstAcceptableScoreCli(scenarioUIDto.getParameters().getWorstAcceptableScore());

			if(null != portfolioScenMap.get(portfolio.getKey())) {
				ScenarioPortfolioParametersDto params = portfolioScenMap.get(portfolio.getKey());
				portfolioUIDto.getParameters().setMaxDso(
						DtoGenUtils.numberToStringObj(params.getMaxDso()));
				portfolioUIDto.getParameters().setMaxLimitGrowth(
						DtoGenUtils.numberToStringPercentage(params.getMaxLimitGrowth()));
				portfolioUIDto.getParameters().setMaxLimitReduction(
						DtoGenUtils.numberToStringPercentage(params.getMaxLimitReduction()));
				portfolioUIDto.getParameters().setMinAcceptedLimitUse(
						DtoGenUtils.numberToStringPercentage(params.getMinAcceptedLimitUse()));
				portfolioUIDto.getParameters().setMinAcceptedRwMargin(
						DtoGenUtils.numberToStringPercentage(params.getMinAcceptedRwMargin()));
				portfolioUIDto.getParameters().setMarginChange(
						DtoGenUtils.numberToStringPercentage(params.getMarginChange()));
				portfolioUIDto.getParameters().setSalesChange(
						DtoGenUtils.numberToStringPercentage(params.getSalesChange()));
				portfolioUIDto.getParameters().setPdChange(
						DtoGenUtils.numberToStringPercentage(params.getPdChange()));
				portfolioUIDto.getParameters().setMinLimitReduction(
						DtoGenUtils.numberToStringPercentage(params.getMinLimitReduction()));
				portfolioUIDto.getParameters().setMinLimitGrowth(
						DtoGenUtils.numberToStringPercentage(params.getMinLimitGrowth()));
				portfolioUIDto.getParameters().setWorstAcceptableScore(
						params.getWorstAcceptableScore());
			}
			if(null != finalPortfolioStatisticsMap && null!=finalPortfolioStatisticsMap.get(portfolio.getKey())) {
				attachScenarioPortfolioStatistics(finalPortfolioStatisticsMap.get(portfolio.getKey()), portfolioUIDto, contextInfo);
			}
			scenarioUIDto.getPortfolios().add(portfolioUIDto);
		});

	}

	private static void attachScenarioPortfolioStatistics(
														  ScenarioPortfolioStatisticsDto stats,
														  ScenarioPortfolioUIDto portfolioUIDto,
														  ContextInfo contextInfo) {
		if(null == stats) return;
		if(null!=stats.getPartStatistics()) {
			portfolioUIDto.getStatistics().getPortfolioStatistics().add(new KeyValueMultipleDto(
					"Statistic",
					"Statistic", new String[]{"Snapshot","Scenario"}));

			portfolioUIDto.getStatistics().getPortfolioStatistics().add(new KeyValueMultipleDto(
					"Number of customers",
					"Number of customers", new String[]{DtoGenUtils.numberToString(stats.getPartStatistics().getCustomersNum()),
					DtoGenUtils.numberToString(stats.getPartStatistics().getScenarioCustomersNum())}));
			portfolioUIDto.getStatistics().getPortfolioStatistics().add(new KeyValueMultipleDto(
					"Turnover",
					"Turnover", new String[]{
					DtoGenUtils.numberToString(stats.getPartStatistics().getTurnover()),
					DtoGenUtils.numberToString(stats.getPartStatistics().getProjectedTurnover())}));
			portfolioUIDto.getStatistics().getPortfolioStatistics().add(new KeyValueMultipleDto(
					"Limits",
					"Limits", new String[]{
					DtoGenUtils.numberToString(stats.getPartStatistics().getCurrentLimits()),
					DtoGenUtils.numberToString(stats.getPartStatistics().getProposedLimits())}));

			portfolioUIDto.getStatistics().getPortfolioStatistics().add(new KeyValueMultipleDto(
					"Funding cost (as a percentage on turnover)",
					"Funding cost (as a percentage on turnover)", new String[]{
					"",
					DtoGenUtils.numberToStringPercentage(stats.getPartStatistics().getFundingCost())}));

			portfolioUIDto.getStatistics().getPortfolioStatistics().add(new KeyValueMultipleDto(
					"Mean Profit Margin",
					"Mean Profit Margin", new String[]{
					"",
					DtoGenUtils.numberToStringPercentage(stats.getPartStatistics().getProfitMargin())}));

			portfolioUIDto.getStatistics().getPortfolioStatistics().add(new KeyValueMultipleDto(
					"Mean Risk Weighted Profit Margin",
					"Mean Risk Weighted Profit Margin", new String[]{
					"",
					DtoGenUtils.numberToStringPercentage(stats.getPartStatistics().getMeanRwm())}));

			portfolioUIDto.getStatistics().getPortfolioStatistics().add(new KeyValueMultipleDto(
					"Proposed provisions",
					"Proposed provisions", new String[]{
					"",
					DtoGenUtils.numberToString(stats.getPartStatistics().getProposedProvisions())}));

			portfolioUIDto.getStatistics().getPortfolioStatistics().add(new KeyValueMultipleDto(
					"Proposed capital cushion",
					"Proposed capital cushion", new String[]{
					"",
					DtoGenUtils.numberToString(stats.getPartStatistics().getProposedWc())}));
		}


		if(null!=stats.getStatDistribution())
			IntStream.range(0, contextInfo.getScores().length).forEach(index -> {
				KeyValuesDto kv = new KeyValuesDto();
				kv.setDescription(contextInfo.getScores()[index]);
				kv.getValues().add(new KeyValueDto("Current", DtoGenUtils.numberToString(stats.getStatDistribution().get(index).getCurrentLimits())));
				kv.getValues().add(new KeyValueDto("Proposed", DtoGenUtils.numberToString(stats.getStatDistribution().get(index).getProposedLimits())));
				portfolioUIDto.getStatistics().getLimits().add(kv);

				kv = new KeyValuesDto();
				kv.setDescription(contextInfo.getScores()[index]);
				kv.getValues().add(new KeyValueDto("Provisions", DtoGenUtils.numberToString(stats.getStatDistribution().get(index).getProposedProvisions())));
				kv.getValues().add(new KeyValueDto("Cushion", DtoGenUtils.numberToString(stats.getStatDistribution().get(index).getProposedWc())));
				portfolioUIDto.getStatistics().getAllowances().add(kv);

				kv = new KeyValuesDto();
				kv.setDescription(contextInfo.getScores()[index]);
				kv.getValues().add(new KeyValueDto("Risk Weighted Margin",
						DtoGenUtils.numberToStringPercentageNoPostfix(stats.getStatDistribution().get(index).getMeanRwm())));
				kv.getValues().add(new KeyValueDto("Credit Risk Cost",
						DtoGenUtils.numberToStringPercentageNoPostfix(stats.getStatDistribution().get(index).getProfitMargin()-
								stats.getStatDistribution().get(index).getMeanRwm())));
				portfolioUIDto.getStatistics().getMargins().add(kv);
			});
	}


	public static void attachProspectParameters(ScenarioUIDto scenarioUIDto,
												ScenarioDto scenario,
												ContextInfo contextInfo) {
		if(null==scenario.getParameters()  || null == scenario.getParameters().getProspectsParameters()) return;
		else {
			Map<Integer, ScenarioProspectParametersDto> prospectParameters = scenario.getParameters().getProspectsParameters().stream().collect(
					Collectors.toMap(ScenarioProspectParametersDto::getProspectId, Function.identity())
			);
			Map<Integer, ScenarioCustomerStatisticsDto> prospectStats = null;
			if(null!=scenario.getStatistics() && null != scenario.getStatistics().getProspectsStats()) {
				prospectStats = scenario.getStatistics().getProspectsStats().stream().collect(
						Collectors.toMap(ScenarioCustomerStatisticsDto::getProspectId, Function.identity())
				);
			}
			Map<Integer, ScenarioCustomerStatisticsDto> finalProspectStats = prospectStats;
			prospectParameters.entrySet().stream().forEach(parameter -> {
				ScenarioProspectUIDto prospect = new ScenarioProspectUIDto();
				prospect.getParameters().setProspectId(DtoGenUtils.numberToString(parameter.getKey()));
				prospect.getParameters().setCustomersNum(DtoGenUtils.numberToString(parameter.getValue().getCustomersNum()));
				prospect.getParameters().setDescription(parameter.getValue().getDescription());
				if(parameter.getValue().getGradeInx()>0)
					prospect.getParameters().setGrade(contextInfo.getGrades()[parameter.getValue().getGradeInx()-1]);
				prospect.getParameters().setMaxDso(DtoGenUtils.numberToStringObj(parameter.getValue().getMaxDso()));
				prospect.getParameters().setMinAcceptedRwMargin(DtoGenUtils.numberToStringPercentage(parameter.getValue().getMinAcceptedRwMargin()));
				prospect.getParameters().setMitigants(contextInfo.getMitigantDescriptions());
				prospect.getParameters().setMitigantsValues(DtoGenUtils.numberArrayToStringArray(parameter.getValue().getMitigants()));
				prospect.getParameters().setProfitMargin(DtoGenUtils.numberToStringPercentage(parameter.getValue().getProfitMargin()));
				prospect.getParameters().setPurchases(DtoGenUtils.numberToString(parameter.getValue().getPurchases()));
				prospect.getParameters().setGradeInx(""+parameter.getValue().getGradeInx());
				attachProspectStatistics(prospect, finalProspectStats.get(parameter.getValue().getProspectId()));
				scenarioUIDto.getProspects().add(prospect);
			});
		}

	}

	public static void attachProspectStatistics(ScenarioProspectUIDto prospect,
												ScenarioCustomerStatisticsDto stats) {
		if(null== prospect || null== prospect.getStatistics() || null == prospect.getStatistics().getProspectStatistics() ||
		null==stats) return;
		prospect.getStatistics().getProspectStatistics().add(new KeyValueMultipleDto(
				"Description",
				"Description", new String[]{"Value"}));
		prospect.getStatistics().getProspectStatistics().add(new KeyValueMultipleDto(
				"Projected turnover",
				"Projected turnover", new String[]{
						DtoGenUtils.numberToString(stats.getProjectedPurchases())}));
		prospect.getStatistics().getProspectStatistics().add(new KeyValueMultipleDto(
				"Maximum acceptable limit",
				"Maximum acceptable limit", new String[]{
						DtoGenUtils.numberToString(stats.getMaxLimit())}));
		prospect.getStatistics().getProspectStatistics().add(new KeyValueMultipleDto(
				"Proposed limit",
				"Proposed limit", new String[]{
						DtoGenUtils.numberToString(stats.getProposedLimit())}));
		prospect.getStatistics().getProspectStatistics().add(new KeyValueMultipleDto(
				"Effective Days Sales Outstanding (DSO)",
				"Effective Days Sales Outstanding (DSO)", new String[]{
						DtoGenUtils.numberToString(stats.getEffectiveDso())}));
		prospect.getStatistics().getProspectStatistics().add(new KeyValueMultipleDto(
				"One year Probability of Default (PD)",
				"One year Probability of Default (PD)", new String[]{
						DtoGenUtils.numberToStringPercentage(stats.getPd())}));
		prospect.getStatistics().getProspectStatistics().add(new KeyValueMultipleDto(
				"Loss Given Default (LGD)",
				"Loss Given Default (LGD)", new String[]{
						DtoGenUtils.numberToStringPercentage(stats.getLgd())}));
		prospect.getStatistics().getProspectStatistics().add(new KeyValueMultipleDto(
				"Funding cost (as a percentage on turnover)",
				"Funding cost (as a percentage on turnover)", new String[]{
						DtoGenUtils.numberToStringPercentage(stats.getFundingCost())}));
		prospect.getStatistics().getProspectStatistics().add(new KeyValueMultipleDto(
				"Risk Weighted profit Margin (RWM)",
				"Risk Weighted profit Margin (RWM)", new String[]{
						DtoGenUtils.numberToStringPercentage(stats.getRwMargin())}));
		prospect.getStatistics().getProspectStatistics().add(new KeyValueMultipleDto(
				"Required capital cushion",
				"Required capital cushion", new String[]{
						DtoGenUtils.numberToString(stats.getWorkingCapital())}));

	}


	public static void attachCustomerParameters(ScenarioUIDto scenarioUIDto,
												ScenarioDto scenario) {
		if(null == scenario.getParameters() || null == scenario.getParameters().getCustomersParameters()) return;
		scenario.getParameters().getCustomersParameters().stream().forEach(customer -> {
			ScenarioCustomerUIDto dto = new ScenarioCustomerUIDto();
			dto.setCustomerId(customer.getCustomerId());
			dto.setCustomerName(customer.getCustomerName());
			dto.getParameters().setMarginChangeCli(scenarioUIDto.getParameters().getMarginChange());
			dto.getParameters().setMaxDsoCli(scenarioUIDto.getParameters().getMaxDso());
			dto.getParameters().setMaxLimitGrowthCli(scenarioUIDto.getParameters().getMaxLimitGrowth());
			dto.getParameters().setMaxLimitReductionCli(scenarioUIDto.getParameters().getMaxLimitReduction());
			dto.getParameters().setMinAcceptedLimitUseCli(scenarioUIDto.getParameters().getMinAcceptedLimitUse());
			dto.getParameters().setMinAcceptedRwMarginCli(scenarioUIDto.getParameters().getMinAcceptedRwMargin());
			dto.getParameters().setMinLimitGrowthCli(scenarioUIDto.getParameters().getMinLimitGrowth());
			dto.getParameters().setMinLimitReductionCli(scenarioUIDto.getParameters().getMinLimitReduction());
			dto.getParameters().setSalesChangeCli(scenarioUIDto.getParameters().getSalesChange());
			if(null!=customer.getParameters()) {
				dto.getParameters().setMarginChange(DtoGenUtils.numberToStringPercentage(customer.getParameters().getMarginChange()));
				dto.getParameters().setMaxDso(DtoGenUtils.numberToStringObj(customer.getParameters().getMaxDso()));
				dto.getParameters().setMaxLimitGrowth(DtoGenUtils.numberToStringPercentage(customer.getParameters().getMaxLimitGrowth()));
				dto.getParameters().setMaxLimitReduction(DtoGenUtils.numberToStringPercentage(customer.getParameters().getMaxLimitReduction()));
				dto.getParameters().setMinAcceptedLimitUse(DtoGenUtils.numberToStringPercentage(customer.getParameters().getMinAcceptedLimitUse()));
				dto.getParameters().setMinAcceptedRwMargin(DtoGenUtils.numberToStringPercentage(customer.getParameters().getMinAcceptedRwMargin()));
				dto.getParameters().setMinLimitGrowth(DtoGenUtils.numberToStringPercentage(customer.getParameters().getMinLimitGrowth()));
				dto.getParameters().setMinLimitReduction(DtoGenUtils.numberToStringPercentage(customer.getParameters().getMinLimitReduction()));
				dto.getParameters().setSalesChange(DtoGenUtils.numberToStringPercentage(customer.getParameters().getSalesChange()));
				dto.getParameters().setPdChange(DtoGenUtils.numberToStringPercentage(customer.getParameters().getPdChange()));
			}


			scenarioUIDto.getCustomers().add(dto);
		});
	}
}
