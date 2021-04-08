package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.input.ScenarioInputDto;
import eu.ark.creditark.services.creditarkservices.dto.input.ScenarioTemplateInputDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ScenariosDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioContainerUiDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioDeleteUIResponseDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUI;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUIDto;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

public interface ScenarioService {
	public ScenarioContainerUiDto getScenarios(ScenarioInputDto input) throws DatabaseConnectionException, ParseException;
	public ScenarioDeleteUIResponseDto deleteScenario(short contextId, int scenarioId) throws DatabaseConnectionException, ScenarioException;
	public int maxScenarioProspectIdInt(short scenarioId, short contextId) throws DatabaseConnectionException;
	public ScenarioUIDto createScenario(String user, ScenarioContainerUiDto scenarioContainerUiDto)
            throws ScenarioException,
            DatabaseConnectionException,
            ParseException,
            CloneNotSupportedException, InterruptedException;
	public ScenarioUIDto saveScenario(String user, ScenarioContainerUiDto scenarioContainerUiDto)
			throws ScenarioException,
			DatabaseConnectionException,
			ParseException,
			CloneNotSupportedException, InterruptedException;

	public ScenarioContainerUiDto template(ScenarioTemplateInputDto input)
			throws DatabaseConnectionException, ParseException;

	public void advertiseSystemScenario(int scenarioId, int contextId);

	/*public void asyncScenario(int scenarioId, String schema) throws ScenarioException,
		DatabaseConnectionException,
		ParseException, InterruptedException, CloneNotSupportedException;*/
}
