package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import eu.ark.creditark.services.creditarkservices.optimizer.LimitsOptimizer;
import eu.ark.creditark.services.creditarkservices.repository.ScenarioRepository;
import eu.ark.creditark.services.creditarkservices.services.optimizer.OptimizerService;
import eu.ark.creditark.services.creditarkservices.shared.Parameters;
import eu.ark.creditark.services.creditarkservices.shared.ScenarioInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncServicesImpl implements AsyncServices {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OptimizerService optimizerService;

    @Autowired
    private ScenarioRepository scenarioRepository;


    @Override
    @Async("concurrentTaskExecutor")
    public CompletableFuture<String> asyncScenario(int scenarioId, String schema, String user)
            throws ScenarioException,
            DatabaseConnectionException,
            ParseException, InterruptedException, CloneNotSupportedException {
        logger.info("Start {}", scenarioId);
        ScenarioInfo scenarioInfo = scenarioRepository.attachScenarioInfoOptimizer(scenarioId, schema);
        Parameters parameters  = scenarioRepository.attachOptimizerParams(scenarioId, schema);
        scenarioRepository.getScenarioInfo(scenarioId,
                parameters,
                schema,
                scenarioInfo);
        optimizerService.calcPortfolio(new LimitsOptimizer(scenarioInfo, parameters));
        scenarioRepository.saveScenarioResults(scenarioId, parameters, scenarioInfo, schema, user);
        scenarioInfo = null;
        logger.info("Start finish {}", scenarioId);
        return CompletableFuture.completedFuture(""+scenarioId);
    }

    @Override
    @Async("deleteScenarioTaskExecutorDb")
    public CompletableFuture<String> deleteScenarioResults(int scenarioId, String schema) throws DatabaseConnectionException {
        scenarioRepository.deleteScenarioResults(scenarioId, schema);
        return CompletableFuture.completedFuture(""+scenarioId);
    }

}
