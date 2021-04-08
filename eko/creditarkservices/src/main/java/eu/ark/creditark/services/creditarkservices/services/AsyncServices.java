package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import eu.ark.creditark.services.creditarkservices.shared.Parameters;
import eu.ark.creditark.services.creditarkservices.shared.ScenarioInfo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface AsyncServices {
    public CompletableFuture<String> asyncScenario(int scenarioId, String schema, String user, Date snapshotDate)
            throws ScenarioException, DatabaseConnectionException,
            ParseException, InterruptedException, CloneNotSupportedException;
    public CompletableFuture<String> deleteScenarioResults(int scenarioId, String schema) throws ScenarioException,
            DatabaseConnectionException;
    public void saveScenarioResults(int scenarioId,
                                    Parameters parameters,
                                    ScenarioInfo scenarioInfo,
                                    String schema, String user) throws DatabaseConnectionException;
}
