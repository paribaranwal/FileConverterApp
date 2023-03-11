package digimeadia.converter.FileConverter.error;

import java.io.IOException;

public class ConversionException extends IOException {
    public ConversionException(String msg) {
        super(msg);
    }

    public ConversionException(String msg, Exception e) {
        super(msg + " happened during conversion because of " + e.toString());
    }
}
