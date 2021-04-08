package eu.ark.creditark.services.creditarkservices.services;

import eu.ark.creditark.services.creditarkservices.config.AppPropertiesConfig;
import eu.ark.creditark.services.creditarkservices.repository.ScenarioRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExportServiceImpl implements ExportService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ScenarioRepository scenarioRepository;

    @Value("${app.reportsFileName}")
    private String reportsFileName;

    @Value("${app.reportFilePath}")
    private String reportFilePath;

    @Autowired
    private AppPropertiesConfig appProperties;


    @Value("${ca.system.scenario.user}")
    private String systemScenarioUser;


    @Override
    public ResponseEntity handleDownloadResponse()
            throws IOException {

        File file = new File(reportFilePath+ File.separator+ File.separator + reportsFileName);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+reportsFileName);
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

}
