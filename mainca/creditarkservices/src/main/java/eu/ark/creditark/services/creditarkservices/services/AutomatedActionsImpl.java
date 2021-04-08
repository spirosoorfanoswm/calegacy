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
    private BusinessService businessService;

    @Autowired
    private ScenarioService scenarioService;

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

                ScenarioUIDto scenario = scenarioService.createScenario(systemScenarioUser, template);
                logger.info("Received scenario {} ", scenario.getScenarioId());
                scenarioService.advertiseSystemScenario(scenario.getScenarioId(), contextId);
            }

        } catch (Exception e) {

        }

    }

    public BusinessService getBusinessService() {
        return businessService;
    }

    public void setBusinessService(BusinessService businessService) {
        this.businessService = businessService;
    }

    public ScenarioService getScenarioService() {
        return scenarioService;
    }

    public void setScenarioService(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }
}
