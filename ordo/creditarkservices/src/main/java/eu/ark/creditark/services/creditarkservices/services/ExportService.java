package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.dto.*;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomersInputDto;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ExportService {
    public void exportCustomers(CustomersInputDto input, String user) throws DatabaseConnectionException, IOException;
    public ExportFileEmptyResponse handleResponseType(CustomersInputDto input, String user) throws DatabaseConnectionException, IOException;
    public ResponseEntity handleDownloadResponse(String file) throws IOException;


    public ExportFileEmptyResponse clienteleDistributionReport(
            ClienteleDistributionDto input, String filename) throws IOException, DRException;
    public ExportFileEmptyResponse clienteleStatisticsReport(
            ClienteleStatisticsDto clienteleStatisticsDto,
            ClienteleStatisticsGraphDto statisticsGraphDto, String filename) throws IOException, DRException;


    public ExportFileEmptyResponse customerDetailsReport(
            CustomerDetailsDto input, String filename) throws IOException, DRException;

    public ExportFileEmptyResponse fileExists(String filename);
    public String delete(String filename);
}
