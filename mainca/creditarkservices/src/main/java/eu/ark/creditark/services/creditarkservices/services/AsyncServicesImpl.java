package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.ExportFileEmptyResponse;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomersInputDto;
import eu.ark.creditark.services.creditarkservices.dto.notification.Notification;
import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import eu.ark.creditark.services.creditarkservices.optimizer.LimitsOptimizer;
import eu.ark.creditark.services.creditarkservices.repository.ScenarioRepository;
import eu.ark.creditark.services.creditarkservices.services.optimizer.OptimizerService;
import eu.ark.creditark.services.creditarkservices.shared.Parameters;
import eu.ark.creditark.services.creditarkservices.shared.ScenarioInfo;
import eu.ark.creditark.services.creditarkservices.shared.ScenarioParams;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncServicesImpl implements AsyncServices {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OptimizerService optimizerService;

    @Autowired
    private NotificationService notificationService;

    private ScenarioRepository scenarioRepository;

    private ExportService exportService;

    @Value("${app.customers.report.create.scenario}")
    private boolean createScenarioReport;

    @Value("${ca.system.scenario.user}")
    private String systemScenarioUser;

    @Override
    @Async("concurrentTaskExecutor")
    public CompletableFuture<String> asyncScenario(int scenarioId, String schema, String user, Date snapshotDate)
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
        saveScenarioResults(scenarioId, parameters, scenarioInfo, schema, user);
        scenarioInfo = null;
        logger.info("Start finish {}", scenarioId);
        return CompletableFuture.completedFuture(""+scenarioId);
    }

    @Async("concurrentTaskExecutorDb")
    public void saveScenarioResults(int scenarioId,
                                    Parameters parameters,
                                    ScenarioInfo scenarioInfo,
                                    String schema, String user)
            throws DatabaseConnectionException {
        logger.info("Async saveScenarioResults {} ", scenarioId);
        StringBuilder extraNotificationInfo = scenarioRepository.saveScenarioResults(scenarioId, parameters, scenarioInfo, schema, user);
        if(createScenarioReport && !user.equals(systemScenarioUser)) {
            ScenarioParams scenarioParams = scenarioRepository.fetchScenarioParameters(scenarioId, schema);
            logger.info("Async check for save results {} ", scenarioId);
            try {
                ExportFileEmptyResponse response =
                        exportService.handleResponseType(new CustomersInputDto(
                                        scenarioParams.getSnapshotDate(),
                                        (short) scenarioParams.getContextId(), scenarioId, -1, -1, false, -1, -1),
                                user);

                if (!response.isExists()) {
                    exportService.exportCustomers(new CustomersInputDto(
                                    scenarioParams.getSnapshotDate(),
                                    (short) scenarioParams.getContextId(), scenarioId, -1, -1, false, -1, -1),
                            user);

                }
                extraNotificationInfo.append(" .Scenario customers report is being created, You will notified when completed");

            } catch (IOException e) {
                logger.error("Export of results for scenario {1} was not possible", scenarioId);
            }
        }
        notificationService.notify(new Notification(extraNotificationInfo.toString()), user);

    }

    @Override
    @Async("deleteScenarioTaskExecutorDb")
    public CompletableFuture<String> deleteScenarioResults(int scenarioId, String schema) throws DatabaseConnectionException {
        scenarioRepository.deleteScenarioResults(scenarioId, schema);
        return CompletableFuture.completedFuture(""+scenarioId);
    }

    public OptimizerService getOptimizerService() {
        return optimizerService;
    }

    public void setOptimizerService(OptimizerService optimizerService) {
        this.optimizerService = optimizerService;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public ScenarioRepository getScenarioRepository() {
        return scenarioRepository;
    }

    @Autowired
    public void setScenarioRepository(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }

    public ExportService getExportService() {
        return exportService;
    }

    @Autowired
    public void setExportService(ExportService exportService) {
        this.exportService = exportService;
    }


}
