package tn.ksoftwares.auth.domain.model.exception;

public class FailedSerializationException extends RuntimeException {

    public FailedSerializationException() {
    }

    public FailedSerializationException(String message) {
        super(message);
    }

    public FailedSerializationException(Throwable cause) {
        super(cause);
    }

    public FailedSerializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedSerializationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
