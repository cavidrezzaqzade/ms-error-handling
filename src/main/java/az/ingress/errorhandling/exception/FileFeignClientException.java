package az.ingress.errorhandling.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class FileFeignClientException extends RuntimeException {
    private final Response errorResponse;
    private final int status;

    public Response getErrorResponse() {
        return errorResponse;
    }

    public int getStatus() {
        return status;
    }

    public FileFeignClientException(Response errorResponse, int status) {
        this.errorResponse = errorResponse;
        this.status = status;
    }

    public String getLocalizedMessage(Locale locale, MessageSource messageSource) {
        try {
            return messageSource.getMessage(errorResponse.getKey(), new Object[]{}, locale);
        } catch (NoSuchMessageException exception) {
            log.warn("Please consider adding localized message for key {} and locale {}",
                    errorResponse.getKey(), locale);
        }
        return getMessage();
    }
}
