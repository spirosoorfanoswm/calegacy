package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.dto.*;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomersInputDto;
import eu.ark.creditark.services.creditarkservices.dto.input.CustomersNumInputDto;
import eu.ark.creditark.services.creditarkservices.dto.notification.Notification;
import eu.ark.creditark.services.creditarkservices.dto.reports.ReportInput;
import eu.ark.creditark.services.creditarkservices.enums.DtoTransformationType;
import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.repository.BusinessRepository;
import eu.ark.creditark.services.creditarkservices.repository.ScenarioRepository;
import eu.ark.creditark.services.creditarkservices.services.transformator.reports.ReportTransformatorFactory;
import eu.ark.creditark.services.creditarkservices.shared.Customer;
import eu.ark.creditark.services.creditarkservices.shared.ScenarioParams;
import eu.ark.creditark.services.creditarkservices.utils.DateUtils;
import eu.ark.creditark.services.creditarkservices.utils.DtoGenUtils;

import eu.ark.creditark.services.creditarkservices.utils.ExportUtils;
import eu.ark.creditark.services.creditarkservices.utils.StringGenUtils;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ExportServiceImpl implements ExportService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private BusinessRepository repositoryBucket;

    @Autowired
    private UtilService utilService;

    @Autowired
    private ReportTransformatorFactory reportTransformatorFactory;

    @Autowired
    private PdfReportBuilderService pdfReportBuilderService;


    private  ScenarioRepository scenarioRepository;

    @Value("${app.customers.report.max.rows}")
    private String scenarioMaxRows;

    @Value("${app.customers.report.file.path}")
    private String reportLocation;

    @Value("${app.customers.report.file.path}")
    private String reportFilePath;

    @Autowired
    private AppPropertiesConfig appProperties;


    @Value("${ca.system.scenario.user}")
    private String systemScenarioUser;

    @Override
    @Async("createCustomersReportTestExecutor")
    public void exportCustomers(CustomersInputDto input, String user) throws DatabaseConnectionException, IOException {
        logger.info("input : {} user :", input, user);
        boolean scenario = input.getScenarioId()>0;
        input.setSortColumn(6);
        String schema = utilService.getSchema(input.getContextId());
        Integer scenarioPrefix = null;
        if(scenario) {
            ScenarioParams scenarioParams = scenarioRepository.fetchScenarioParameters(input.getScenarioId(), schema);
            if(null!=scenarioParams) scenarioPrefix = scenarioParams.hashCode();
        }
        StringBuilder exportFilenamePrefix= ExportUtils.createExportFileNamePrefix(input, scenarioPrefix, user);
        StringBuilder exportZipName= new StringBuilder(exportFilenamePrefix).append(".zip");
        if((new File(exportZipName.toString())).exists()) {
            return;
        }
        int maxRows = Integer.parseInt(scenarioMaxRows);


        int numOfCustomers = repositoryBucket.getCustomersNum(
                new CustomersNumInputDto(input.getSnapshotDate(),input.getScenarioId(), input.getPortfolioId(), input.getContextId()), schema);

        StringBuilder exportFileName = new StringBuilder();
        if(numOfCustomers<=maxRows) {
            input.setOffset(0);
            input.setLength(maxRows);
            exportFileName.append(exportFilenamePrefix.toString()).append(0).append("_").append(maxRows).append(".csv");
            generateReport(repositoryBucket.getCustomersExport(input, schema),
                    exportFileName.toString(), scenario);
        } else {
            int lastRows = numOfCustomers%maxRows;
            int times = numOfCustomers/maxRows;

            for(int i=0; i<times; i++) {
                exportFileName = new StringBuilder();
                exportFileName.append(exportFilenamePrefix.toString()).append(i*maxRows).append("_").append((i+1)*maxRows).append(".csv");
                input.setOffset((i)*maxRows);
                input.setLength(maxRows);
                generateReport(repositoryBucket.getCustomersExport(input, schema),exportFileName.toString(), scenario);
            }


            if(lastRows>0) {
                input.setOffset((times)*maxRows);
                input.setLength(maxRows);
                exportFileName = new StringBuilder();
                exportFileName.append(exportFilenamePrefix.toString()).append((times) * maxRows).append("_").append((times * maxRows) + lastRows).append(".csv");
                generateReport(repositoryBucket.getCustomersExport(input, schema),exportFileName.toString(), scenario);
            }
        }

       List<String> files =  Files.list(Paths.get(reportFilePath))
                .map( x -> x.getFileName().toString()).collect(Collectors.toList())
        .stream().filter(item -> item.contains(exportFilenamePrefix.toString())).collect(Collectors.toList());
        files.stream().forEach(fi -> logger.info(fi));
        createZip(input, exportZipName.toString(), files, user);
    }

    @Override
    public ExportFileEmptyResponse handleResponseType(CustomersInputDto input, String user) throws DatabaseConnectionException, IOException {
        logger.info("input : {} user :", input, user);
        boolean scenario = input.getScenarioId()>0;
        input.setSortColumn(6);
        String schema = utilService.getSchema(input.getContextId());
        Integer scenarioPrefix = null;
        if(scenario) {
            ScenarioParams scenarioParams = scenarioRepository.fetchScenarioParameters(input.getScenarioId(), schema);
            if(null!=scenarioParams) scenarioPrefix = scenarioParams.hashCode();
        }
        StringBuilder exportFilenamePrefix= ExportUtils.createExportFileNamePrefix(input, scenarioPrefix, user);
        StringBuilder exportZipName= new StringBuilder(exportFilenamePrefix).append(".zip");
        ExportFileEmptyResponse response = new ExportFileEmptyResponse(user, new Date());
        if((new File(reportFilePath+File.separator+File.separator + exportZipName.toString())).exists()) {
            response.setMessage(appProperties.getMessages().get(0));
            response.setExists(true);
            response.setFileName(exportZipName.toString());
        } else {
            List<String> files =  Files.list(Paths.get(reportFilePath+File.separator))
                    .map( x -> x.getFileName().toString()).collect(Collectors.toList())
                    .stream().filter(item -> item.contains(exportFilenamePrefix.toString())).collect(Collectors.toList());
            if(files.size()>0) {
                response.setPending(true);
                response.setMessage(appProperties.getMessages().get(1));
            } else {
                response.setMessage(appProperties.getMessages().get(2));
            }
        }
        return response;
    }



    @Override
    public ResponseEntity handleDownloadResponse(String filaname)
            throws IOException {

        File file = new File(reportFilePath+File.separator+File.separator + filaname);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+filaname);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);

    }

    @Override
    public ExportFileEmptyResponse clienteleStatisticsReport(
            ClienteleStatisticsDto clienteleStatisticsDto,
            ClienteleStatisticsGraphDto statisticsGraphDto, String filename) throws IOException, DRException {
        ExportFileEmptyResponse response = new ExportFileEmptyResponse();
        JasperConcatenatedReportBuilder file =  pdfReportBuilderService.clienteleStatisticsReport(
        reportTransformatorFactory.getTransformatorFactory(DtoTransformationType.CLIENT_STATISTICS_REPORT)
                .transform((new ReportInput.Builder())
                        .withClienteleStatisticsDto(clienteleStatisticsDto)
                        .withClienteleStatisticsGraphDto(statisticsGraphDto)
                        .build()));
        try(OutputStream out = new FileOutputStream(reportFilePath+File.separator+File.separator+ filename)) {
            file.toPdf(out);
            response.setExists(true);
            response.setFileName(filename);
        }
        return response;
    }

    public ExportFileEmptyResponse customerDetailsReport(
            CustomerDetailsDto input, String filename) throws IOException, DRException {
        ExportFileEmptyResponse response = new ExportFileEmptyResponse();
        JasperConcatenatedReportBuilder file =  pdfReportBuilderService.customerDetailsReport(
                reportTransformatorFactory.getTransformatorFactory(DtoTransformationType.CUSTOMER_DETAILS_REPORT)
                        .transform((new ReportInput.Builder())
                                .withCustomerDetailsDto(input)
                                .build()));
        try(OutputStream out = new FileOutputStream(
                reportFilePath+File.separator+File.separator+ filename)) {


            file.toPdf(out);
            response.setExists(true);
            response.setFileName(filename);
        }
        return response;
    }

    @Override
    public ExportFileEmptyResponse fileExists(String filename) {
        ExportFileEmptyResponse response = new ExportFileEmptyResponse();
        if((new File(reportFilePath+File.separator+File.separator + filename)).exists()) {
            response.setMessage(appProperties.getMessages().get(0));
            response.setExists(true);
            response.setFileName(filename);
        }
        return response;
    }

    @Override
    public String delete(String filename) {
        if((new File(reportFilePath+File.separator+File.separator + filename)).exists()) {
            (new File(reportFilePath+File.separator+File.separator + filename)).delete();
        }
        return filename;
    }

    @Override
    public ExportFileEmptyResponse clienteleDistributionReport(ClienteleDistributionDto input,
                                                               String filename) throws IOException, DRException {
        ExportFileEmptyResponse response = new ExportFileEmptyResponse();
        JasperConcatenatedReportBuilder file =  pdfReportBuilderService.clienteleDistributionReport(reportTransformatorFactory
                .getTransformatorFactory(DtoTransformationType.CLIENT_DISTRIBUTION_REPORT)
                .transform((new ReportInput.Builder()).withClienteleDistributionDto(input).build()));
        try(OutputStream out = new FileOutputStream(reportFilePath+File.separator+File.separator+filename)) {
            file.toPdf(out);
            response.setExists(true);
            response.setFileName(filename);
        }
        return response;
    }

    private synchronized void deleteFiles(CustomersInputDto input, List<String> srcFiles, String username) {
        for(String file:srcFiles) {
            if((new File(reportFilePath+File.separator+File.separator + file).exists()))
                (new File(reportFilePath+File.separator+File.separator + file)).delete();
        }
        if(input.getScenarioId()>0) {
            notificationService.notify(new Notification(
                    StringGenUtils.format(appProperties.getMessages().get(4),
                            new String[]{
                                    Integer.toString(input.getScenarioId()),
                                    DateUtils.dateToString(input.getSnapshotDate()),
                                    username})), username);
        } else {
            notificationService.notify(new Notification(
                    StringGenUtils.format(appProperties.getMessages().get(3),
                            new String[]{
                                    DateUtils.dateToString(input.getSnapshotDate()),
                                    input.getPortfolioId()==-1?"All portfolios":Integer.toString(input.getPortfolioId()),
                                    username})), username);
        }

    }

    private synchronized void createZip(CustomersInputDto input, String filename,
                                        List<String> srcFiles,
                                        String username) throws IOException {
        try (  FileOutputStream fos = new FileOutputStream(reportFilePath+File.separator+File.separator + filename);
               ZipOutputStream zipOut = new ZipOutputStream(fos);) {
            for (final String srcFile : srcFiles) {
                logger.info(reportFilePath+File.separator+File.separator + srcFile);
                final File fileToZip = new File(reportFilePath+File.separator+File.separator + srcFile);
                final FileInputStream fis = new FileInputStream(fileToZip);
                final ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOut.putNextEntry(zipEntry);

                final byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
            }
            zipOut.close();
        } finally {
            deleteFiles(input, srcFiles, username);
        }

    }

    private synchronized void generateReport(List<Customer> customers,
                                String filename, boolean scenario) throws IOException {
        String[] customerData = null;
        String[] headers = null;
        if(scenario) {
            customerData = new String[10];
            headers = new String[]{
                    appProperties.getCustomerSnapshotData().get(14),
                    appProperties.getCustomerSnapshotData().get(0),
                    appProperties.getCustomerSnapshotData().get(13),
                    appProperties.getCustomerSnapshotData().get(8),
                    appProperties.getCustomerSnapshotData().get(15),
                    appProperties.getCustomerSnapshotData().get(16),
                    appProperties.getCustomerSnapshotData().get(17),
                    appProperties.getCustomerSnapshotData().get(3),
                    appProperties.getCustomerSnapshotData().get(18),
                    appProperties.getCustomerSnapshotData().get(19)
            };
        }
        else {
            customerData = new String[9];
            headers = new String[]{appProperties.getCustomerSnapshotData().get(14),
                    appProperties.getCustomerSnapshotData().get(0),
                    appProperties.getCustomerSnapshotData().get(13),
                    appProperties.getCustomerSnapshotData().get(8),
                    appProperties.getCustomerSnapshotData().get(15),
                    appProperties.getCustomerSnapshotData().get(17),
                    appProperties.getCustomerSnapshotData().get(3),
                    appProperties.getCustomerSnapshotData().get(18),
                    appProperties.getCustomerSnapshotData().get(19)};
        }

        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(reportFilePath+File.separator+File.separator + filename));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader(headers));
        ) {


            for(Customer customer:customers) {
                customerData[0] = customer.getCustomerId();
                customerData[1] = customer.getCustomerName();
                customerData[2] = customer.getScore();
                customerData[3] = DtoGenUtils.numberToStringUIFormated(customer.getBalance());
                customerData[4] = DtoGenUtils.numberToStringUIFormated(customer.getCreditLimit());
                if(scenario)  {
                    customerData[5] = DtoGenUtils.numberToStringUIFormated(customer.getProposedLimit());
                    customerData[6] = customer.getPortfolio();
                    customerData[7] = customer.getVatNumber();
                    customerData[8] = customer.getCustomerStatus();
                    customerData[9] = customer.getBehavioralScore();
                } else {
                    customerData[5] = customer.getPortfolio();
                    customerData[6] = customer.getVatNumber();
                    customerData[7] = customer.getCustomerStatus();
                    customerData[8] = customer.getBehavioralScore();
                }
                csvPrinter.printRecord(customerData);
            }
            csvPrinter.flush();
        } finally {

        }
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public BusinessRepository getRepositoryBucket() {
        return repositoryBucket;
    }

    public void setRepositoryBucket(BusinessRepository repositoryBucket) {
        this.repositoryBucket = repositoryBucket;
    }

    public UtilService getUtilService() {
        return utilService;
    }

    public void setUtilService(UtilService utilService) {
        this.utilService = utilService;
    }

    public ScenarioRepository getScenarioRepository() {
        return scenarioRepository;
    }

    @Autowired
    public void setScenarioRepository(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }

    public AppPropertiesConfig getAppProperties() {
        return appProperties;
    }

    public void setAppProperties(AppPropertiesConfig appProperties) {
        this.appProperties = appProperties;
    }
}
