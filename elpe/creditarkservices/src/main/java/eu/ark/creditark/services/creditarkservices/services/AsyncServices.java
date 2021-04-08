package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.concurrent.CompletableFuture;

public interface AsyncServices {
    public CompletableFuture<String> asyncScenario(int scenarioId, String schema, String user) throws ScenarioException,
            DatabaseConnectionException,
            ParseException, InterruptedException, CloneNotSupportedException;
    public CompletableFuture<String> deleteScenarioResults(int scenarioId, String schema) throws ScenarioException,
            DatabaseConnectionException;
}
