package eu.ark.creditark.services.creditarkservices.api;

import eu.ark.creditark.services.creditarkservices.dto.ExportFileEmptyResponse;
import eu.ark.creditark.services.creditarkservices.dto.input.ClienteleStatisticsInputDto;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomersInputDto;
import eu.ark.creditark.services.creditarkservices.enums.GenerealConstants;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.services.BusinessService;
import eu.ark.creditark.services.creditarkservices.services.ExportService;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;
import io.swagger.annotations.Api;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;

@RestController
@RequestMapping(path="/export")
@Api(value = "/export", description = "Export services")
@PreAuthorize("hasAnyAuthority('ROLE_MONITOR')")
@CrossOrigin(origins = "http://localhost:4200")
public class ExportController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment env;

    @Autowired
    ExportService exportService;

    @Autowired
    BusinessService businessService;
    @GetMapping(path = "/customers")
    public ResponseEntity<ExportFileEmptyResponse> customers(
            Authentication authentication,
            @RequestParam(name = "snapshotDate") String snapshotDate,
            @RequestParam(name = "scenarioId") int scenarioId,
            @RequestParam(name = "portfolioId") int portfolioId,
            @RequestParam(name = "contextId") short contextId)
            throws DatabaseConnectionException, ParseException, IOException {
        logger.info("customers export user:{} snapshotDate:{} scenarioId:{} portfolioId:{} contextId:{}", (null == authentication || null == authentication.getName()) ?
                        env.getProperty("ca.default.user") : authentication.getName(),
                snapshotDate,
                scenarioId,
                portfolioId,
                contextId);

        ExportFileEmptyResponse response =
                exportService.handleResponseType(new CustomersInputDto(
                                DateUtils.stringToDate(snapshotDate,
                                        GenerealConstants.DATE_FORMAT.getValue()),
                                contextId, scenarioId, portfolioId, -1, false, -1, -1),
                        (null == authentication || null == authentication.getName()) ?
                                env.getProperty("ca.default.user") : authentication.getName());

        if (!response.isExists()) {
            exportService.exportCustomers(new CustomersInputDto(
                            DateUtils.stringToDate(snapshotDate,
                                    GenerealConstants.DATE_FORMAT.getValue()),
                            contextId, scenarioId, portfolioId, -1, false, -1, -1),
                    (null == authentication || null == authentication.getName()) ?
                            env.getProperty("ca.default.user") : authentication.getName());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.ok(response);
        }

    }

    @GetMapping(path = "/downloadfile")
    public ResponseEntity download(@RequestParam(name = "file") String file)
            throws DatabaseConnectionException, ParseException, IOException {
        logger.info("download file:{} ", file);

        return exportService.handleDownloadResponse(file);
    }

    @GetMapping(path = "/clienteledistribution")
    public ResponseEntity<ExportFileEmptyResponse> clienteleDistribution(Authentication authentication,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam(name = "snapshotDate")  String snapshotDate,
                                      @RequestParam(name = "portfolioId") int portfolioId,
                                      @RequestParam(name = "comparisonPeriod") int comparisonPeriod,
                                      @RequestParam(name = "contextId") int contextId) throws ParseException, DatabaseConnectionException, DRException, IOException {

        StringBuilder filename = new StringBuilder("riskprofile_");
        filename.append(snapshotDate).append("_")
                .append(portfolioId).append("_")
                .append(comparisonPeriod).append("_")
                .append(contextId).append(".pdf");
        logger.info("report clienteledistribution user:{} snapshotDate:{} portfolioId:{} comparisonPeriod:{} contextId:{}",
                (null == authentication || null == authentication.getName())?
                        env.getProperty("ca.default.user"):authentication.getName() ,
                snapshotDate,
                portfolioId,
                comparisonPeriod,
                contextId);

        ExportFileEmptyResponse resp = exportService.fileExists(filename.toString());
        if(!resp.isExists()) {
            return  ResponseEntity.ok(exportService.clienteleDistributionReport(
                    businessService.clienteleDistribution(new ClienteleStatisticsInputDto(DateUtils.stringToDate(snapshotDate,
                            GenerealConstants.DATE_FORMAT.getValue()),
                                    (short)portfolioId,
                                    (short)comparisonPeriod,
                                    (short)contextId),
                            (null == authentication || null == authentication.getName())?
                                    env.getProperty("ca.default.user"):authentication.getName()), filename.toString()));

        }

        /*try(  OutputStream out = response.getOutputStream()) {
            JasperConcatenatedReportBuilder jrb = new JasperConcatenatedReportBuilder();
            try {
                response.setContentType("application/pdf; name="+filename.toString());
                response.setHeader("Content-Disposition","inline; filename="+filename.toString());
                jrb = exportService.clienteleDistributionReport(
                        businessService.clienteleDistribution(new ClienteleStatisticsInputDto(DateUtils.stringToDate(snapshotDate,
                                GenerealConstants.DATE_FORMAT.getValue()),
                                        (short)portfolioId,
                                        (short)comparisonPeriod,
                                        (short)contextId),
                                (null == authentication || null == authentication.getName())?
                                        env.getProperty("ca.default.user"):authentication.getName()));
            } catch (Exception e) {
                logger.info("");
            }

            jrb.toPdf(out);
        } finally {

        }*/


        return ResponseEntity.ok(resp);
    }

    @GetMapping(path = "/statistics")
    public ResponseEntity<ExportFileEmptyResponse> statistics(Authentication authentication,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam(name = "snapshotDate")  String snapshotDate,
                                      @RequestParam(name = "portfolioId") int portfolioId,
                                      @RequestParam(name = "comparisonPeriod") int comparisonPeriod,
                                      @RequestParam(name = "contextId") int contextId) throws DRException, IOException, ParseException, DatabaseConnectionException {

        logger.info("report clienteledistribution user:{} snapshotDate:{} portfolioId:{} comparisonPeriod:{} contextId:{}",
                (null == authentication || null == authentication.getName())?
                        env.getProperty("ca.default.user"):authentication.getName() ,
                snapshotDate,
                portfolioId,
                comparisonPeriod,
                contextId);
        StringBuilder filename = new StringBuilder("statistics_");
        filename.append(snapshotDate).append("_")
                .append(portfolioId).append("_")
                .append(comparisonPeriod).append("_")
                .append(contextId).append(".pdf");
        ExportFileEmptyResponse resp = exportService.fileExists(filename.toString());
        if(!resp.isExists()) {
            ResponseEntity.ok(exportService.clienteleStatisticsReport( businessService.clienteleStatistics(
                    new ClienteleStatisticsInputDto(
                            DateUtils.stringToDate(snapshotDate,
                                    GenerealConstants.DATE_FORMAT.getValue()),
                            (short)portfolioId,
                            (short)comparisonPeriod,
                            (short)contextId)),
                    businessService.clienteleStatisticsGraph(
                            new ClienteleStatisticsInputDto(DateUtils.stringToDate(
                                    snapshotDate,
                                    GenerealConstants.DATE_FORMAT.getValue()),
                                    (short)portfolioId,
                                    (short)comparisonPeriod,
                                    (short)contextId)), filename.toString()));
            resp.setExists(true);
            resp.setFileName(filename.toString());
        }
        return ResponseEntity.ok(resp);
        /*try(OutputStream out = response.getOutputStream()) {
            JasperConcatenatedReportBuilder jrb = new JasperConcatenatedReportBuilder();
            logger.info("report clienteledistribution user:{} snapshotDate:{} portfolioId:{} comparisonPeriod:{} contextId:{}",
                    (null == authentication || null == authentication.getName())?
                            env.getProperty("ca.default.user"):authentication.getName() ,
                    snapshotDate,
                    portfolioId,
                    comparisonPeriod,
                    contextId);
            try {
                StringBuilder filename = new StringBuilder("statistics_");
                filename.append(snapshotDate).append("_")
                        .append(portfolioId).append("_")
                        .append(comparisonPeriod).append("_")
                        .append(contextId).append(".pdf");
                response.setContentType("application/pdf; name="+filename.toString());
                response.setHeader("Content-Disposition","inline; filename="+filename.toString());


                jrb = exportService.clienteleStatisticsReport( businessService.clienteleStatistics(
                        new ClienteleStatisticsInputDto(
                                DateUtils.stringToDate(snapshotDate,
                                        GenerealConstants.DATE_FORMAT.getValue()),
                                (short)portfolioId,
                                (short)comparisonPeriod,
                                (short)contextId)),
                        businessService.clienteleStatisticsGraph(
                                new ClienteleStatisticsInputDto(DateUtils.stringToDate(
                                        snapshotDate,
                                        GenerealConstants.DATE_FORMAT.getValue()),
                                        (short)portfolioId,
                                        (short)comparisonPeriod,
                                        (short)contextId)));
            } catch (Exception e) {
                logger.info("");
            }

            jrb.toPdf(out);

        } finally {

        }*/

    }


}
