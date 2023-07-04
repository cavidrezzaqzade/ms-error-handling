package az.ingress.errorhandling.exception;

import org.springframework.http.HttpStatus;

public enum Errors implements ErrorResponse {
    DATA_NOT_FOUND( "DATA_NOT_FOUND", HttpStatus.NOT_FOUND, "the data was not found with this id-{id}"),
    INTERNAL_SERVER_ERROR( "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "unknown error occurred");

    final String key;
    final HttpStatus httpStatus;
    final String message;

    Errors(String key, HttpStatus httpStatus, String message) {
        this.key = key;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getKey() {
        return key;
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}