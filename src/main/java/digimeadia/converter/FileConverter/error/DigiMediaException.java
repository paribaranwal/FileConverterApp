package digimeadia.converter.FileConverter.error;

public class DigiMediaException {
    public String getMessage() {
        return message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    private String message;
    private String stackTrace;
    public DigiMediaException(String message, String stackTrace) {
        this.message = message;
        this.stackTrace = stackTrace;
    }
}
