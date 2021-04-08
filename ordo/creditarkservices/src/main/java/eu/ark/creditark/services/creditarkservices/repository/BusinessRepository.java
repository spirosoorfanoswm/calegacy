package eu.ark.creditark.services.creditarkservices.repository;

import eu.ark.creditark.services.creditarkservices.dto.input.*;
import eu.ark.creditark.services.creditarkservices.shared.*;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.utils.DbUtils;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Repository
public class BusinessRepository {

	private static String getAverageDaysToPay;
	private static String getAverageDaysUnpaid;

	static {
		getAverageDaysToPay = "SELECT VALUE FROM d_customer_apd_kpi WHERE customer_id=? AND snapshot_date=?";
		getAverageDaysUnpaid = "SELECT VALUE FROM d_customer_apdu_kpi WHERE customer_id=? AND snapshot_date=?";

	}

	@Autowired
	private AppPropertiesConfig appProperties;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final JdbcTemplate jdbcTemplate;

	public BusinessRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//@Cacheable(value = "contextinfospec", key = "#p0")
	public void contextInfo(short contextId, ContextInfo contextInfo, Short[] ids, String schema) throws Exception{
		logger.info("DB contextinfospec for " + contextId) ;
		PreparedStatement st = null;
		ResultSet rows = null;

		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();
			st = conn.prepareStatement( "SELECT * FROM public.get_context_info(?)");
			st.setShort(1, contextId);
			st.execute();
			rows = st.getResultSet();

			rows.next();

			int i;
			String[] descr;
			String[] s;

			if(null==rows || null==rows.getArray(1))
				return;

			ids = (Short[])rows.getArray(1).getArray();
			s = (String[])rows.getArray(2).getArray();

			Portfolio[] portfolios = new Portfolio[ids.length];
			for (i = 0; i < ids.length; i++) {
				portfolios[i] = new Portfolio();
				portfolios[i].setPortfolioId(ids[i]);
				portfolios[i].setDescription(s[i]);
			}
			contextInfo.setPortfolios(portfolios);
			descr = (String[])rows.getArray(3).getArray();
			Integer[] ids_1 = (Integer[])rows.getArray(4).getArray();
			s = (String[])rows.getArray(5).getArray();

			CustomDataMap[] qualitativeData = new CustomDataMap[descr.length];
			for (i = 0; i < descr.length; i++) {
				qualitativeData[i] = new CustomDataMap();
				qualitativeData[i].setDescription(descr[i]);
				qualitativeData[i].setDataType(ids_1[i].intValue());
				qualitativeData[i].setDiscreteValues(s[i] == null? null: s[i].split("~\\|~"));
			}
			contextInfo.setQualitativeData(qualitativeData);
			contextInfo.setActivityDescriptions((String[])rows.getArray(6).getArray());
			contextInfo.setExposureDescriptions((String[])rows.getArray(7).getArray());
			contextInfo.setBucketDescriptions((String[])rows.getArray(8).getArray());
			descr = (String[])rows.getArray(9).getArray();
			ids_1 = (Integer[])rows.getArray(10).getArray();
			s = (String[])rows.getArray(11).getArray();

			CustomDataMap[] criteria = new CustomDataMap[descr.length];
			for (i = 0; i < descr.length; i++) {
				criteria[i] = new CustomDataMap();
				criteria[i].setDescription(descr[i]);
				criteria[i].setDataType(ids_1[i].intValue());
				criteria[i].setDiscreteValues(s[i] == null? null: s[i].split("~\\|~"));
			}
			contextInfo.setCriteria(criteria);
			contextInfo.setCustomerStatuses((String[])rows.getArray(12).getArray());
			contextInfo.setEntityInfoDescriptions((String[])rows.getArray(13).getArray());
			contextInfo.setEntityFormat((String[])rows.getArray(14).getArray());
			contextInfo.setMitigantDescriptions((String[])rows.getArray(15).getArray());
			contextInfo.setMitigantRecoveryRates(getDoubleArray(rows, 16));

			if (rows.getArray(15) != null) {
				java.sql.Date[] sqlDates = (java.sql.Date[])rows.getArray(17).getArray();
				Date[] dates = new Date[sqlDates.length];
				for (i = 0; i < sqlDates.length; i++) dates[i] = sqlDates[i];
				Arrays.sort(dates, Collections.reverseOrder());
				contextInfo.setSnapshotDates(Arrays.copyOf(dates, dates.length-1));
			}


			contextInfo.setScores((String[])rows.getArray(18).getArray());// scores
			contextInfo.setGrades((String[])rows.getArray(19).getArray());// grades
			rows.close();
		} catch (Exception e) {
			logger.error("contextinfospec {}", e);
			 throw e;
		} finally {
			DbUtils.close(null, st);
		}
	}

	@Cacheable(value = "contextinfo", key = "#input.contextId")
	public ContextInfo getContextInfo(ContextInfoInputDto input, String schema) throws DatabaseConnectionException {
		logger.info("DB getContextInfo for " + input);
		ContextInfo contextInfo = new ContextInfo();
		Short[] ids = null;
		try {
			contextInfo(input.getContextId(), contextInfo, ids, schema);
			//scenarioDefaultParams(input.getContextId(), contextInfo, schema);
			//reportDescriptors(input.getContextId(), input.getUser(), contextInfo, ids);
			//userDefaultParams(input.getContextId(), contextInfo);
			contextInfo.setComparisonPeriods(appProperties.getComparisonPeriods());
			contextInfo.setScenarioThresholdsDto(appProperties.getScenarioThresholdsDto());
			logger.info("ret {}", contextInfo);
			return contextInfo;
		} catch (Exception e) {
			logger.error("Error during getContextInfo {} ", e);
			throw new DatabaseConnectionException("Error during retrieving application context");
		}
	}

	//@Cacheable(value = "scenarioDefaultParams", key = "#contextId")
	public void scenarioDefaultParams(short contextId, ContextInfo contextInfo, String schema) throws Exception{
		logger.info("DB scenarioDefaultParams for {} {}",  schema, contextId) ;
		PreparedStatement st = null;
		ResultSet rows = null;

		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			//conn.prepareStatement(
			//		"SET search_path TO "+schema+", public, core, ext").execute();
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			st = conn.prepareStatement("SELECT * FROM public.get_scenario_default_params(?,?)");
			st.setShort(1, contextId);
			st.setDate(2, new java.sql.Date(cal.getTime().getTime()));
			st.execute();
			rows = st.getResultSet();
			rows.next();

			ScenarioDefaultParams par = new ScenarioDefaultParams();
			par.raroc = rows.getDouble(1);
			par.wacc = rows.getDouble(2);
			par.worstAcceptedScore = rows.getString(3);
			par.maxDso = rows.getInt(4);
			if (rows.wasNull()) par.maxDso = null;
			par.minAcceptedLimitUse = rows.getDouble(5);
			if (rows.wasNull()) par.minAcceptedLimitUse = null;
			par.maxLimitGrowth = rows.getDouble(6);
			if (rows.wasNull()) par.maxLimitGrowth = null;
			par.maxLimitReduction = rows.getDouble(7);
			if (rows.wasNull()) par.maxLimitReduction = null;
			par.minLimitGrowth = rows.getDouble(8);
			if (rows.wasNull()) par.minLimitGrowth = null;
			par.minLimitReduction = rows.getDouble(9);
			if (rows.wasNull()) par.minLimitReduction = null;
			par.significantDigits = rows.getInt(10);
			if (rows.wasNull()) par.significantDigits = null;
			par.minLimit = rows.getDouble(11);
			if (rows.wasNull()) par.minLimit = null;
			par.minLimitPct = rows.getDouble(12);
			if (rows.wasNull()) par.minLimitPct = null;
			par.minAcceptedRwm = rows.getDouble(13);
			if (rows.wasNull()) par.minAcceptedRwm = null;

			rows.close();
			contextInfo.setDefaultScenarioParams(par);
		} catch (Exception e) {
			logger.error("scenarioDefaultParams {}" , e);
			throw e;
		} finally {
			DbUtils.close(null, st);
		}
	}

	@Cacheable(value = "reportsData"/*, key = "#number", condition = "#number>10"*/)
	public Map<Integer, ReportProperties> getReportsData() throws DatabaseConnectionException {
		/*logger.info("reportsData");
		ResultSet rows = null;
		PreparedStatement st = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
			conn.prepareStatement("SET search_path TO d_elpe, public, core, ext"*//*"SELECT * FROM public.get_context_info(?)"*//*)
			.execute();

			// Get all reports
			st = conn.prepareStatement("SELECT * FROM core.get_reports_properties()");
			st.execute();
			rows = st.getResultSet();

			HashMap<Integer, ReportProperties> result = new HashMap<Integer, ReportProperties>();
			while (rows.next())
				result.put(Integer.valueOf(rows.getInt(1)), new ReportProperties(
						rows.getString(3),
						rows.getString(2),
						(String[])rows.getArray(4).getArray()));
			return result;
		} catch (Exception e) {
			throw new DatabaseConnectionException("Error during getReportsData");
		} finally {
			DbUtils.close(rows, st);
		}*/
		return null;
	}


	@Cacheable(value = "configinfo", key = "#loginName")
	public ConfigInfo getConfigInfo(String loginName) throws DatabaseConnectionException {
		logger.info("DB configinfo for " + loginName);
		PreparedStatement st = null;
		ResultSet rows = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			st = conn.prepareStatement("SELECT * FROM core.get_config_info(?)");
			st.setString(1, loginName);
			st.execute();
			rows = st.getResultSet();
			rows.next();
			if (rows.getString(1) == null) return null;
			ConfigInfo configInfo = new ConfigInfo();
			configInfo.setLoginName(loginName);
			configInfo.setFullName(rows.getString(1));
			configInfo.setOrganization(rows.getString(2));
			configInfo.setChangePwRequired(rows.getBoolean(3));
			configInfo.setRoles(getShortArray(rows, 4));
			Short[] t;
			int i;
			t = (Short[])rows.getArray(5).getArray();
			String[] descrs = (String[])rows.getArray(6).getArray();
			LinkedHashMap<Integer, String> descMap = new LinkedHashMap<Integer, String>();
			for (i = 0; i < descrs.length; i++)
				descMap.put(t[i].intValue(), descrs[i]);
			configInfo.setContexts(descMap);
			configInfo.setPwRegEx(rows.getString(7));
			t = (Short[])rows.getArray(8).getArray();
			descrs = (String[])rows.getArray(9).getArray();
			HashMap<Integer, String> helpFiles = new HashMap<Integer, String>();
			for (i = 0; i < descrs.length; i++)
				helpFiles.put(t[i].intValue(), descrs[i]);
			configInfo.setHelpFiles(helpFiles);

			return configInfo;
		} catch (Exception e) {
			logger.error("DB configinfo for ",e);
			throw new DatabaseConnectionException("Error during retrieving configuration info");
		} finally {
			DbUtils.close(rows, st);
		}
	}

	@Cacheable(value = "customer", key = "#input")
	public CustomerDetails getCustomer(CustomerInputDto input, String schema)
			throws DatabaseConnectionException {
		logger.info("DB getCustomer for " + input) ;
		PreparedStatement st = null;
		ResultSet rows = null;
		CustomerDetails cust = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()){

			conn.prepareStatement(
					"SET search_path TO "+schema+", public, core, ext").execute();
			st = conn.prepareStatement("SELECT * FROM get_customer_data(?,?,?,?)");
			//st = conn.prepareStatement("SELECT * FROM get_customer_data_v2(?,?,?,?)");
			st.setShort(1, (short) input.getContextId());
			st.setString(2, input.getCustomerId());
			st.setDate(3, new java.sql.Date(input.getSnapshotDate().getTime()));
			st.setShort(4, (short) input.getPeriod());
			st.execute();
			rows = st.getResultSet();
			int j = 1;
			cust = new CustomerDetails();
			rows.next(); // Get the first row
			cust.setContextId(input.getContextId());
			cust.setCustomerId(input.getCustomerId());
			cust.setPortfolio(rows.getString(j++));
			cust.setVatNumber(rows.getString(j++));
			cust.setCustomerName(rows.getString(j++));
			cust.setAffiliate(rows.getBoolean(j++));
			cust.setGroupName(rows.getString(j++));
			cust.setIndustryId(rows.getString(j++));
			cust.setAreaId(rows.getString(j++));
			cust.setCustomerSince(rows.getDate(j++));
			cust.setOptimizationExcluded(rows.getBoolean(j++));
			cust.setExtAssessmentExcluded(rows.getBoolean(j++));
			cust.setCustomerStatus(rows.getString(j++));
			cust.setProfitMargin(rows.getDouble(j++));
			cust.setExposure(getDoubleArray(rows, j++));
			cust.setPastDueAmount(getDoubleArray(rows, j++));
			cust.setActivity(getDoubleArray(rows, j++));
			cust.setCreditMitigants(getDoubleArray(rows, j++));
			cust.setCriteria((Double[]) rows.getArray(j++).getArray());
			cust.setBehavioralScore(rows.getString(j++));
			cust.setCreditArkScore(rows.getString(j++));
			cust.setPd(rows.getDouble(j++));
			cust.setProvisions(getDoubleArray(rows, j++));
			cust.setPrevDate(rows.getDate(j++));
			cust.setPeriods0(rows.getInt(j++));
			cust.setMaxBalance0(rows.getDouble(j++));
			cust.setMaxBalanceDate0(rows.getDate(j++));
			cust.setMinBalance0(rows.getDouble(j++));
			cust.setMinBalanceDate0(rows.getDate(j++));
			cust.setMaxPastDue0(rows.getDouble(j++));
			cust.setMaxPastDueDate0(rows.getDate(j++));
			cust.setAvgBalance0(rows.getDouble(j++));
			cust.setAvgTurnover0(rows.getDouble(j++));
			cust.setAvgPayments0(rows.getDouble(j++));
			cust.setWorstBehavioralScore0(rows.getString(j++));
			cust.setWorstBehavioralScoreDate0(rows.getDate(j++));
			cust.setWorstCreditArkScore0(rows.getString(j++));
			cust.setWorstCreditArkScoreDate0(rows.getDate(j++));
			cust.setPeriods1(rows.getInt(j++));
			cust.setMaxBalance1(rows.getDouble(j++));
			cust.setMaxBalanceDate1(rows.getDate(j++));
			cust.setMinBalance1(rows.getDouble(j++));
			cust.setMinBalanceDate1(rows.getDate(j++));
			cust.setMaxPastDue1(rows.getDouble(j++));
			cust.setMaxPastDueDate1(rows.getDate(j++));
			cust.setAvgBalance1(rows.getDouble(j++));
			cust.setAvgTurnover1(rows.getDouble(j++));
			cust.setAvgPayments1(rows.getDouble(j++));
			cust.setWorstBehavioralScore1(rows.getString(j++));
			cust.setWorstBehavioralScoreDate1(rows.getDate(j++));
			cust.setWorstCreditArkScore1(rows.getString(j++));
			cust.setWorstCreditArkScoreDate1(rows.getDate(j++));
			cust.setQualitativeSnapshotDate(rows.getDate(j++));
			Array arr = rows.getArray(j++);
			cust.setQualitativeData((arr == null)?null:(Double[]) arr.getArray());
			cust.setQualitativeScore(rows.getString(j++));
			cust.setExtAssessmentDate(rows.getDate(j++));
			cust.setExtAssessment(rows.getString(j++));
			arr = rows.getArray(j++);
			cust.setEntityInfo((arr == null)?null:(String[]) arr.getArray());
			getGetAverageDaysToPay(input, schema, cust);
			getGetAverageDaysUnpaid(input, schema, cust);
			return cust;
		} catch (Exception e) {
			logger.error("Error during getCustomer", e);
			throw new DatabaseConnectionException("Error during getCustomer");
		} finally {

			DbUtils.close(rows, st);

		}
	}

	public void getGetAverageDaysToPay(CustomerInputDto input, String schema, CustomerDetails cust) {
		PreparedStatement st = null;
		ResultSet rows = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.prepareStatement("SET search_path TO " + schema + ", public, core, ext").execute();
			st = conn.prepareStatement(getAverageDaysToPay);
			st.setString(1, input.getCustomerId());
			st.setDate(2, new java.sql.Date(input.getSnapshotDate().getTime()));
			st.execute();
			rows = st.getResultSet();
			if(rows.next()) {
				cust.setAverageDaysToPay(rows.getDouble("VALUE"));
			}
		} catch (Exception e) {
			logger.error("Error during getAverageDaysValues {}", e);
		} finally {

			DbUtils.close(rows, st);

		}
	}

	public void getGetAverageDaysUnpaid(CustomerInputDto input, String schema, CustomerDetails cust) {
		PreparedStatement st = null;
		ResultSet rows = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.prepareStatement("SET search_path TO " + schema + ", public, core, ext").execute();
			st = conn.prepareStatement(getAverageDaysUnpaid);
			st.setString(1, input.getCustomerId());
			st.setDate(2, new java.sql.Date(input.getSnapshotDate().getTime()));
			st.execute();
			rows = st.getResultSet();
			if(rows.next()) {
				cust.setAverageDaysUnpaid(rows.getDouble("VALUE"));
			}
		} catch (Exception e) {
			logger.error("Error during getGetAverageDaysUnpaid {}", e);
		} finally {

			DbUtils.close(rows, st);

		}
	}

	public ScenarioCustomerStatistics getCustomerStatistics(String owner, int scenarioId, String customerId,
			String schema) throws DatabaseConnectionException {
		logger.info("DB getCustomerStatistics {} {} {} {}", owner, scenarioId, customerId, schema) ;
		PreparedStatement st = null;
		ResultSet rows = null;
		ScenarioCustomerStatistics cust = new ScenarioCustomerStatistics();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();
			st = conn.prepareStatement("SELECT * FROM get_customer_statistics(?,?,?)");
			st.setString(1, owner);
			st.setInt(2, scenarioId);
			st.setString(3, customerId);
			st.execute();
			rows = st.getResultSet();
			rows.next();
			cust.setProjectedPurchases(rows.getDouble(1));
			cust.setMaxLimit(rows.getDouble(2));
			cust.setProposedLimit(rows.getDouble(3));
			cust.setPd(rows.getDouble(4));
			cust.setLgd(rows.getDouble(5));
			cust.setFundingCost(rows.getDouble(6));
			cust.setWorkingCapital(rows.getDouble(7));
			cust.setRwMargin(rows.getDouble(8));
		} catch (Exception e) {
			logger.error("getCustomerStatistics {}", e);
			throw new DatabaseConnectionException("Error during getCustomerStatistics");
		} finally {
			DbUtils.close(rows, st);
		}
		return cust;
	}

	@Cacheable(value = "customersnum", key = "#input")
	public int getCustomersNum(CustomersNumInputDto input, String schema)
			throws DatabaseConnectionException {
		logger.info("DB customersnum for {} {}", schema, input) ;
		 CallableStatement cs = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.prepareStatement(
					"SET search_path TO "+schema+", public, core, ext").execute();
			if (input.getPortfolioId() != -1) {
				cs = conn.prepareCall("{?=call get_customers_num(?,?,?)}");
				cs.setShort(4, (short)input.getPortfolioId());
			} else {
				cs = conn.prepareCall("{?=call get_customers_num(?,?)}");
			}
			cs.registerOutParameter(1, Types.INTEGER); // Return value
			cs.setShort(2, (short)input.getContextId());
			cs.setDate(3, new java.sql.Date(input.getSnapshotDate().getTime()));

			cs.execute();
			return cs.getInt(1);
		} catch (Exception e) {
			logger.error("Error during getCustomersNum {} : {}", schema, input);
			throw new DatabaseConnectionException("Error during fetching customer number");
		} finally {
			DbUtils.close(cs);
		}
	}

	@Cacheable(value = "customers", key = "#input")
	public List<Customer> getCustomers(
			CustomersInputDto input, String schema)
					throws DatabaseConnectionException {
		logger.info("DB getCustomers for {} : {} " ,schema, input) ;
		PreparedStatement st = null;
		ResultSet rows = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.prepareStatement(
					"SET search_path TO "+schema+", public, core, ext").execute();
			st = conn.prepareStatement("SELECT * FROM get_customers(?,?,?,?,?,?,?,?)");
			st.setShort(1, input.getContextId());
			st.setDate(2, new java.sql.Date(input.getSnapshotDate().getTime()));
			st.setShort(3, (short)input.getScenarioId());
			st.setShort(4, (short)input.getPortfolioId());
			st.setShort(5, (short)input.getSortColumn());
			st.setBoolean(6, input.isSortDescending());
			st.setInt(7, input.getOffset());
			st.setInt(8, input.getLength());
			st.execute();
			rows = st.getResultSet();
			List<Customer> r = new ArrayList<Customer>();
			while (rows.next()) {
				Customer c = new Customer();
				c.setCustomerId(rows.getString(1));
				c.setCustomerName(rows.getString(2));
				c.setScore(rows.getString(3));
				c.setCreditLimit(rows.getDouble(4));
				c.setProposedLimit(rows.getDouble(5));
				c.setBalance(rows.getDouble(6));
				c.setPortfolio(rows.getString(7));
				c.setVatNumber(rows.getString(8));
				c.setCustomerStatus(rows.getString(9));
				c.setBehavioralScore(rows.getString(10));
				r.add(c);
			}
			return r;
		} catch (Exception e) {
			logger.error("getCustomers {}", e);
			throw new DatabaseConnectionException("Error during loading customer");
		} finally {
			DbUtils.close(rows, st);
		}
	}

	public List<Customer> getCustomersScenario(
			CustomersInputDto input, String schema)
			throws DatabaseConnectionException {
		logger.info("DB getCustomers for {} : {} " ,schema, input) ;
		PreparedStatement st = null;
		ResultSet rows = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.prepareStatement(
					"SET search_path TO "+schema+", public, core, ext").execute();
			st = conn.prepareStatement("SELECT * FROM get_customers(?,?,?,?,?,?,?,?)");
			st.setShort(1, input.getContextId());
			st.setDate(2, new java.sql.Date(input.getSnapshotDate().getTime()));
			st.setShort(3, (short)input.getScenarioId());
			st.setShort(4, (short)input.getPortfolioId());
			st.setShort(5, (short)input.getSortColumn());
			st.setBoolean(6, input.isSortDescending());
			st.setInt(7, input.getOffset());
			st.setInt(8, input.getLength());
			st.execute();
			rows = st.getResultSet();
			List<Customer> r = new ArrayList<Customer>();
			while (rows.next()) {
				Customer c = new Customer();
				c.setCustomerId(rows.getString(1));
				c.setCustomerName(rows.getString(2));
				c.setScore(rows.getString(3));
				c.setCreditLimit(rows.getDouble(4));
				c.setProposedLimit(rows.getDouble(5));
				c.setBalance(rows.getDouble(6));
				c.setPortfolio(rows.getString(7));
				c.setVatNumber(rows.getString(8));
				c.setCustomerStatus(rows.getString(9));
				c.setBehavioralScore(rows.getString(10));
				r.add(c);
			}
			return r;
		} catch (Exception e) {
			logger.error("getCustomers {}", e);
			throw new DatabaseConnectionException("Error during loading customer");
		} finally {
			DbUtils.close(rows, st);
		}
	}

	public List<Customer> getCustomersExport(
			CustomersInputDto input, String schema)
			throws DatabaseConnectionException {
		logger.info("DB getCustomersExport for {} : {} " ,schema, input) ;
		PreparedStatement st = null;
		ResultSet rows = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.prepareStatement(
					"SET search_path TO "+schema+", public, core, ext").execute();
			st = conn.prepareStatement("SELECT * FROM get_customers(?,?,?,?,?,?,?,?)");
			st.setShort(1, input.getContextId());
			st.setDate(2, new java.sql.Date(input.getSnapshotDate().getTime()));
			st.setShort(3, (short)input.getScenarioId());
			st.setShort(4, (short)input.getPortfolioId());
			st.setShort(5, (short)input.getSortColumn());
			st.setBoolean(6, input.isSortDescending());
			st.setInt(7, input.getOffset());
			st.setInt(8, input.getLength());
			st.execute();
			rows = st.getResultSet();
			List<Customer> r = new ArrayList<Customer>();
			while (rows.next()) {
				Customer c = new Customer();
				c.setCustomerId(rows.getString(1));
				c.setCustomerName(rows.getString(2));
				c.setScore(rows.getString(3));
				c.setCreditLimit(rows.getDouble(4));
				c.setProposedLimit(rows.getDouble(5));
				c.setBalance(rows.getDouble(6));
				c.setPortfolio(rows.getString(7));
				c.setVatNumber(rows.getString(8));
				c.setCustomerStatus(rows.getString(9));
				c.setBehavioralScore(rows.getString(10));
				r.add(c);
			}
			return r;
		} catch (Exception e) {
			logger.error("getCustomers {}", e);
			throw new DatabaseConnectionException("Error during loading customer");
		} finally {
			DbUtils.close(rows, st);
		}
	}

	@Cacheable(value = "customerentity", key = "#input")
	public List<CustomerIdentity> getCustomers(CustomerInputSearchDto input, String schema) throws DatabaseConnectionException {
		logger.info("DB getCustomers CustomerIdentity for " ) ;
		PreparedStatement st = null;
		ResultSet rows = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();

			switch (input.getSearchBy()) {
			case CODE:
				st = conn.prepareStatement("SELECT * FROM get_customers_identities_id(?,?,?,?)");
				break;
			case VAT:
				st = conn.prepareStatement("SELECT * FROM get_customers_identities_vat(?,?,?,?)");
				break;
			default:
				st = conn.prepareStatement("SELECT * FROM get_customers_identities_name(?,?,?,?)");
			}
			st.setShort(1, (short)input.getContextId());
			st.setDate(2, new java.sql.Date(input.getSnapshotDate().getTime()));
			st.setShort(3, (short)input.getPortfolioId());
			st.setString(4, input.getSearchKey());

			st.execute();
			rows = st.getResultSet();

			List<CustomerIdentity> r = new ArrayList<CustomerIdentity>();

			while (rows.next()) {
				CustomerIdentity c = new CustomerIdentity();
				c.setCustomerId(rows.getString(1));
				c.setCustomerName(rows.getString(2));
				c.setPortfolioId(rows.getInt(3));
				c.setPortfolio(rows.getString(4));
				c.setVatNumber(rows.getString(5));
				r.add(c);
			}
			return r;
		}  catch (Exception e) {
			logger.error("getCustomers CustomerIdentity {}", e);
			throw new DatabaseConnectionException("Error during loading customer");
		} finally {
			DbUtils.close(rows, st);
		}
	}

	/**
	 *
	 * @param input
	 * @return
	 * @throws DatabaseConnectionException
	 */

	@Cacheable(value = "portfolioAging", key = "#input")
	public PortfolioAgingOverall getPortfolioAging(ClienteleStatisticsInputDto input, String schema) {
		logger.info("DB getPortfolioAging {} ", input.toString());
		PortfolioAgingOverall response = new PortfolioAgingOverall();
		List<PortfolioAging> portfolioAgings = new ArrayList();
		List<PortfolioAging> portfolioDunningAgings = new ArrayList();
		response.setPortfolioAgingList(portfolioAgings);
		if(input.getPortfolioId()<=0) {
			response.setPortfolioAgingList(portfolioAgings);
			response.setPortfolioDunningAgingList(portfolioDunningAgings);
			return response;
		}
		PreparedStatement st = null;
		ResultSet rows = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.prepareStatement(
					"SET search_path TO "+schema+", public, core, ext").execute();
			st = conn.prepareStatement("SELECT context_id, snapshot_date, portfolio_id, aging_bucket, aging_bucket_value FROM d_portfolio_aging where context_id=? and snapshot_date=? and portfolio_id=?");
			st.setInt(1, input.getContextId());
			st.setDate(2, new java.sql.Date(input.getSnapshotDate().getTime()));
			st.setInt(3, input.getPortfolioId());
			st.execute();
			rows = st.getResultSet();
			while(rows.next()) {
				portfolioAgings.add(new PortfolioAging(
						rows.getInt("portfolio_id"),
						rows.getString("aging_bucket"),
						rows.getDouble("aging_bucket_value")));
			}
			rows.close();
			st.close();

			st = conn.prepareStatement("SELECT context_id, snapshot_date, portfolio_id, aging_bucket, aging_bucket_value FROM d_portfolio_dunning_aging where context_id=? and snapshot_date=? and portfolio_id=?");
			st.setInt(1, input.getContextId());
			st.setDate(2, new java.sql.Date(input.getSnapshotDate().getTime()));
			st.setInt(3, input.getPortfolioId());
			st.execute();
			rows = st.getResultSet();
			while(rows.next()) {
				portfolioDunningAgings.add(new PortfolioAging(
						rows.getInt("portfolio_id"),
						rows.getString("aging_bucket"),
						rows.getDouble("aging_bucket_value")));
			}
			rows.close();
			st.close();

			st = conn.prepareStatement("SELECT context_id, snapshot_date, portfolio_id, value FROM d_portfolio_apdu_kpi where context_id=? and snapshot_date=? and portfolio_id=?");
			st.setInt(1, input.getContextId());
			st.setDate(2, new java.sql.Date(input.getSnapshotDate().getTime()));
			st.setInt(3, input.getPortfolioId());
			st.execute();
			rows = st.getResultSet();
			if(rows.next()) {
				response.setAverageDaysUnpaid(new AveragePortfolioAging(rows.getDouble("value")));
			}

			rows.close();
			st.close();

			st = conn.prepareStatement("SELECT context_id, snapshot_date, portfolio_id, value FROM d_portfolio_apd_kpi where context_id=? and snapshot_date=? and portfolio_id=?");
			st.setInt(1, input.getContextId());
			st.setDate(2, new java.sql.Date(input.getSnapshotDate().getTime()));
			st.setInt(3, input.getPortfolioId());
			st.execute();
			rows = st.getResultSet();
			if(rows.next()) {
				response.setAverageDaysToPay(new AveragePortfolioAging(rows.getDouble("value")));
			}
			response.setPortfolioAgingList(portfolioAgings);
			response.setPortfolioDunningAgingList(portfolioDunningAgings);

		} catch (Exception e) {
			logger.error("clientStatistics {}" , input.toString());
			//throw new DatabaseConnectionException("Error during getClienteleStatistics");
		} finally {
			DbUtils.close(st);
			DbUtils.close(rows, null);
		}
		return response;
	}
	@Cacheable(value = "clientelestatistics", key = "#input")
	public ClienteleStatistics getClienteleStatistics(ClienteleStatisticsInputDto input, String schema)
					throws DatabaseConnectionException {
		logger.info("DB clientelestatistics {} ", input.toString());
		ClienteleStatistics b = new ClienteleStatistics();
		PreparedStatement st = null;
		ResultSet rows = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.prepareStatement(
					"SET search_path TO "+schema+", public, core, ext").execute();
			st = conn.prepareStatement("SELECT * FROM get_clientele_statistics(?,?,?,?)");
			st.setShort(1, (short)input.getContextId());
			st.setDate(2, new java.sql.Date(input.getSnapshotDate().getTime()));
			st.setShort(3, input.getPortfolioId());
			st.setShort(4, input.getComparisonPeriod());
			st.execute();
			rows = st.getResultSet();
			rows.next();
			b.setCustomers(rows.getInt(1));
			b.setCustomersPrev(rows.getInt(2));
			b.setBalance(rows.getDouble(3));
			b.setBalancePrev(rows.getDouble(4));
			b.setPastDue(rows.getDouble(5));
			b.setPastDuePrev(rows.getDouble(6));
			b.setLimits(rows.getDouble(7));
			b.setLimitsPrev(rows.getDouble(8));
			b.setOverrides(rows.getDouble(9));
			b.setOverridesPrev(rows.getDouble(10));
			b.setUnusedLimits(rows.getDouble(11));
			b.setUnusedLimitsPrev(rows.getDouble(12));
			b.setAggSales(rows.getDouble(13));
			b.setAggSalesPrev(rows.getDouble(14));
			b.setAggVat(rows.getDouble(15));
			b.setAggVatPrev(rows.getDouble(16));
			b.setAggTurnover(rows.getDouble(17));
			b.setAggTurnoverPrev(rows.getDouble(18));
			b.setAggPayments(rows.getDouble(19));
			b.setAggPaymentsPrev(rows.getDouble(20));
			b.setMeanSales(rows.getDouble(21));
			b.setMeanSalesPrev(rows.getDouble(22));
			b.setMeanTurnover(rows.getDouble(23));
			b.setMeanTurnoverPrev(rows.getDouble(24));
			b.setMeanPayments(rows.getDouble(25));
			b.setMeanPaymentsPrev(rows.getDouble(26));
			b.setMeanExposure(rows.getDouble(27));
			b.setMeanExposurePrev(rows.getDouble(28));
			b.setMeanPastDue(rows.getDouble(29));
			b.setMeanPastDuePrev(rows.getDouble(30));
			b.setMeanLimits(rows.getDouble(31));
			b.setMeanLimitsPrev(rows.getDouble(32));
			b.setMeanOverrides(rows.getDouble(33));
			b.setMeanOverridesPrev(rows.getDouble(34));
			b.setMeanUnusedLimits(rows.getDouble(35));
			b.setMeanUnusedLimitsPrev(rows.getDouble(36));
			b.setVolSales(rows.getDouble(37));
			b.setVolSalesPrev(rows.getDouble(38));
			b.setVolPayments(rows.getDouble(39));
			b.setVolPaymentsPrev(rows.getDouble(40));
			b.setVolExposure(rows.getDouble(41));
			b.setVolExposurePrev(rows.getDouble(42));
			b.setCustomersPerScore(getIntArray(rows, 43));
			b.setExposurePerScore(getDoubleArray(rows, 44));
			b.setPeriodSnapshotDate((Date[])rows.getArray(45).getArray());
			b.setPeriodTurnover(getDoubleArray(rows, 46));
			b.setPeriodSales(getDoubleArray(rows, 47));
			b.setPeriodPayments(getDoubleArray(rows, 48));
			b.setPeriodLimit(getDoubleArray(rows, 49));
			b.setPeriodExposure(getDoubleArray(rows, 50));
			b.setPeriodPastDue(getDoubleArray(rows, 51));
			b.setSnapshotDatePrev(rows.getDate(52));
			b.setLimitsPerScore(getDoubleArray(rows, 53));
			logger.info("DB clientelestatistics {} ", b);
		} catch (Exception e) {
			logger.error("clientStatistics {}" , input.toString());
			//throw new DatabaseConnectionException("Error during getClienteleStatistics");
		} finally {
			DbUtils.close(st);
			DbUtils.close(rows, null);
		}
		return b;
	}


	private void setNDouble(CallableStatement cs, int parameterIndex, Double value) throws SQLException {
		if (value == null)
			cs.setNull(parameterIndex, Types.DOUBLE);
		else
			cs.setDouble(parameterIndex, value);
	}

	private Double getNDouble(ResultSet rows, int parameterIndex) throws SQLException {
		Double d = rows.getDouble(parameterIndex);
		return rows.wasNull() ? null : d;
	}

	private void setNInt(CallableStatement cs, int parameterIndex, Integer value) throws SQLException {
		if (value == null)
			cs.setNull(parameterIndex, Types.INTEGER);
		else
			cs.setInt(parameterIndex, value);
	}

	private Integer getNInt(ResultSet rows, int parameterIndex) throws SQLException {
		Integer d = rows.getInt(parameterIndex);
		return rows.wasNull() ? null : d;
	}

	private void setNShort(CallableStatement cs, int parameterIndex, Integer value) throws SQLException {
		if (value == null)
			cs.setNull(parameterIndex, Types.SMALLINT);
		else
			cs.setShort(parameterIndex, value.shortValue());
	}


	private double[] getDoubleArray(ResultSet rows, int parameterIndex) throws SQLException {
		Double[] d = (Double[])rows.getArray(parameterIndex).getArray();
		double[] r = new double[d.length];
		for (int i = 0; i < d.length; i++)
			r[i] = d[i].doubleValue();
		return r;
	}

	private int[] getIntArray(ResultSet rows, int parameterIndex) throws SQLException {
		Integer[] d = (Integer[])rows.getArray(parameterIndex).getArray();
		int[] r = new int[d.length];
		for (int i = 0; i < d.length; i++)
			r[i] = d[i].intValue();
		return r;
	}

	private int[] getShortArray(ResultSet rows, int parameterIndex) throws SQLException {
		Short[] d = (Short[])rows.getArray(parameterIndex).getArray();
		int[] r = new int[d.length];
		for (int i = 0; i < d.length; i++)
			r[i] = d[i].intValue();
		return r;
	}

	public AppPropertiesConfig getAppProperties() {
		return appProperties;
	}

	public void setAppProperties(AppPropertiesConfig appProperties) {
		this.appProperties = appProperties;
	}
}
