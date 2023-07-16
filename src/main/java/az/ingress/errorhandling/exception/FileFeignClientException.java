package az.ingress.errorhandling.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class FileFeignClientException extends RuntimeException {
    private final String message;
    private final int status;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public FileFeignClientException(String message, int status) {
        this.message = message;
        this.status = status;
    }

}
