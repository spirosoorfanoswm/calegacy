package eu.ark.creditark.services.creditarkservices.api;

import java.text.ParseException;
import java.util.List;

import eu.ark.creditark.services.creditarkservices.dto.input.ScenarioTemplateInputDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioContainerUiDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioDeleteUIResponseDto;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUI;
import eu.ark.creditark.services.creditarkservices.dto.scenario.ui.ScenarioUIDto;
import eu.ark.creditark.services.creditarkservices.exceptions.ScenarioException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import eu.ark.creditark.services.creditarkservices.dto.input.ScenarioInputDto;
import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.services.ScenarioService;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;

@RestController
@RequestMapping(path="/scenario")
public class ScenarioController {

	@Autowired
	private Environment env;

	@Autowired
    private ScenarioService scenarioService;
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @GetMapping(path = "/fetch")
	@PreAuthorize("hasAnyAuthority('ROLE_CLI_VIEWSCENARIOS')")
	@CrossOrigin(origins = "*")
    public ResponseEntity<ScenarioContainerUiDto> getScenarios(
			Authentication authentication,
					@RequestParam(name = "snapshotDate")  String snapshotDate,
					@RequestParam(name = "contextId") int contextId) throws DatabaseConnectionException, ParseException  {
		logger.info("user {} snapshotDate {} contextId {}",(null == authentication || null == authentication.getName())?
				env.getProperty("ca.default.user"):authentication.getName(),  snapshotDate, contextId);
    	return ResponseEntity.ok(
    	scenarioService.getScenarios(new ScenarioInputDto(DateUtils.stringToDate(snapshotDate, 
				GenerealConstants.DATE_FORMAT.getValue()), (null == authentication || null == authentication.getName())?
				env.getProperty("ca.default.user"):authentication.getName(), (short)contextId)));
    	
    }

	@GetMapping(path = "/template")
	@PreAuthorize("hasAnyAuthority('ROLE_CLI_EDITSCENARIOS')")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ScenarioContainerUiDto> template(
			Authentication authentication,
			@RequestParam(name = "snapshotDate")  String snapshotDate,
			@RequestParam(name = "contextId") int contextId,
			@RequestParam(name = "comparisonPeriod") int comparisonPeriod) throws DatabaseConnectionException, ParseException {
		logger.info("user:{} contextId:{} snapshotDate:{}", (null == authentication || null == authentication.getName())?
				env.getProperty("ca.default.user"):authentication.getName(),  contextId, snapshotDate);
    	ScenarioContainerUiDto response =
		scenarioService.template(new ScenarioTemplateInputDto(snapshotDate, contextId, comparisonPeriod,
				(null == authentication || null == authentication.getName())?
						env.getProperty("ca.default.user"):authentication.getName()));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping
	@PreAuthorize("hasAnyAuthority('ROLE_CLI_EDITSCENARIOS')")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ScenarioDeleteUIResponseDto> delete(Authentication authentication,
															  @RequestParam(value = "contextId") int contextId,
															  @RequestParam (value = "scenarioId") int scenarioId)
			throws DatabaseConnectionException, ScenarioException {
		logger.info("user:{} contextId:{} scenarioId:{}", (null == authentication || null == authentication.getName())?
				env.getProperty("ca.default.user"):authentication.getName(),  contextId, scenarioId);
		return ResponseEntity.ok(scenarioService.deleteScenario((short) contextId, (short) scenarioId));
	}


	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_CLI_EDITSCENARIOS')")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ScenarioUIDto> create(Authentication authentication,
					   @RequestBody ScenarioContainerUiDto scenarioContainerUiDto) throws
			DatabaseConnectionException,
			ParseException, ScenarioException,
			CloneNotSupportedException, InterruptedException {
		logger.info("scenarioContainerUiDto : {}", scenarioContainerUiDto);
    	return ResponseEntity.ok(scenarioService.createScenario((null == authentication || null == authentication.getName())?
				env.getProperty("ca.default.user"):authentication.getName(), scenarioContainerUiDto));

	}

	@PutMapping
	@PreAuthorize("hasAnyAuthority('ROLE_CLI_EDITSCENARIOS')")
	@CrossOrigin(origins = "*")
	public ResponseEntity<ScenarioUIDto> update(Authentication authentication,
					   @RequestBody ScenarioContainerUiDto scenarioContainerUiDto) throws
			DatabaseConnectionException, ParseException,
			ScenarioException, CloneNotSupportedException, InterruptedException {
		logger.info("user:{} scenarioContainerUiDto:{}", (null == authentication || null == authentication.getName())?
				env.getProperty("ca.default.user"):authentication.getName(), scenarioContainerUiDto);
		return ResponseEntity.ok(scenarioService.saveScenario((null == authentication || null == authentication.getName())?
				env.getProperty("ca.default.user"):authentication.getName(), scenarioContainerUiDto));
	}
    
	public ScenarioService getScenarioService() {
		return scenarioService;
	}

	public void setScenarioService(ScenarioService scenarioService) {
		this.scenarioService = scenarioService;
	}


}
