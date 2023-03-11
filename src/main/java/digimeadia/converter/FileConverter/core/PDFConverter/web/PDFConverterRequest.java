package digimeadia.converter.FileConverter.core.PDFConverter.web;

import digimeadia.converter.FileConverter.core.ConverterRequest;
import digimeadia.converter.FileConverter.core.FileTypes;
import digimeadia.converter.FileConverter.error.ConversionException;
import digimeadia.converter.FileConverter.util.Logger;
import digimeadia.converter.FileConverter.util.SizeUtitlity;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.file.Files;

public class PDFConverterRequest extends ConverterRequest<ByteArrayResource> {
    @Override
    public boolean canConvert() throws ConversionException {
        Logger.log("Name: " + this.getFilename() +
                ", Extension: " + this.getExtension() +
                ", Size: " + SizeUtitlity.getSizeInMB(this.getFileSize()));
        if (this.getExtension().compareToIgnoreCase(FileTypes.PDF.name()) == 0) {
            Logger.log("Can not convert PDF to PDF");
            throw new ConversionException("Can not convert PDF to PDF");
        }
        return true;
    }

    @Override
    public ByteArrayResource convertToOutputResource() throws IOException {
        Logger.log("Starting Conversion for " + this.getFilename() + "." + this.getExtension());
        XWPFDocument document = new XWPFDocument(this.getFile().getInputStream());
        PdfOptions pdfOptions = PdfOptions.create();
        File targetFile = new File(this.getFilename() + ".pdf");
        OutputStream outStream = new FileOutputStream(targetFile);
        PdfConverter.getInstance().convert(document, outStream, pdfOptions);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.writeTo(outStream);
        baos.close();
        outStream.close();
        document.close();

        Logger.log("Conversion complete. " + targetFile.getName() + " is created.");
        return getBytes(targetFile.getAbsolutePath());
    }
    private ByteArrayResource getBytes(String filePath) throws IOException {
        Resource pdfFile = new FileSystemResource(filePath);
        PDDocument document = PDDocument.load(pdfFile.getInputStream());

        // Convert the PDF to a byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        document.save(outputStream);
        byte[] bytes = outputStream.toByteArray();
        Files.deleteIfExists(pdfFile.getFile().toPath());
        return new ByteArrayResource(bytes);
    }
}
