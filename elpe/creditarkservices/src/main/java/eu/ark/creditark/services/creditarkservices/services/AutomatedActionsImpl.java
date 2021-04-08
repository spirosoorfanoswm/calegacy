package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.input.ScenarioTemplateInputDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioContainerUiDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUIDto;
import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class AutomatedActionsImpl implements AutomatedActions {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${ca.system.scenario}")
    private boolean systemScenario;

    @Value("${ca.system.scenario.user}")
    private String systemScenarioUser;

    @Autowired
    BusinessService businessService;

    @Autowired
    ScenarioService scenarioService;

    @Override
    public void systemScenario(List<Short> contexts,
                               List<Date> snapshotDates) {
        logger.info("Starting daily system scenario");
        ScenarioTemplateInputDto scenarioTemplateInputDto = new ScenarioTemplateInputDto();
        try {
            if(systemScenario) {
                short contextId = contexts.get(0);
                ContextInfo contextInfo = businessService.getContextInfo(contexts.get(0), systemScenarioUser);
                int comparisonPeriod = contextInfo.getComparisonPeriods().get(0).getPeriod();
                String snapshotDate = DateUtils.dateToString(contextInfo.getSnapshotDates()[0], GenerealConstants.DATE_FORMAT.getValue());
                scenarioTemplateInputDto = new ScenarioTemplateInputDto();
                scenarioTemplateInputDto.setComparisonPeriod(comparisonPeriod);
                scenarioTemplateInputDto.setContextId(contextId);
                scenarioTemplateInputDto.setSnapshotDate(snapshotDate);
                scenarioTemplateInputDto.setUser(systemScenarioUser);
                logger.info("Invoke template with {} ", scenarioTemplateInputDto);
                ScenarioContainerUiDto template = scenarioService.template(scenarioTemplateInputDto);
                template.getScenarios().get(0).setScenarioName("system_scenario");
                logger.info("Received template {} ", template);
                logger.info("Invoke create with {} ", template);
                ScenarioUIDto scenario = scenarioService.createScenario(systemScenarioUser, template);
                logger.info("Received scenario {} ", scenario);
                scenarioService.advertiseSystemScenario(scenario.getScenarioId());
            }

        } catch (Exception e) {

        }

    }
}
