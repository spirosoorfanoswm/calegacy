package eu.ark.creditark.services.creditarkservices.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.dto.ClienteleStatisticsDto;
import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleStatisticsInputDto;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomersInputDto;
import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import eu.ark.creditark.services.creditarkservices.services.AutomatedActions;
import eu.ark.creditark.services.creditarkservices.services.BusinessService;
import eu.ark.creditark.services.creditarkservices.services.ConcurrentService;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;
import eu.ark.creditark.services.creditarkservices.utils.DtoCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.utils.DbUtils;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CommonRepositoryImpl implements CommonRepository{
	
	@Autowired
	private BusinessRepository businessRepository;

	@Autowired
	private ConcurrentService concurrentService;

	@Autowired
	private BusinessService businessService;

	@Autowired
	private AutomatedActions automatedActions;

	@Autowired
	private AppPropertiesConfig appProperties;



	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final JdbcTemplate jdbcTemplate;
	
	public CommonRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Cacheable(value = "applySearchPath", key = "#contextId")
	public String searchPath(short contextId) throws DatabaseConnectionException {
		logger.info("DB searchPath for " + contextId) ;
		PreparedStatement st = null;
		ResultSet rows = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){

			st = conn.prepareStatement("sELECT schema_name FROM core.context where context_id=?");
			st.setShort(1, contextId);
			st.execute();
			rows = st.getResultSet();
			rows.next();
			return rows.getString(1);
		} catch (Exception e) {
			throw new DatabaseConnectionException("Error during loading context schema");
		} finally {
			DbUtils.close(rows, st);
		}
	}
	@Transactional
	public void loadUsers() throws DatabaseConnectionException {

		boolean loaded = true;
		PreparedStatement st = null;
		ResultSet rows = null;
		List<String> users = new ArrayList<>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){

			st = conn.prepareStatement("SELECT login_name FROM core.app_user");
			st.execute();
			rows = st.getResultSet();

			while (rows.next()) {
				String user = rows.getString(1);

					users.add(user);
			}

			users.stream().forEach(user -> {
				try {
					businessService.getConfigInfo(user);
				} catch (DatabaseConnectionException e) {
					logger.error("loadUsers getConfigInfo {}", user);
				}
			});
			
		} catch (Exception e) {
			logger.error("loadUsers", e);
			loaded = false;
			throw new DatabaseConnectionException("Error during caching");
		} finally {
			DbUtils.close(rows, st);
			if(loaded)
				loadContexts(users);
		}

	}
	@Transactional
	public void loadContexts(List<String> users) throws DatabaseConnectionException {
		boolean loaded = true;
		PreparedStatement st = null;
		ResultSet rows = null;
		Set<Date> distinctDates = new HashSet<>();
		Set<Short> portfolios = new HashSet<>();
		Set<Short> periods = appProperties.getComparisonPeriods().stream().map(period -> (short)period.getPeriod()).collect(Collectors.toSet());
		List<Short> contexts = new ArrayList<>();
		Map<Short, ContextInfo> contextsMap = new HashMap<>(0);
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			st = conn.prepareStatement("SELECT context_id FROM core.context where context_id!=1");
			st.execute();
			rows = st.getResultSet();

			ContextInfo contextInfo = new ContextInfo();
			Short[] ids = null;
			while (rows.next())
				contexts.add(rows.getShort(1));
			for(Short context: contexts) {
				for(String user:users) {
					contextInfo = concurrentService.contextInfo(context.shortValue(), user);
					if(null!=contextInfo) {
						contextsMap.put(context, contextInfo);
						if(null!=contextInfo.getSnapshotDates())
							distinctDates.addAll(Arrays.stream(contextInfo.getSnapshotDates()).collect(Collectors.toSet()));
						if(null!=contextInfo.getPortfolios())
							portfolios.addAll(Arrays.stream(contextInfo.getPortfolios()).map(portfolio -> (short)portfolio.getPortfolioId()).collect(Collectors.toSet()));
						portfolios.add(new Short("-1"));
					}
				}
			}


		} catch (Exception e) {
			logger.error("loadContexts : ", e);
			loaded = false;
			throw new DatabaseConnectionException("Error during caching context info");
		} finally {
			DbUtils.close(rows, st);
			if(loaded)
				loadClienteleStatistics(contextsMap,
						contexts,
						distinctDates.stream().collect(Collectors.toList()),
						portfolios.stream().collect(Collectors.toList()),
						periods.stream().collect(Collectors.toList()));
		}
	}

	@Transactional
	public void loadClienteleStatistics(Map<Short, ContextInfo> contextsMap,
										List<Short> contextIds,
										List<Date> snapshotDatesInput,
										List<Short> portfolioIds,
										List<Short> periods) {

		contextsMap.entrySet().stream().forEach(context-> {
			Set<Short> portfolios = new HashSet<>();
			portfolios.addAll(Arrays.stream(context.getValue().getPortfolios()).map(portfolio -> (short)portfolio.getPortfolioId()).collect(Collectors.toSet()));
			portfolios.add(new Short("-1"));
			Set<Date> snapshotDates = new HashSet<>();
			snapshotDates.addAll(Arrays.stream(context.getValue().getSnapshotDates()).collect(Collectors.toSet()));
			try {
				for (Date snapshotDate : snapshotDates)
					for (Short portfolio : portfolios)
						for (Short period : periods) {

							businessService.clienteleStatistics
									(new ClienteleStatisticsInputDto(
											DateUtils.stringToDate(DateUtils.dateToString(snapshotDate, GenerealConstants.DATE_FORMAT.getValue()), GenerealConstants.DATE_FORMAT.getValue()),
											portfolio,
											period,
											context.getKey().shortValue()));
						}
			} catch (Exception e) {
				logger.error("error during caching loadClienteleStatistics");
			} finally {
				runDefaultScenario(contextIds, snapshotDatesInput);
			}

		});
	}

	public void runDefaultScenario(List<Short> contexts, List<Date> snapshotDates) {
		automatedActions.systemScenario(contexts, snapshotDates);
	}

	public void loadCustomers(List<Short> contexts,
							  List<Date> snapshotDates,
							  List<Short> portfolios) {
		List<CustomersInputDto> requests = DtoCacheUtil.createLoadCustomersRequests(contexts, snapshotDates, portfolios);
		//logger.info();
		for (CustomersInputDto request:requests) {
			concurrentService.getCustomers(request);
		}

	}


	public BusinessRepository getBusinessRepository() {
		return businessRepository;
	}

	public void setBusinessRepository(BusinessRepository businessRepository) {
		this.businessRepository = businessRepository;
	}

	public ConcurrentService getConcurrentService() {
		return concurrentService;
	}

	public void setConcurrentService(ConcurrentService concurrentService) {
		this.concurrentService = concurrentService;
	}

	public BusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}

	public AutomatedActions getAutomatedActions() {
		return automatedActions;
	}

	public void setAutomatedActions(AutomatedActions automatedActions) {
		this.automatedActions = automatedActions;
	}

	public AppPropertiesConfig getAppProperties() {
		return appProperties;
	}

	public void setAppProperties(AppPropertiesConfig appProperties) {
		this.appProperties = appProperties;
	}
}
