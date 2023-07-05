package az.ingress.errorhandling.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;
import java.util.Map;

/**
 * @author caci
 */

@Slf4j
public class FileFeignClientException extends RuntimeException {
    private final ErrorResponse errorResponse;
    private final Map<String, Object> messageArguments;


    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public FileFeignClientException(ErrorResponse errorResponse, Map<String, Object> messageArguments) {
        this.errorResponse = errorResponse;
        this.messageArguments = messageArguments;
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
