package digimeadia.converter.FileConverter.core.PDFConverter.web;

import digimeadia.converter.FileConverter.core.PDFConverter.PDFFileConverterService;
import digimeadia.converter.FileConverter.util.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/pdf")
@CrossOrigin(origins = {
        "http://localhost:3001",
        "http://192.168.1.37:3001",
        "https://online-file-converter.netlify.app"
})
public class PDFConverterController {
    private final PDFFileConverterService service;

    public PDFConverterController(PDFFileConverterService service) {
        this.service = service;
    }

    @PostMapping(value = "/convert", consumes = {"application/json", "multipart/form-data"})
    public ResponseEntity convertToPdf(@RequestPart("user") @Valid String user,
                                       @RequestPart("file") MultipartFile file,
                                       HttpServletResponse response)
            throws IllegalStateException, IOException {
        try {
            PDFConverterRequest req = new PDFConverterRequest();
            req.setUser(user);
            req.setFile(file);
            return service.convertToPdf(req);
        }
        catch(Exception e) {
            Logger.log(e.getMessage());
            throw e;
        }
    }
}
