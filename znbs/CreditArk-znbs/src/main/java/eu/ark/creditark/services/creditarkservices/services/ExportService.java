package eu.ark.creditark.services.creditarkservices.services;

import java.io.IOException;
import org.springframework.http.ResponseEntity;

public interface ExportService {

    public ResponseEntity handleDownloadResponse() throws IOException;

}
