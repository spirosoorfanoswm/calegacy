package eu.ark.creditark.services.creditarkservices.api;

import eu.ark.creditark.services.creditarkservices.exceptions.DatabaseConnectionException;
import eu.ark.creditark.services.creditarkservices.services.ExportService;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.text.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/export")
@Api(value = "/export", description = "Export services")
@CrossOrigin(origins = "http://localhost:4200")
public class ExportController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Environment env;

    private ExportService exportService;



    @GetMapping(path = "/downloadfile")
    public ResponseEntity download()
            throws DatabaseConnectionException, ParseException, IOException {
        return exportService.handleDownloadResponse();
    }


    public ExportService getExportService() {
        return exportService;
    }

    @Autowired
    public void setExportService(ExportService exportService) {
        this.exportService = exportService;
    }



}
