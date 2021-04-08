package eu.ark.creditark.services.creditarkservices.repository;

import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.dto.input.ContextInfoInputDto;
import eu.ark.creditark.services.creditarkservices.dto.input.ScenarioInputDto;
import eu.ark.creditark.services.creditarkservices.dto.notification.Notification;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUIDto;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.services.NotificationService;
import eu.ark.creditark.services.creditarkservices.services.optimizer.CustomerInfoCallable;
import eu.ark.creditark.services.creditarkservices.services.optimizer.CustomerInfoExecutor;
import eu.ark.creditark.services.creditarkservices.shared.*;
import eu.ark.creditark.services.creditarkservices.utils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ScenarioRepository {

    private static String has_results;
    private static String delete_scenario;
    private static String get_prospects_params;
    private static String get_customers_params;
    private static String get_scenarios_params;
    private static String get_portfolios_params;
    private static String get_scenario_statistics;
    private static String get_portfolios_statistics;
    private static String get_prospects_statistics;
    private static String create_scenario;
    private static String d_system_scenario_insert;
    private static String clear_scenario_results;
    private static String delete_scenario_portfolio;
    private static String delete_scenario_prospect;
    private static String delete_scenario_customer;
    private static String save_scenario_params;
    private static String save_portfolio_params;
    private static String save_prospect_params;
    private static String save_customer_params;
    private static String can_change_scenario;
    private static String get_optimizer_scenario;
    private static String get_optimizer_params;
    private static String get_optimizer_customers;
    private static String get_optimizer_prospects;
    private static String d_customer_results;
    private static String insert_customer_results;
    private static String insert_prospect_results;
    private static String set_scenario_results_valid;
    static {
        has_results = "{?=call has_results(?)}";
        delete_scenario = "{call delete_scenario(?)}";
        get_prospects_params = "SELECT * FROM get_prospects_params(?,?,?)";
        get_customers_params = "SELECT * FROM public.get_customers_params(?,?,?)";
        get_scenarios_params = "SELECT * FROM public.get_scenarios_params(?,?,?)";
        get_portfolios_params = "SELECT * FROM public.get_portfolios_params(?,?,?)";
        get_scenario_statistics = "SELECT * FROM get_scenario_statistics(?)";
        get_portfolios_statistics = "SELECT * FROM public.get_portfolios_statistics(?)";
        get_prospects_statistics = "SELECT * FROM public.get_prospects_statistics(?)";
        create_scenario="{?=call create_scenario(?,?,?)}";
        d_system_scenario_insert="INSERT into d_system_scenario(scenario_id, scenario_date) VALUES (?, ?)";
        clear_scenario_results = "{call clear_scenario_results(?)}";
        delete_scenario_portfolio = "{call delete_scenario_portfolio(?,?)}";
        delete_scenario_prospect = "{call delete_scenario_prospect(?,?)}";
        delete_scenario_customer = "{call delete_scenario_customer(?,?)}";
        save_scenario_params = "{call save_scenario_params(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        save_portfolio_params = "{call save_portfolio_params(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        save_prospect_params = "{call save_prospect_params(?,?,?,?,?,?,?,?,?,?,?)}";
        save_customer_params = "{call save_customer_params(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        can_change_scenario = "{?=call can_change_scenario(?,?)}";
        get_optimizer_scenario = "SELECT * FROM get_optimizer_scenario(?)";
        get_optimizer_params = "SELECT * FROM get_optimizer_params(?)";
        get_optimizer_customers = "SELECT * FROM get_optimizer_customers(?)";
        get_optimizer_prospects = "SELECT * FROM get_optimizer_prospects(?)";
        d_customer_results = "delete from d_customer_results where scenario_id=?";
        insert_customer_results = "{call insert_customer_results(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        insert_prospect_results = "{call insert_prospect_results(?,?,?,?,?,?,?,?,?,?,?)}";
        set_scenario_results_valid = "{call set_scenario_results_valid(?)}";
    }

    @Autowired
    NotificationService notificationService;

    private AppPropertiesConfig appProperties;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final JdbcTemplate jdbcTemplate;

    public ScenarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<Integer, Scenario> getScenarios(ScenarioInputDto input,
                                               String schema,
                                               ContextInfo contextInfo, boolean reduce, int scenarioId)
            throws DatabaseConnectionException {
        logger.info("{} {} {} {} {}", schema, reduce, scenarioId, contextInfo, input);
        PreparedStatement st = null;
        ResultSet rows = null;
        LinkedHashMap<Integer, Scenario> bus = new LinkedHashMap<Integer, Scenario>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();
            st = conn.prepareStatement(get_scenarios_params);
            st.setString(1, input.getUser());
            st.setShort(2, input.getContextId());
            st.setDate(3, new java.sql.Date(input.getSnapshotDate().getTime()));
            st.execute();
            rows = st.getResultSet();

            while (rows.next()) {
                Scenario scenario = new Scenario();
                ScenarioClienteleParameters sc = scenario.getParameters().getClienteleParameters();

                scenario.setOwnerLogin(rows.getString(2));
                scenario.setOwnerName(rows.getString(3));
                scenario.setLocked(rows.getBoolean(4));
                scenario.setDescription(rows.getString(5));
                sc.setWacc(rows.getDouble(6));
                sc.setRaroc(rows.getDouble(7));
                sc.setCreditAmount(rows.getDouble(8));
                sc.setSignificantDigits(getNInt(rows, 9));
                sc.setMinLimit(getNDouble(rows, 10));
                sc.setWorstAcceptableScore(rows.getString(11));
                sc.setMaxLimitGrowth(getNDouble(rows, 12));
                sc.setMaxLimitReduction(getNDouble(rows, 13));
                sc.setMinLimitGrowth(getNDouble(rows, 14));
                sc.setMinLimitReduction(getNDouble(rows, 15));
                sc.setMinAcceptedLimitUse(getNDouble(rows, 16));
                sc.setMaxDso(getNInt(rows, 17));
                sc.setMinAcceptedRwMargin(rows.getDouble(18));
                sc.setSalesChange(getNDouble(rows, 19));
                sc.setMarginChange(getNDouble(rows, 20));
                sc.setPdChange(getNDouble(rows, 21));
                sc.setUserComments(rows.getString(22));
                sc.setMinLimitPct(getNDouble(rows, 24));

                scenario.setValidResult(rows.getBoolean(23));
                bus.put(rows.getInt(1), scenario);
            }
            rows.close();
            st.close();

            // Get portfolios parameters

            st = conn.prepareStatement(get_portfolios_params);
            st.setString(1, input.getUser());
            st.setShort(2, input.getContextId());
            st.setDate(3, new java.sql.Date(input.getSnapshotDate().getTime()));
            st.execute();
            rows = st.getResultSet();

            int curScenarioId = -1;
            Scenario curScenario = null;
            while (rows.next()) {
                int scen = rows.getInt(1);
                if (scen != curScenarioId) {
                    curScenarioId = scen;
                    curScenario = bus.get(scen);
                }
                ScenarioPortfolioParameters pp = new ScenarioPortfolioParameters();
                pp.setMaxLimitGrowth(getNDouble(rows, 3));
                pp.setMaxLimitReduction(getNDouble(rows, 4));
                pp.setMinLimitGrowth(getNDouble(rows, 5));
                pp.setMinLimitReduction(getNDouble(rows, 6));
                pp.setMinAcceptedLimitUse(getNDouble(rows, 7));
                pp.setMaxDso(getNInt(rows, 8));
                pp.setMinAcceptedRwMargin(rows.getDouble(9));
                pp.setSalesChange(getNDouble(rows, 10));
                pp.setMarginChange(getNDouble(rows, 11));
                pp.setPdChange(getNDouble(rows, 12));
                pp.setUserComments(rows.getString(13));
                pp.setWorstAcceptableScore(rows.getString(14));
                pp.setPortfolioId(rows.getInt(2));
                curScenario.getParameters().setPortfolioParameters(rows.getInt(2), pp);
            }
            rows.close();
            st.close();

            // Get prospects

            st = conn.prepareStatement(get_prospects_params);
            st.setString(1, input.getUser());
            st.setShort(2, input.getContextId());
            st.setDate(3, new java.sql.Date(input.getSnapshotDate().getTime()));
            st.execute();
            rows = st.getResultSet();

            curScenarioId = -1;
            curScenario = null;
            while (rows.next()) {
                int scen = rows.getInt(1);
                if (scen != curScenarioId) {
                    curScenarioId = scen;
                    curScenario = bus.get(scen);
                }
                ScenarioProspectParameters pp = new ScenarioProspectParameters();
                pp.setDescription(rows.getString(3));
                pp.setCustomersNum(rows.getInt(4));
                pp.setPurchases(rows.getDouble(5));
                pp.setMaxDso(getNInt(rows, 6));
                pp.setGradeInx(rows.getInt(7));
                pp.setProfitMargin(rows.getDouble(8));
                pp.setMitigants(getDoubleArray(rows, 9));
                pp.setMinAcceptedRwMargin(rows.getDouble(10));

                curScenario.getParameters().setProspectParameters(rows.getInt(2), pp);
            }
            rows.close();
            st.close();

            // Get customers parameters

            st = conn.prepareStatement(get_customers_params);
            st.setString(1, input.getUser());
            st.setShort(2, input.getContextId());
            st.setDate(3, new java.sql.Date(input.getSnapshotDate().getTime()));
            st.execute();
            rows = st.getResultSet();

            curScenarioId = -1;
            curScenario = null;
            while (rows.next()) {
                int scen = rows.getInt(1);
                if (scen != curScenarioId) {
                    curScenarioId = scen;
                    curScenario = bus.get(scen);
                }
                ScenarioCustomerParameters cp = new ScenarioCustomerParameters();
                cp.setCustomerName(rows.getString(3));
                ScenarioPortfolioParameters pp = new ScenarioPortfolioParameters();
                pp.setMaxLimitGrowth(getNDouble(rows, 4));
                pp.setMaxLimitReduction(getNDouble(rows, 5));
                pp.setMinLimitGrowth(getNDouble(rows, 6));
                pp.setMinLimitReduction(getNDouble(rows, 7));
                pp.setMinAcceptedLimitUse(getNDouble(rows, 8));
                pp.setMaxDso(getNInt(rows, 9));
                pp.setMinAcceptedRwMargin(rows.getDouble(10));
                pp.setSalesChange(getNDouble(rows, 11));
                pp.setMarginChange(getNDouble(rows, 12));
                pp.setPdChange(getNDouble(rows, 13));
                pp.setUserComments(rows.getString(14));

                cp.setParameters(pp);

                curScenario.getParameters().setCustomerParameters(rows.getString(2), cp);
            }

            // Get statistics where available
            for (Map.Entry<Integer, Scenario> entry : bus.entrySet()) {
                Scenario s = entry.getValue();
                if (s.hasValidResult())
                    s.setStatistics(getScenarioStatistics(entry.getKey(), (short)input.getContextId(), schema));
            }
            if(reduce)
                return bus.entrySet().stream().filter(x -> x.getKey().intValue() == scenarioId).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            return bus;

        } catch (Exception e) {
            logger.error("getScenarios {}", e);
            throw new DatabaseConnectionException("Error during loading Scenarios");
        } finally {
            DbUtils.close(rows, st);
        }
    }

    public ScenarioStatistics getScenarioStatistics(int scenarioId, short contextId, String schema) throws DatabaseConnectionException {
        PreparedStatement st = null;
        ResultSet rows = null;
        ScenarioStatistics bus = new ScenarioStatistics();
        logger.info("{} {} {}", scenarioId, contextId, schema);
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();

            // Scenario statistics
            st = conn.prepareStatement(get_scenario_statistics);
            st.setInt(1, scenarioId);
            st.execute();
            rows = st.getResultSet();
            rows.next();
            ScenarioPartStatistics ss = new ScenarioPartStatistics();
            ScenarioPartStatistics[] sd;
            // total statistics
            ss.setCustomersNum(rows.getInt(1));
            ss.setScenarioCustomersNum(rows.getInt(2));
            ss.setTurnover(rows.getDouble(3));
            ss.setProjectedTurnover(rows.getDouble(4));
            ss.setCurrentLimits(rows.getDouble(5));
            ss.setProposedLimits(rows.getDouble(6));
            ss.setFundingCost(rows.getDouble(7));
            ss.setProfitMargin(rows.getDouble(8));
            ss.setMeanRwm(rows.getDouble(9));
            ss.setProposedProvisions(rows.getDouble(10));
            ss.setProposedWc(rows.getDouble(11));
            bus.setStats(ss);
            // customers distribution
            int[] customerNum = getIntArray(rows, 12);
            double[] turnover = getDoubleArray(rows, 13);
            double[] projectedTurnover = getDoubleArray(rows, 14);
            double[] currentLimits = getDoubleArray(rows, 15);
            double[] proposedLimits = getDoubleArray(rows, 16);
            double[] fundingCost = getDoubleArray(rows, 17);
            double[] profitMargin = getDoubleArray(rows, 18);
            double[] meanRwm = getDoubleArray(rows, 19);
            double[] proposedProvisions = getDoubleArray(rows, 20);
            double[] proposedWc = getDoubleArray(rows, 21);
            sd = new ScenarioPartStatistics[customerNum.length];
            for (int i = 0; i < customerNum.length; i++) {
                ss = new ScenarioPartStatistics();
                ss.setCustomersNum(customerNum[i]);
                ss.setScenarioCustomersNum(customerNum[i]);
                ss.setTurnover(turnover[i]);
                ss.setProjectedTurnover(projectedTurnover[i]);
                ss.setCurrentLimits(currentLimits[i]);
                ss.setProposedLimits(proposedLimits[i]);
                ss.setFundingCost(fundingCost[i]);
                ss.setProfitMargin(profitMargin[i]);
                ss.setMeanRwm(meanRwm[i]);
                ss.setProposedProvisions(proposedProvisions[i]);
                ss.setProposedWc(proposedWc[i]);
                sd[i] = ss;
            }
            bus.setClienteleDistribution(sd);

            // Get prospects distribution
            customerNum = getIntArray(rows, 22);
            projectedTurnover = getDoubleArray(rows, 23);
            proposedLimits = getDoubleArray(rows, 24);
            fundingCost = getDoubleArray(rows, 25);
            profitMargin = getDoubleArray(rows, 26);
            meanRwm = getDoubleArray(rows, 27);
            proposedProvisions = getDoubleArray(rows, 28);
            proposedWc = getDoubleArray(rows, 29);

            sd = new ScenarioPartStatistics[customerNum.length];
            for (int i = 0; i < customerNum.length; i++) {
                ss = new ScenarioPartStatistics();
                ss.setCustomersNum(0);
                ss.setScenarioCustomersNum(customerNum[i]);
                ss.setTurnover(0);
                ss.setProjectedTurnover(projectedTurnover[i]);
                ss.setCurrentLimits(0);
                ss.setProposedLimits(proposedLimits[i]);
                ss.setFundingCost(fundingCost[i]);
                ss.setProfitMargin(profitMargin[i]);
                ss.setMeanRwm(meanRwm[i]);
                ss.setProposedProvisions(proposedProvisions[i]);
                ss.setProposedWc(proposedWc[i]);
                sd[i] = ss;
            }
            bus.setProspectsDistribution(sd);

            rows.close();
            st.close();
            // Portfolios statistics
            st = conn.prepareStatement(get_portfolios_statistics);
            st.setInt(1, scenarioId);
            st.execute();
            rows = st.getResultSet();
            HashMap<Integer, ScenarioPortfolioStatistics> portfoliosStatistics = new HashMap<Integer, ScenarioPortfolioStatistics>();
            while (rows.next()) {
                ScenarioPortfolioStatistics sps = new ScenarioPortfolioStatistics();
                ss = new ScenarioPartStatistics();
                ss.setCustomersNum(rows.getInt(2));
                ss.setTurnover(rows.getDouble(3));
                ss.setProjectedTurnover(rows.getDouble(4));
                ss.setCurrentLimits(rows.getDouble(5));
                ss.setProposedLimits(rows.getDouble(6));
                ss.setFundingCost(rows.getDouble(7));
                ss.setProfitMargin(rows.getDouble(8));
                ss.setMeanRwm(rows.getDouble(9));
                ss.setProposedProvisions(rows.getDouble(10));
                ss.setProposedWc(rows.getDouble(11));
                sps.setPartStatistics(ss);
                // customers distribution
                customerNum = getIntArray(rows, 12);
                turnover = getDoubleArray(rows, 13);
                projectedTurnover = getDoubleArray(rows, 14);
                currentLimits = getDoubleArray(rows, 15);
                proposedLimits = getDoubleArray(rows, 16);
                fundingCost = getDoubleArray(rows, 17);
                profitMargin = getDoubleArray(rows, 18);
                meanRwm = getDoubleArray(rows, 19);
                proposedProvisions = getDoubleArray(rows, 20);
                proposedWc = getDoubleArray(rows, 21);

                sd = new ScenarioPartStatistics[customerNum.length];
                for (int i = 0; i < customerNum.length; i++) {
                    ss = new ScenarioPartStatistics();
                    ss.setCustomersNum(customerNum[i]);
                    ss.setTurnover(turnover[i]);
                    ss.setProjectedTurnover(projectedTurnover[i]);
                    ss.setCurrentLimits(currentLimits[i]);
                    ss.setProposedLimits(proposedLimits[i]);
                    ss.setFundingCost(fundingCost[i]);
                    ss.setProfitMargin(profitMargin[i]);
                    ss.setMeanRwm(meanRwm[i]);
                    ss.setProposedProvisions(proposedProvisions[i]);
                    ss.setProposedWc(proposedWc[i]);
                    sd[i] = ss;
                }
                sps.setStatDistribution(sd);
                portfoliosStatistics.put(rows.getInt(1), sps);
            }

            if (!portfoliosStatistics.isEmpty()) bus.setPortfoliosStats(portfoliosStatistics);
            rows.close();
            st.close();
            // Prospects statistics
            st = conn.prepareStatement(get_prospects_statistics);
            st.setInt(1, scenarioId);
            st.execute();
            rows = st.getResultSet();
            HashMap<Integer, ScenarioCustomerStatistics> prospectStatistics = new HashMap<Integer, ScenarioCustomerStatistics>();
            while (rows.next()) {
                ScenarioCustomerStatistics scs = new ScenarioCustomerStatistics();
                scs.setProjectedPurchases(rows.getDouble(2));
                scs.setMaxLimit(rows.getDouble(3));
                scs.setProposedLimit(rows.getDouble(4));
                scs.setPd(rows.getDouble(5));
                scs.setLgd(rows.getDouble(6));
                scs.setFundingCost(rows.getDouble(7));
                scs.setWorkingCapital(rows.getDouble(8));
                scs.setRwMargin(rows.getDouble(9));
                prospectStatistics.put(rows.getInt(1), scs);
            }
            if (!prospectStatistics.isEmpty()) bus.setProspectsStats(prospectStatistics);
            //return bus;
        } catch (Exception e) {
            logger.error("getScenarioStatistics {}", e);
            //throw new DatabaseConnectionException("Error during getScenarioStatistics");
        } finally {
            DbUtils.close(rows, st);
        }
        return bus;
    }

    public void deleteScenario(int scenarioId, String schema)
            throws DatabaseConnectionException {
        logger.info("deleteScenario {} {}" , scenarioId, schema);
        CallableStatement cs = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();
            cs = conn.prepareCall(delete_scenario);
            cs.setInt(1, scenarioId);
            cs.execute();
        } catch (Exception e) {
            logger.error("deleteScenario {}" , e);
            throw new DatabaseConnectionException("Error during deleting Scenario");
        } finally {
            DbUtils.close(cs);
        }
    }

    public int maxScenarioProspectId(short scenarioId, short contextId, String schema) throws DatabaseConnectionException {
        logger.info("DB maxScenarioId for " + scenarioId) ;
        PreparedStatement st = null;
        ResultSet rows = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
            conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();
            st = conn.prepareStatement("select max(prospect_id) FROM "+schema+".d_scenario_prospect where scenario_id=?");
            st.setShort(1, scenarioId);
            st.execute();
            rows = st.getResultSet();
            if(rows.next()) return rows.getInt(1);
            else return 1;
        } catch (Exception e) {
            logger.error("maxScenarioId {}" , e);
            throw new DatabaseConnectionException("Error during maxScenarioId");
        } finally {
            DbUtils.close(rows, st);
        }
    }

    public int createScenario(short contextId,
                              String loginName,
                              java.util.Date snapshotDate,
                              ScenarioBus bus,
                              String schema)
            throws DatabaseConnectionException {
        CallableStatement cs = null;
        logger.info("{} {} {}",
                contextId, loginName, snapshotDate);
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();
            conn.setAutoCommit(false);

            cs = conn.prepareCall(create_scenario);
            cs.registerOutParameter(1, Types.INTEGER); // Return value
            cs.setString(2, loginName);
            cs.setShort(3, (short)contextId);
            cs.setDate(4, new java.sql.Date(snapshotDate.getTime()));
            cs.execute();
            int scenarioId = cs.getInt(1);
            saveScenarioParams(conn, scenarioId, bus);
            conn.commit();
            return scenarioId;
        } catch (Exception e) {
            logger.error("createScenario  {}", e);
            throw new DatabaseConnectionException("Error during creating new Scenario");
        } finally {
            DbUtils.close(cs);
        }
    }

    public void advertiseSystemScenario(int scenarioId) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement stmt = conn
                            .prepareStatement(d_system_scenario_insert);
                    stmt.setInt(1, scenarioId);
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    stmt.setDate(2,   new java.sql.Date(cal.getTime().getTime()));
                    return stmt;
                }
            });
        } catch (Exception e) {
           logger.error("{}", e);

        } finally {

        }

    }

    public void saveScenario(int scenarioId, ScenarioBus bus, String schema)
            throws DatabaseConnectionException {
        logger.info("{} {}",
                scenarioId);
        CallableStatement cs = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.prepareStatement(
                    "SET search_path TO "+schema+", public, core, ext").execute();
            conn.setAutoCommit(false);
            saveScenarioParams(conn, scenarioId, bus);
            // Clear potential previously calculated results
            cs = conn.prepareCall(clear_scenario_results);
            cs.setInt(1, scenarioId);
            cs.execute();
            cs.close();
            // Delete portfolios parameters
            if (bus.deletedPortfoliosIds != null) {
                cs = conn.prepareCall(delete_scenario_portfolio);
                for (int i = 0; i < bus.deletedPortfoliosIds.length; i++) {
                    cs.setInt(1, scenarioId);
                    cs.setShort(2, (short)bus.deletedPortfoliosIds[i]);
                    cs.addBatch();
                }
                cs.executeBatch();
                cs.close();
            }
            // Delete prospects parameters
            if (bus.deletedProspectsIds != null) {
                cs = conn.prepareCall(delete_scenario_prospect);
                for (int i = 0; i < bus.deletedProspectsIds.length; i++) {
                    cs.setInt(1, scenarioId);
                    cs.setShort(2, (short)bus.deletedProspectsIds[i]);
                    cs.addBatch();
                }
                cs.executeBatch();
                cs.close();
            }
            // Delete customers parameters
            if (bus.deletedCustomersIds != null) {
                cs = conn.prepareCall(delete_scenario_customer);
                for (int i = 0; i < bus.deletedCustomersIds.length; i++) {
                    cs.setInt(1, scenarioId);
                    cs.setString(2, bus.deletedCustomersIds[i]);
                    cs.addBatch();
                }
                cs.executeBatch();
                cs.close();
            }
            conn.commit();
        } catch (Exception e) {
            logger.error("saveScenario {}" , e);
            throw new DatabaseConnectionException("Error during updating Scenario");
        } finally {
            DbUtils.close(cs);
        }
    }

    private void saveScenarioParams(Connection conn, int scenarioId, ScenarioBus bus)
            throws DatabaseConnectionException {
        logger.info("{} {}",
                scenarioId, bus);
        CallableStatement cs = null;
        try  {
            if (bus.scenarioParameters != null) {
                ScenarioClienteleParameters sc = bus.scenarioParameters;
                cs = conn.prepareCall(
                        save_scenario_params);
                cs.setInt(1, scenarioId);
                cs.setString(2, bus.description);
                cs.setDouble(3, sc.getWacc());
                cs.setDouble(4, sc.getRaroc());
                cs.setDouble(5, sc.getCreditAmount());
                setNInt(cs, 6, sc.getSignificantDigits());
                setNDouble(cs, 7, sc.getMinLimit());
                setNDouble(cs, 8, sc.getMinLimitPct());
                cs.setString(9, sc.getWorstAcceptableScore());
                setNDouble(cs, 10, sc.getMaxLimitGrowth());
                setNDouble(cs, 11, sc.getMaxLimitReduction());
                setNDouble(cs, 12, sc.getMinLimitGrowth());
                setNDouble(cs, 13, sc.getMinLimitReduction());
                setNDouble(cs, 14, sc.getMinAcceptedLimitUse());
                setNInt(cs, 15, sc.getMaxDso());
                cs.setDouble(16, sc.getMinAcceptedRwMargin());
                setNDouble(cs, 17, sc.getSalesChange());
                setNDouble(cs, 18, sc.getMarginChange());
                setNDouble(cs, 19, sc.getPdChange());
                cs.setString(20, sc.getUserComments());
                cs.execute();
                cs.close();
            }
            // Save portfolios parameters data
            if (bus.portfoliosIds != null) {
                cs = conn.prepareCall(save_portfolio_params);
                for (int i = 0; i < bus.portfoliosIds.length; i++) {
                    ScenarioPortfolioParameters sp = bus.portfoliosParameters[i];
                    cs.setInt(1, scenarioId);
                    cs.setShort(2, (short)bus.portfoliosIds[i]);
                    setNDouble(cs, 3, sp.getMaxLimitGrowth());
                    setNDouble(cs, 4, sp.getMaxLimitReduction());
                    setNDouble(cs, 5, sp.getMinLimitGrowth());
                    setNDouble(cs, 6, sp.getMinLimitReduction());
                    setNDouble(cs, 7, sp.getMinAcceptedLimitUse());
                    setNInt(cs, 8, sp.getMaxDso());
                    cs.setDouble(9, sp.getMinAcceptedRwMargin());
                    setNDouble(cs, 10, sp.getSalesChange());
                    setNDouble(cs, 11, sp.getMarginChange());
                    setNDouble(cs, 12, sp.getPdChange());
                    cs.setString(13, sp.getUserComments());
                    cs.addBatch();
                }
                cs.executeBatch();
                cs.close();
            }
            // Save prospects parameters data
            if (bus.prospectsIds != null) {
                cs = conn.prepareCall(save_prospect_params);
                for (int i = 0; i < bus.prospectsIds.length; i++) {
                    ScenarioProspectParameters pp = bus.prospectsParameters[i];
                    cs.setInt(1, scenarioId);
                    cs.setShort(2, (short)bus.prospectsIds[i]);
                    cs.setString(3, pp.getDescription());
                    cs.setShort(4, (short)pp.getCustomersNum());
                    cs.setDouble(5, pp.getPurchases());
                    setNShort(cs, 6, pp.getMaxDso());
                    cs.setShort(7, (short)pp.getGradeInx());
                    cs.setDouble(8, pp.getProfitMargin());
                    double[] mitigants = pp.getMitigants();
                    Double[] arr = new Double[mitigants.length];
                    for (int j = 0; j < arr.length; j++)
                        arr[j] = mitigants[j];
                    Array sqlArr = conn.createArrayOf("float8", arr);
                    cs.setArray(9, sqlArr);
                    cs.setDouble(10, pp.getMinAcceptedRwMargin());
                    cs.setString(11, pp.getComments());
                    cs.addBatch();
                }
                cs.executeBatch();
                cs.close();
            }
            // Save customers parameters data
            if (bus.customersIds != null) {
                cs = conn.prepareCall(save_customer_params );
                for(int i = 0; i < bus.customersIds.length; i++) {
                    ScenarioPortfolioParameters sp = bus.customersParameters[i];
                    cs.setInt(1, scenarioId);
                    cs.setString(2, bus.customersIds[i]);
                    setNDouble(cs, 3, sp.getMaxLimitGrowth());
                    setNDouble(cs, 4, sp.getMaxLimitReduction());
                    setNDouble(cs, 5, sp.getMinLimitGrowth());
                    setNDouble(cs, 6, sp.getMinLimitReduction());
                    setNDouble(cs, 7, sp.getMinAcceptedLimitUse());
                    setNInt(cs, 8, sp.getMaxDso());
                    cs.setDouble(9, sp.getMinAcceptedRwMargin());
                    setNDouble(cs, 10, sp.getSalesChange());
                    setNDouble(cs, 11, sp.getMarginChange());
                    setNDouble(cs, 12, sp.getPdChange());
                    cs.setString(13, sp.getUserComments());
                    cs.addBatch();
                }
                cs.executeBatch();
                cs.close();
            }
        } catch (Exception e) {
            logger.error("saveScenarioParams {}" , e);
            throw new DatabaseConnectionException("Error during saveScenarioParams");
        } finally {
            DbUtils.close(cs);
        }
    }

    private void setNDouble(CallableStatement cs, int parameterIndex, Double value) throws SQLException {
        if (value == null)
            cs.setNull(parameterIndex, Types.DOUBLE);
        else
            cs.setDouble(parameterIndex, value);
    }

    public boolean canChangeScenario(int scenarioId, String loginName, String schema)
            throws DatabaseConnectionException {
        logger.info("{} {} {}",
                scenarioId, schema, loginName);
        CallableStatement cs = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();
            cs = conn.prepareCall(can_change_scenario);
            cs.registerOutParameter(1, Types.BOOLEAN); // Returned value
            cs.setString(2, loginName);
            cs.setInt(3, scenarioId);
            cs.execute();
            return cs.getBoolean(1);
        } catch (Exception e) {
            logger.error("scenarioId: {} loginName: {} error:{}",scenarioId, loginName , e);
            throw new DatabaseConnectionException("Error during canChangeScenario");
        } finally {
            DbUtils.close(cs);
        }
    }

    public ScenarioInfo attachScenarioInfoOptimizer(int scenarioId, String schema)
            throws DatabaseConnectionException {
        logger.info("{}", scenarioId);
        ScenarioInfo scenarioInfo  = new ScenarioInfo();
        PreparedStatement st = null;
        ResultSet rows = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();
            st = conn.prepareStatement(get_optimizer_scenario);
            st.setInt(1, scenarioId);
            st.execute();
            rows = st.getResultSet();
            rows.next();
            scenarioInfo.creditAmount = rows.getDouble(1);
            scenarioInfo.wacc = rows.getDouble(2);
            scenarioInfo.raroc = rows.getDouble(3);
            scenarioInfo.significantDigits = rows.getInt(4);
            scenarioInfo.minLimit = rows.getDouble(5);
            scenarioInfo.minLimitPct = rows.getDouble(6);
        } catch (Exception e) {
            logger.error("scenarioId: {} error: {}", scenarioId, e);
            throw new DatabaseConnectionException("Error during attachScenarioInfoOptimizer");
        } finally {
            DbUtils.close(rows, st);
        }
        return scenarioInfo;
    }

    public Parameters attachOptimizerParams(int scenarioId, String schema) throws DatabaseConnectionException {
        PreparedStatement st = null;
        ResultSet rows = null;
        Parameters parameters = new Parameters();
        logger.info("{}", scenarioId);
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();
            st = conn.prepareStatement(get_optimizer_params);
            st.setInt(1, scenarioId);
            st.execute();
            rows = st.getResultSet();
            rows.next();
            parameters.turnoverDays = rows.getInt(1);
            parameters.mitLgds = getDoubleArray(rows, 2);
            parameters.varMultiplier = rows.getDouble(3);
        } catch (Exception e) {
            logger.error("scenarioId: {} error: {}", scenarioId, e);
            throw new DatabaseConnectionException("Error during attachOptimizerParams");
        } finally {
            DbUtils.close(rows, st);
        }
        return parameters;
    }

    public ScenarioInfo getScenarioInfo(int scenarioId, Parameters parameters, String schema, ScenarioInfo scenarioInfo)

            throws DatabaseConnectionException {
        logger.info("{} {}",
                scenarioId, schema);
        PreparedStatement st = null;
        ResultSet rows = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();

            // Populate customers and prospects
            ArrayList<CustomerInfo> infoArray = new ArrayList<CustomerInfo>();
            CustomerInfo c;
            st = conn.prepareStatement(get_optimizer_customers);
            st.setInt(1, scenarioId);
            st.execute();
            rows = st.getResultSet();
            while (rows.next()) {
                c = new CustomerInfo();
                c.customerId = rows.getString(1);
                c.turnover = rows.getDouble(2);
                c.projTurnover = rows.getDouble(3);
                c.creditLimit = rows.getDouble(4);
                c.meanLimit = rows.getDouble(5);
                c.meanBalance = rows.getDouble(6);
                c.maxBalance = rows.getDouble(7);
                c.maxPurchases = rows.getDouble(8);
                c.pd = rows.getDouble(9);
                c.profitMargin = rows.getDouble(10);
                c.creditMitigants = getDoubleArray(rows, 11);
                c.maxLimitGrowth = rows.getDouble(12);
                c.maxLimitReduction = rows.getDouble(13);
                c.minLimitGrowth = rows.getDouble(14);
                c.minLimitReduction = rows.getDouble(15);
                c.worstAcceptedPd = rows.getDouble(16);
                c.maxAcceptedDso = rows.getInt(17);
                c.minAcceptedLimitUse = rows.getDouble(18);
                c.minAcceptedRwMargin = rows.getDouble(19);
                infoArray.add(c);
                c = null;
            }

            rows.close();
            st.close();
            st = conn.prepareStatement(get_optimizer_prospects);
            st.setInt(1, scenarioId);
            st.execute();
            rows = st.getResultSet();
            while (rows.next()) {
                c = new CustomerInfo();
                c.prospectId = rows.getInt(1);
                c.num = rows.getInt(2);
                c.turnover = rows.getDouble(3);
                c.projTurnover = c.turnover;
                c.maxPurchases = rows.getDouble(4);
                c.pd = rows.getDouble(5);
                c.profitMargin = rows.getDouble(6);
                c.creditMitigants = getDoubleArray(rows, 7);
                c.worstAcceptedPd = rows.getDouble(8);
                c.maxAcceptedDso = rows.getInt(9);
                c.minAcceptedLimitUse = rows.getDouble(10);
                c.minAcceptedRwMargin = rows.getDouble(11);
                infoArray.add(c);
                c = null;
            }
            rows.close();
            st.close();

            scenarioInfo.customersInfo = infoArray.toArray(new CustomerInfo[infoArray.size()]);
            infoArray = null;

        } catch (Exception e) {
            logger.error("scenarioId: {} error: {}", scenarioId, e);
            throw new DatabaseConnectionException("Error during getScenarioInfo");
        } finally {
            DbUtils.close(rows, st);
        }
        return scenarioInfo;
    }

    public boolean hasResults(int scenarioId, String schema) throws DatabaseConnectionException {
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             CallableStatement cs =
                     connection.prepareCall(has_results)) {
            //connection.prepareStatement("SET search_path TO "+schema+", public, core, ext")
            //        .execute();
            cs.registerOutParameter(1, Types.BOOLEAN); // Return value
            cs.setInt(2, scenarioId);

            cs.execute();
            return cs.getBoolean(1);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Error during hasResults");
        }
    }

    public void deleteScenarioResults(int scenarioId, String schema) throws DatabaseConnectionException {
        PreparedStatement st = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.prepareStatement("SET search_path TO "+schema+", public, core, ext").execute();
            st = conn.prepareStatement(d_customer_results);
            st.setInt(1, scenarioId);
            st.execute();
        } catch (Exception e) {
            logger.error("deleteScenarioResults scenarioId: {} error: {}" , scenarioId, e);
            throw new DatabaseConnectionException("Error during deleteScenarioResults");
        } finally {
            logger.info("Deleted results on scenario {} ", scenarioId);
            DbUtils.close(st);

        }
    }
    @Async("concurrentTaskExecutorDb")
    public void saveScenarioResults(int scenarioId,
                                    Parameters parameters,
                                    ScenarioInfo scenarioInfo,
                                    String schema, String user)
            throws DatabaseConnectionException {
        logger.info("{}", scenarioId);
        StringBuilder extraNotificationInfo = new StringBuilder() ;
        extraNotificationInfo.append("Scenario ").append(scenarioId).append(" finished.");
        CallableStatement  cs = null;
        CallableStatement cp = null;
        final int BATCH_SIZE = 1000;
        int countCustomers = 0;
        int countProspects = 0;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.prepareStatement("SET search_path TO "+schema+", public, core, ext")
                    .execute();
            conn.setAutoCommit(false);

            // Clear potential remaining data
            cs = conn.prepareCall(clear_scenario_results);
            cs.setInt(1, scenarioId);
            cs.execute();
            cs.close();

            // Save scenario results
            CustomerInfo[] c = scenarioInfo.customersInfo;
            cs = conn.prepareCall(insert_customer_results);
            cp = conn.prepareCall(insert_prospect_results);



            for (int i = 0; i <c.length; i++) {
                if (c[i].customerId == null) {	// Prospect
                    cp.setInt(1, scenarioId);
                    cp.setShort(2, (short)c[i].prospectId);
                    cp.setDouble(3, c[i].turnover);
                    cp.setDouble(4, c[i].maxCalcLimit);
                    cp.setDouble(5, c[i].proposedLimit);
                    cp.setDouble(6, c[i].pd);
                    cp.setDouble(7, c[i].lgd);
                    cp.setDouble(8, c[i].fundingCost);
                    cp.setDouble(9, c[i].capitalCushion);
                    cp.setDouble(10, c[i].rwMargin);
                    cp.setDouble(11, c[i].profitMargin);
                    cp.addBatch();
                    if (++countProspects % BATCH_SIZE == 0) cp.executeBatch();
                } else {						// Customer
                    cs.setInt(1, scenarioId);
                    cs.setString(2, c[i].customerId);
                    cs.setDouble(3, c[i].turnover);
                    cs.setDouble(4, c[i].projTurnover);
                    cs.setDouble(5, c[i].maxCalcLimit);
                    cs.setDouble(6, c[i].creditLimit);
                    cs.setDouble(7, c[i].proposedLimit);
                    cs.setDouble(8, c[i].pd);
                    cs.setDouble(9, c[i].lgd);
                    cs.setDouble(10, c[i].fundingCost);
                    cs.setDouble(11, c[i].capitalCushion);
                    cs.setDouble(12, c[i].rwMargin);
                    cs.setDouble(13, c[i].profitMargin);
                    cs.addBatch();
                    if (++countCustomers % BATCH_SIZE == 0) cs.executeBatch();
                }
            }
            cs.executeBatch();
            cs.close();
            cp.executeBatch();

            // Store intermediate results and validate scenario
            cs = conn.prepareCall(set_scenario_results_valid);
            cs.setInt(1, scenarioId);
            cs.execute();

            conn.commit();
        } catch (Exception e) {
            logger.error("saveScenarioResults scenarioId: {} error: {}" , scenarioId, e);
            extraNotificationInfo.append("With errors. ").append(e.getMessage());
            throw new DatabaseConnectionException("Error during saveScenarioResults");
        } finally {
            logger.info("Saved results on scenario {} with customers total {} and prospects total {}", scenarioId, countCustomers, countProspects);
            DbUtils.close(cs);
            DbUtils.close(cp);
            scenarioInfo = null;
            notificationService.notify(new Notification(extraNotificationInfo.toString()), user);

        }
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
    private double[] getDoubleArray(CachedRowSet rows, int parameterIndex) throws SQLException {
        Double[] d = (Double[])rows.getArray(parameterIndex).getArray();
        double[] r = new double[d.length];
        for (int i = 0; i < d.length; i++)
            r[i] = d[i].doubleValue();
        return r;
    }
    //CachedRowSet

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

}
