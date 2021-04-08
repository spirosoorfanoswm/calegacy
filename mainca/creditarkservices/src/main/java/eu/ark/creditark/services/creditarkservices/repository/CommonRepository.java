package eu.ark.creditark.services.creditarkservices.repository;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CommonRepository {
    public String searchPath(short contextId) throws DatabaseConnectionException;
    public void loadUsers() throws DatabaseConnectionException;
    public void loadContexts(List<String> users) throws DatabaseConnectionException;
    public void loadClienteleStatistics(Map<Short, ContextInfo> contextsMap,
                                        List<Short> contextIds,
                                        List<Date> snapshotDatesInput,
                                        List<Short> portfolioIds,
                                        List<Short> periods);
    public void runDefaultScenario(List<Short> contexts, List<Date> snapshotDates);
    public void loadCustomers(List<Short> contexts,
                              List<Date> snapshotDates,
                              List<Short> portfolios);
}
