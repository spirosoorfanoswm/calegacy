package eu.ark.creditark.services.creditarkservices.api;

import eu.ark.creditark.services.creditarkservices.dto.*;
import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleStatisticsInputDto;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomerInputDto;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomerInputSearchDto;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomersInputDto;
import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import eu.ark.creditark.services.creditarkservices.enums.SearchBy;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.services.BusinessService;
import eu.ark.creditark.services.creditarkservices.services.UserService;
import eu.ark.creditark.services.creditarkservices.shared.ConfigInfo;
import eu.ark.creditark.services.creditarkservices.shared.ContextInfo;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path="/repository")
@Api(value = "/repository", description = "Operations on credit monitor")
@PreAuthorize("hasAnyAuthority('ROLE_MONITOR')")
@CrossOrigin(origins = "*")
public class BusinessController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment env;

    @Autowired
    BusinessService businessService;

    @Autowired
    UserService userService;

    @GetMapping(path = "/configinfo")
    @ApiOperation(value = "Retrieves the configuration info",
            response = ConfigInfo.class,
            responseContainer = "ResponseEntity")

    public ResponseEntity<ConfigInfo> getConfigInfo(Authentication authentication) throws DatabaseConnectionException {

        logger.info("configinfo called with {}", (null == authentication || null == authentication.getName())?
                env.getProperty("ca.default.user"):authentication.getName());


        userService.insertIfNotExists((null == authentication || null == authentication.getName())?
                env.getProperty("ca.default.user"):authentication.getName());

        return ResponseEntity.ok(businessService.getConfigInfo((null == authentication || null == authentication.getName())?
                env.getProperty("ca.default.user"):authentication.getName()));
    }

    @GetMapping(path = "/contextinfo")
    @ApiOperation(value = "Retrieves the context info",
            response = ContextInfo.class,
            responseContainer = "ResponseEntity")
    public ResponseEntity<ContextInfo> getContextInfo(Authentication authentication, @RequestParam(name = "contextId") int contextId) throws DatabaseConnectionException  {
        logger.info("clientelestatisticsgraph user: {} contextId: {}",  (null == authentication || null == authentication.getName())?
                env.getProperty("ca.default.user"):authentication.getName(), contextId);
        return ResponseEntity.ok(businessService.getContextInfo(contextId, (null == authentication || null == authentication.getName())?
                env.getProperty("ca.default.user"):authentication.getName()));
    }

    @GetMapping(path = "/clientelestatistics")
    public ResponseEntity<ClienteleStatisticsDto> clienteleStatistics(
            Authentication authentication,
            @RequestParam(name = "snapshotDate")  String snapshotDate,
            @RequestParam(name = "portfolioId")  int portfolioId,
            @RequestParam(name = "comparisonPeriod")  int comparisonPeriod,
            @RequestParam(name = "contextId")  int contextId)
            throws DatabaseConnectionException, ParseException {
        logger.info("clientelestatistics user:{} snapshotDate:{} portfolioId:{} comparisonPeriod:{} contextId:{}",  (null == authentication || null == authentication.getName())?
                env.getProperty("ca.default.user"):authentication.getName(),
                snapshotDate,
                portfolioId,
                comparisonPeriod,
                contextId);
        return  ResponseEntity.ok(businessService.clienteleStatistics(
                new ClienteleStatisticsInputDto(
                        DateUtils.stringToDate(snapshotDate,
                                GenerealConstants.DATE_FORMAT.getValue()),
                        (short)portfolioId,
                        (short)comparisonPeriod,
                        (short)contextId)));
    }

    @GetMapping(path = "/clientelestatisticsgraph")
    public ResponseEntity<ClienteleStatisticsGraphDto> clienteleStatisticsGraph(
            Authentication authentication,
            @RequestParam(name = "snapshotDate") String snapshotDate,
            @RequestParam(name = "portfolioId") int portfolioId,
            @RequestParam(name = "comparisonPeriod") int comparisonPeriod,
            @RequestParam(name = "contextId") int contextId)
            throws DatabaseConnectionException, ParseException {
        logger.info("clientelestatisticsgraph user:{} snapshotDate:{} portfolioId:{} comparisonPeriod:{} contextId:{}",
                (null == authentication || null == authentication.getName())?
                env.getProperty("ca.default.user"):authentication.getName(),
                snapshotDate, portfolioId,
                comparisonPeriod,
                contextId);
        return ResponseEntity.ok(businessService.clienteleStatisticsGraph(
                new ClienteleStatisticsInputDto(DateUtils.stringToDate(
                        snapshotDate,
                        GenerealConstants.DATE_FORMAT.getValue()),
                        (short)portfolioId,
                        (short)comparisonPeriod,
                        (short)contextId)));
    }
    @GetMapping(path = "/clienteledistribution")
    public ResponseEntity<ClienteleDistributionDto> clienteleDistribution(Authentication authentication,
                                                                          @RequestParam(name = "snapshotDate")  String snapshotDate,
                                                                          @RequestParam(name = "portfolioId") int portfolioId,
                                                                          @RequestParam(name = "comparisonPeriod") int comparisonPeriod,
                                                                          @RequestParam(name = "contextId") int contextId) throws ParseException, DatabaseConnectionException {
        logger.info("clienteledistribution user:{} snapshotDate:{} portfolioId:{} comparisonPeriod:{} contextId:{}",
                (null == authentication || null == authentication.getName())?
                env.getProperty("ca.default.user"):authentication.getName() ,
                snapshotDate,
                portfolioId,
                comparisonPeriod,
                contextId);
        return ResponseEntity.ok(businessService.clienteleDistribution(new ClienteleStatisticsInputDto(DateUtils.stringToDate(snapshotDate,
                GenerealConstants.DATE_FORMAT.getValue()),
                (short)portfolioId,
                (short)comparisonPeriod,
                (short)contextId),
                (null == authentication || null == authentication.getName())?
                env.getProperty("ca.default.user"):authentication.getName()));
    }

    @GetMapping(path = "/customers")
    public ResponseEntity<CustomerListDto> customers(
            Authentication authentication,
            @RequestParam(name = "snapshotDate") String snapshotDate,
            @RequestParam(name = "scenarioId") int scenarioId,
            @RequestParam(name = "portfolioId") int portfolioId,
            @RequestParam(name = "sortColumn") int sortColumn,
            @RequestParam(name = "sortDescending") boolean sortDescending,
            @RequestParam(name = "offset") int offset,
            @RequestParam(name = "length") int length,
            @RequestParam(name = "contextId") short contextId)
            throws  DatabaseConnectionException, ParseException {
        logger.info("customers all user:{} snapshotDate:{} scenarioId:{} portfolioId:{} sortColumn:{} sortDescending:{} offset:{} length:{} contextId:{}", (null == authentication || null == authentication.getName())?
                env.getProperty("ca.default.user"):authentication.getName() ,
                snapshotDate,
                scenarioId,
                portfolioId,
                sortColumn,
                sortDescending,
                offset, length,
                contextId);
        return ResponseEntity.ok(businessService.customers(new CustomersInputDto(
                DateUtils.stringToDate(snapshotDate,
                        GenerealConstants.DATE_FORMAT.getValue()),
                contextId, scenarioId, portfolioId, sortColumn, sortDescending, offset, length)));
    }

    @GetMapping(path = "/customers/search/{searchKey}")
    public ResponseEntity<List<CustomerEntityDetailsDto>> searchCustomer(
            Authentication authentication,
            @PathVariable(name = "searchKey") String searchKey,
           @RequestParam(name = "snapshotDate") String snapshotDate,
           @RequestParam(name = "portfolioId") int portfolioId,
           @RequestParam(name = "contextId") int contextId,
           @RequestParam(name = "searchBy") SearchBy searchBy,
            @RequestParam(name = "scenarioId", required = false) String scenarioId) throws DatabaseConnectionException, ParseException {
         logger.info("customers search  user:{} searchKey:{} snapshotDate:{} portfolioId:{} contextId:{} searchBy:{}", (null == authentication || null == authentication.getName())?
                 env.getProperty("ca.default.user"):authentication.getName(),
                 searchKey,
                 snapshotDate,
                 portfolioId,
                 contextId,
                 searchBy);
         return ResponseEntity.ok( businessService.search(new CustomerInputSearchDto(null, DateUtils.stringToDate(
                 snapshotDate,
                 GenerealConstants.DATE_FORMAT.getValue()), 0,  contextId, searchBy, portfolioId, searchKey, null==scenarioId?-1:Integer.parseInt(scenarioId))));
    }


    @GetMapping(path = "/customers/{customerId}")
    public ResponseEntity<CustomerDetailsDto> customer(Authentication authentication,
                                                       @PathVariable(value="customerId") String customerId,
                                                       @RequestParam(name = "snapshotDate") String snapshotDate,
                                                       @RequestParam(name = "period") int period,
                                                       @RequestParam(name = "contextId") int contextId,
                                                       @RequestParam(name = "scenarioId", required = false) String scenarioId)
            throws  DatabaseConnectionException, ParseException {
        logger.info("customers user:{} customerId:{} snapshotDate:{} period:{} contextId:{} scenarioId:{}", (null == authentication || null == authentication.getName())?
                env.getProperty("ca.default.user"):authentication.getName() ,
                customerId,
                snapshotDate,
                period,
                contextId,
                scenarioId);
        return ResponseEntity.ok(businessService.customer(new CustomerInputDto(customerId, DateUtils.stringToDate(
                snapshotDate,
                GenerealConstants.DATE_FORMAT.getValue()), period, contextId, null==scenarioId?-1:Integer.parseInt(scenarioId)), (null == authentication || null == authentication.getName())?
                env.getProperty("ca.default.user"):authentication.getName()));
    }
}
