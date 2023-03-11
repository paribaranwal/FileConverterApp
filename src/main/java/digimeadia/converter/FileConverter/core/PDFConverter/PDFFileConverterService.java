package digimeadia.converter.FileConverter.core.PDFConverter;
import digimeadia.converter.FileConverter.core.PDFConverter.web.PDFConverterRequest;
import digimeadia.converter.FileConverter.error.ConversionException;
import digimeadia.converter.FileConverter.error.DigiMediaException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class PDFFileConverterService {
    public ResponseEntity convertToPdf(PDFConverterRequest req) throws ConversionException, IOException {
        try {
        req.canConvert();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + req.getFilename() + "\"");
        headers.setContentType(MediaType.APPLICATION_PDF);
        ByteArrayResource resource = req.convertToOutputResource();
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .body(resource);
        } catch (Exception ioe) {
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new DigiMediaException(ioe.getMessage(), ioe.getLocalizedMessage()));
        }
    }
}
