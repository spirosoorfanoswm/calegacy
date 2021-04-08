package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.input.*;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.*;
import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import eu.ark.creditark.services.creditarkservices.optimizer.LimitsOptimizer;
import eu.ark.creditark.services.creditarkservices.repository.ScenarioRepository;
import eu.ark.creditark.services.creditarkservices.services.optimizer.OptimizerService;
import eu.ark.creditark.services.creditarkservices.services.transformator.scenarios.*;
import eu.ark.creditark.services.creditarkservices.shared.*;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;
import eu.ark.creditark.services.creditarkservices.utils.DtoGenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenariosDto;
import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.repository.BusinessRepository;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class ScenarioServiceImpl implements ScenarioService {

	@Autowired
	private BusinessRepository repositoryBucket;

	private ScenarioRepository scenarioRepository;

	@Autowired
	private UtilService utilService;
	
	@Autowired
	private ScenarioTransformatorFactory scenarioTransformatorFactory;

	@Autowired
	private ScenarioUITransformatorFactory scenarioUITransformatorFactory;

	@Autowired
	private ScenarioUIToBusTransformatorFactory scenarioUIToBusTransformatorFactory;

	@Autowired
	private ScenarioStatisticsToUIStatisticsTransformatorFactory scenarioStatisticsToUIStatisticsTransformator;

	@Autowired
	private AsyncServices asyncServices;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	//@Cacheable(value = "scenarioTemplate", key = "#input")
	@Override
	public ScenarioContainerUiDto template(ScenarioTemplateInputDto input/*String snapshotDate,
										   int contextId,
										   int comparisonPeriod,
										   String user*/) throws DatabaseConnectionException, ParseException {
		String schema = utilService.getSchema((short)input.getContextId());
		ContextInfo contextInfo = repositoryBucket.getContextInfo(new ContextInfoInputDto((short)input.getContextId(), input.getUser()), schema);

		ClienteleStatistics clienteleStatistics = repositoryBucket.getClienteleStatistics(new ClienteleStatisticsInputDto(
				DateUtils.stringToDate(input.getSnapshotDate(),
						GenerealConstants.DATE_FORMAT.getValue()), (short)-1,
				(short) input.getComparisonPeriod(), (short) input.getContextId()), schema);
		ScenarioContainerUiDto response = new ScenarioContainerUiDto();
		response.setContextId((short) input.getContextId());
		response.setSnapshotDate(input.getSnapshotDate());
		ScenarioUIDto scenario = new ScenarioUIDto();
		scenario.setScenarioOwnerName(input.getUser());
		scenario.setScenarioOwnerlogin(input.getUser());
		scenario.getParameters().setWorstAcceptableScore(contextInfo.getDefaultScenarioParams().getWorstAcceptedScore());
		scenario.getParameters().setMaxDso(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxDso()));
		scenario.getParameters().setMinAcceptedLimitUse(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMinAcceptedLimitUse()));
		scenario.getParameters().setWacc(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getWacc()));
		scenario.getParameters().setSignificantDigits(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getSignificantDigits()));
		scenario.getParameters().setRaroc(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getRaroc()));
		scenario.getParameters().setMinLimitReduction(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMinLimitReduction()));
		scenario.getParameters().setMinLimitGrowth(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMinLimitGrowth()));
		scenario.getParameters().setMinAcceptedRwMargin(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMinAcceptedRwm()));
		scenario.getParameters().setMaxLimitReduction(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMaxLimitReduction()));
		scenario.getParameters().setMaxLimitGrowth(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMaxLimitGrowth()));
		scenario.getParameters().setCreditAmount(DtoGenUtils.numberToString(DtoGenUtils.calcCreditAmount(clienteleStatistics.getBalance(), contextInfo.getDefaultScenarioParams().getSignificantDigits())));
		scenario.getParameters().setMinLimit(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMinLimit()));
		scenario.getParameters().setMinLimitPct(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMinLimitPct()));
		for(Portfolio portfolio : contextInfo.getPortfolios()) {
			clienteleStatistics.getBalance();
			ScenarioPortfolioUIDto scenarioPortfolioUIDto = new ScenarioPortfolioUIDto();
			scenarioPortfolioUIDto.setPortfolioName(portfolio.getDescription());
			scenarioPortfolioUIDto.setPortfolioId(portfolio.getPortfolioId()+"");
			/*scenarioPortfolioUIDto.getParameters().setMaxDso(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxDso()));
			scenarioPortfolioUIDto.getParameters().setWorstAcceptableScore(contextInfo.getDefaultScenarioParams().getWorstAcceptedScore());
			scenarioPortfolioUIDto.getParameters().setMinLimitGrowth(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxLimitGrowth()));
			scenarioPortfolioUIDto.getParameters().setMinLimitReduction(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxLimitReduction()));
			scenarioPortfolioUIDto.getParameters().setMinAcceptedLimitUse(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMinAcceptedLimitUse()));
			scenarioPortfolioUIDto.getParameters().setMaxLimitGrowth(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxLimitGrowth()));
			scenarioPortfolioUIDto.getParameters().setMaxLimitReduction(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxLimitReduction()));
			scenarioPortfolioUIDto.getParameters().setMinAcceptedRwMargin(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMinAcceptedRwm()));
			*/
			scenarioPortfolioUIDto.getParameters().setMaxDsoCli(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxDso()));
			scenarioPortfolioUIDto.getParameters().setWorstAcceptableScoreCli(contextInfo.getDefaultScenarioParams().getWorstAcceptedScore());
			scenarioPortfolioUIDto.getParameters().setMinLimitGrowthCli(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMinLimitGrowth()));
			scenarioPortfolioUIDto.getParameters().setMinLimitReductionCli(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMinLimitReduction()));
			scenarioPortfolioUIDto.getParameters().setMinAcceptedLimitUseCli(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMinAcceptedLimitUse()));
			scenarioPortfolioUIDto.getParameters().setMaxLimitGrowthCli(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMaxLimitGrowth()));
			scenarioPortfolioUIDto.getParameters().setMaxLimitReductionCli(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMaxLimitReduction()));
			scenarioPortfolioUIDto.getParameters().setMinAcceptedRwMarginCli(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMinAcceptedRwm()));
			scenario.getPortfolios().add(scenarioPortfolioUIDto);
		}

		ScenarioCustomerUIDto customerUIDto = new ScenarioCustomerUIDto();
		ScenarioCustomerParametersUIDto scenarioCustomerParametersUIDto = new ScenarioCustomerParametersUIDto();
		/*scenarioCustomerParametersUIDto.setMaxDso(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxDso()));
		scenarioCustomerParametersUIDto.setMinLimitGrowth(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxLimitGrowth()));
		scenarioCustomerParametersUIDto.setMinLimitReduction(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxLimitReduction()));
		scenarioCustomerParametersUIDto.setMinAcceptedLimitUse(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMinAcceptedLimitUse()));
		scenarioCustomerParametersUIDto.setMaxLimitGrowth(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxLimitGrowth()));
		scenarioCustomerParametersUIDto.setMaxLimitReduction(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxLimitReduction()));
		scenarioCustomerParametersUIDto.setMinAcceptedRwMargin(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMinAcceptedRwm()));
		*/
		scenarioCustomerParametersUIDto.setMaxDsoCli(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxDso()));
		scenarioCustomerParametersUIDto.setMinLimitGrowthCli(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMaxLimitGrowth()));
		scenarioCustomerParametersUIDto.setMinLimitReductionCli(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMaxLimitReduction()));
		scenarioCustomerParametersUIDto.setMinAcceptedLimitUseCli(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMinAcceptedLimitUse()));
		scenarioCustomerParametersUIDto.setMaxLimitGrowthCli(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMaxLimitGrowth()));
		scenarioCustomerParametersUIDto.setMaxLimitReductionCli(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMaxLimitReduction()));
		scenarioCustomerParametersUIDto.setMinAcceptedRwMarginCli(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMinAcceptedRwm()));
		customerUIDto.setParameters(scenarioCustomerParametersUIDto);
		scenario.getCustomers().add(customerUIDto);

		ScenarioProspectUIDto scenarioProspectUIDto = new ScenarioProspectUIDto();
		scenarioProspectUIDto.getParameters().setMaxDso(DtoGenUtils.numberToString(contextInfo.getDefaultScenarioParams().getMaxDso()));
		scenarioProspectUIDto.getParameters().setMinAcceptedRwMargin(DtoGenUtils.numberToStringPercentage(contextInfo.getDefaultScenarioParams().getMinAcceptedRwm()));
		scenarioProspectUIDto.getParameters().setMitigants(contextInfo.getMitigantDescriptions());
		scenarioProspectUIDto.getParameters().setMitigantsValues(DtoGenUtils.numberArrayToStringArray(new double[contextInfo.getMitigantDescriptions().length]));
		scenario.getProspects().add(scenarioProspectUIDto);
		response.getScenarios().add(scenario);

		return response;
	}

	@Override
	public void advertiseSystemScenario(int scenarioId, int contextId) {
		logger.info("Advertise scenario {}", scenarioId);
		try {
			scenarioRepository.advertiseSystemScenario(scenarioId);
			scenarioRepository.deletePreviousDefaultScenarios(contextId);
		} catch (SQLException e) {
			logger.info("Advertise scenario {} {}", scenarioId, e);
		}
	}




	@Override
	public ScenarioContainerUiDto getScenarios(ScenarioInputDto input)
			throws DatabaseConnectionException, ParseException {
		String schema = utilService.getSchema(input.getContextId());
		ScenariosDto scenariosDto = scenarioTransformatorFactory
		.getTransformatorFactory(DtoTransformationType.SCENARIOS)
		.transform(new ScenarioTransformatorInputDto(
				input.getSnapshotDate(),
				scenarioRepository.getScenarios(input, schema, repositoryBucket.getContextInfo(
						new ContextInfoInputDto((short) input.getContextId(), input.getUser()),
						schema), false, 0)));
		ScenarioContainerUiDto response = new ScenarioContainerUiDto();
		List<ScenarioUIDto> scenarios =  scenarioUITransformatorFactory
				.getTransformatorFactory(DtoTransformationType.SCENARIOS_MAIN_PARAMETERS)
				.transform(scenariosDto,
						repositoryBucket.getContextInfo(new ContextInfoInputDto(input.getContextId(), input.getUser()), schema));

		if(null!=scenarios)
			for(ScenarioUIDto scenarioUIDto:scenarios) {
				scenarioUIDto.setEditable(scenarioRepository.canChangeScenario(scenarioUIDto.getScenarioId(),
						input.getUser(), schema));
			}
		response.setContextId(input.getContextId());
		response.setSnapshotDate(DateUtils.dateToString(input.getSnapshotDate(), GenerealConstants.DATE_FORMAT.getValue()));
		response.getScenarios().addAll(scenarios);
		return response;
	}

	public void resolveScenario() {

	}

	@Override
	public ScenarioUIDto createScenario(String user,
							   ScenarioContainerUiDto scenarioContainerUiDto)
			throws ScenarioException,
			DatabaseConnectionException,
			ParseException,
			CloneNotSupportedException, InterruptedException {

		short contextId = scenarioContainerUiDto.getContextId();
		String schema = utilService.getSchema(contextId);
		ContextInfo contextInfo = repositoryBucket.getContextInfo(new ContextInfoInputDto(contextId, user), schema);

		if(null != scenarioContainerUiDto.getScenarios().get(0).getProspects()) {
			int idx = 1;
			for(ScenarioProspectUIDto prospectUIDto:scenarioContainerUiDto.getScenarios().get(0).getProspects()) {
				prospectUIDto.getParameters().setProspectId(""+idx++);
			}
		}
		ScenarioBus bus = scenarioUIToBusTransformatorFactory
				.getTransformatorFactory(DtoTransformationType.SCENARIOS_TO_BUS)
				.transform(scenarioContainerUiDto, contextInfo);

		int scenarioId = scenarioRepository.createScenario(contextId, user,
				DateUtils.stringToDate(scenarioContainerUiDto.getSnapshotDate(),
						GenerealConstants.DATE_FORMAT.getValue()), bus,
						schema);

		Date snapshotDate = DateUtils.stringToDate(scenarioContainerUiDto.getSnapshotDate(), GenerealConstants.DATE_FORMAT.getValue());
		logger.info("createScenario {}", scenarioId);
		asyncServices.
		asyncScenario(scenarioId, schema, user, snapshotDate);
		ScenarioInputDto scenarioInputDto = new ScenarioInputDto(snapshotDate,
				user, contextId);
		ScenariosDto scenariosDto = scenarioTransformatorFactory
				.getTransformatorFactory(DtoTransformationType.SCENARIOS)
				.transform(new ScenarioTransformatorInputDto(
						snapshotDate,
						scenarioRepository.getScenarios(scenarioInputDto, schema, contextInfo, true, scenarioId)));
		List<ScenarioUIDto> scenarios =  scenarioUITransformatorFactory
				.getTransformatorFactory(DtoTransformationType.SCENARIOS_MAIN_PARAMETERS)
				.transform(scenariosDto,
						contextInfo);
		scenarioContainerUiDto.setScenarios(scenarios);
		return scenarioContainerUiDto.getScenarios().get(0);
	}

	@Override
	public ScenarioUIDto saveScenario(String user,
							 ScenarioContainerUiDto scenarioContainerUiDto)
			throws ScenarioException,
			DatabaseConnectionException,
			ParseException, CloneNotSupportedException, InterruptedException {
		short contextId = scenarioContainerUiDto.getContextId();
		int scenarioId = scenarioContainerUiDto.getScenarios().get(0).getScenarioId();
		String schema = utilService.getSchema(contextId);
		if(null != scenarioContainerUiDto.getScenarios().get(0).getProspects()) {
			int idx = maxScenarioProspectIdInt((short)scenarioContainerUiDto.getScenarios().get(0).getScenarioId(),
					scenarioContainerUiDto.getContextId());
			for(ScenarioProspectUIDto prospectUIDto:scenarioContainerUiDto.getScenarios().get(0).getProspects()) {
				if(null == prospectUIDto.getParameters().getProspectId()) {
					prospectUIDto.getParameters().setProspectId(""+(++idx));
				}
			}
		}
		logger.info("{}", scenarioId);
		ContextInfo contextInfo = repositoryBucket.getContextInfo(new ContextInfoInputDto(contextId, user), schema);

		ScenarioBus bus = scenarioUIToBusTransformatorFactory
				.getTransformatorFactory(DtoTransformationType.SCENARIOS_TO_BUS)
				.transform(scenarioContainerUiDto, contextInfo);

		scenarioRepository.saveScenario(scenarioContainerUiDto.getScenarios().get(0).getScenarioId(), bus, schema);
		Date snapshotDate = DateUtils.stringToDate(scenarioContainerUiDto.getSnapshotDate(),
				GenerealConstants.DATE_FORMAT.getValue());
		asyncServices.
		asyncScenario(scenarioId, schema, user, snapshotDate);
		ScenarioInputDto scenarioInputDto = new ScenarioInputDto(snapshotDate,
				user, contextId);
		ScenariosDto scenariosDto = scenarioTransformatorFactory
				.getTransformatorFactory(DtoTransformationType.SCENARIOS)
				.transform(new ScenarioTransformatorInputDto(
						snapshotDate,
						scenarioRepository.getScenarios(scenarioInputDto, schema, contextInfo, true, scenarioId)));
		List<ScenarioUIDto> scenarios =  scenarioUITransformatorFactory
				.getTransformatorFactory(DtoTransformationType.SCENARIOS_MAIN_PARAMETERS)
				.transform(scenariosDto,
						contextInfo);

		scenarioContainerUiDto.setScenarios(scenarios);
		return scenarioContainerUiDto.getScenarios().get(0);
	}

	@Override
	public ScenarioDeleteUIResponseDto deleteScenario(short contextId, int scenarioId) throws DatabaseConnectionException, ScenarioException {
		String schema = utilService.getSchema(contextId);
		scenarioRepository.deleteScenario(scenarioId, schema);
		asyncServices.deleteScenarioResults(scenarioId, schema);
		ScenarioDeleteUIResponseDto response = new ScenarioDeleteUIResponseDto();
		response.setResp("Deleted scenario:"+scenarioId);
		return response;
	}

	@Override
	public int maxScenarioProspectIdInt(short scenarioId, short contextId) throws DatabaseConnectionException {
		return scenarioRepository.maxScenarioProspectId(scenarioId, contextId, utilService.getSchema(contextId));
	}
	public BusinessRepository getRepositoryBucket() {
		return repositoryBucket;
	}

	public void setRepositoryBucket(BusinessRepository repositoryBucket) {
		this.repositoryBucket = repositoryBucket;
	}

	public ScenarioRepository getScenarioRepository() {
		return scenarioRepository;
	}

	@Autowired
	public void setScenarioRepository(ScenarioRepository scenarioRepository) {
		this.scenarioRepository = scenarioRepository;
	}

	public UtilService getUtilService() {
		return utilService;
	}

	public void setUtilService(UtilService utilService) {
		this.utilService = utilService;
	}

	public ScenarioTransformatorFactory getScenarioTransformatorFactory() {
		return scenarioTransformatorFactory;
	}

	public void setScenarioTransformatorFactory(ScenarioTransformatorFactory scenarioTransformatorFactory) {
		this.scenarioTransformatorFactory = scenarioTransformatorFactory;
	}

	public ScenarioUITransformatorFactory getScenarioUITransformatorFactory() {
		return scenarioUITransformatorFactory;
	}

	public void setScenarioUITransformatorFactory(ScenarioUITransformatorFactory scenarioUITransformatorFactory) {
		this.scenarioUITransformatorFactory = scenarioUITransformatorFactory;
	}

	public ScenarioUIToBusTransformatorFactory getScenarioUIToBusTransformatorFactory() {
		return scenarioUIToBusTransformatorFactory;
	}

	public void setScenarioUIToBusTransformatorFactory(ScenarioUIToBusTransformatorFactory scenarioUIToBusTransformatorFactory) {
		this.scenarioUIToBusTransformatorFactory = scenarioUIToBusTransformatorFactory;
	}

	public ScenarioStatisticsToUIStatisticsTransformatorFactory getScenarioStatisticsToUIStatisticsTransformator() {
		return scenarioStatisticsToUIStatisticsTransformator;
	}

	public void setScenarioStatisticsToUIStatisticsTransformator(ScenarioStatisticsToUIStatisticsTransformatorFactory scenarioStatisticsToUIStatisticsTransformator) {
		this.scenarioStatisticsToUIStatisticsTransformator = scenarioStatisticsToUIStatisticsTransformator;
	}

	public AsyncServices getAsyncServices() {
		return asyncServices;
	}

	public void setAsyncServices(AsyncServices asyncServices) {
		this.asyncServices = asyncServices;
	}

}
