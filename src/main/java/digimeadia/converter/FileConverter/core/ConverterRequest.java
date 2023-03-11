package digimeadia.converter.FileConverter.core;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;

public abstract class ConverterRequest<T> {
    public final MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
        String originalName = file.getOriginalFilename();
        this.filename = originalName.substring(0,
                originalName.lastIndexOf('.'));
        this.extension = originalName
                .substring(originalName.lastIndexOf('.') + 1);
        this.size = file.getSize();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @NotEmpty
    private MultipartFile file;

    @NotEmpty
    private String user;

    protected abstract boolean canConvert()  throws IOException;
    protected abstract T convertToOutputResource() throws IOException;

    public String getExtension() {
        return extension;
    }

    private String extension;

    public String getFilename() {
        return filename;
    }
    public Long getFileSize() {
        return size;
    }

    private String filename;
    private Long size;
}
