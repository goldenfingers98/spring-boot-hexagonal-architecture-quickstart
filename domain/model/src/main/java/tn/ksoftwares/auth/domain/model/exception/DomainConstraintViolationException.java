package tn.ksoftwares.auth.domain.model.exception;

public class DomainConstraintViolationException extends Exception {

    public DomainConstraintViolationException() {
    }

    public DomainConstraintViolationException(String message) {
        super(message);
    }

    public DomainConstraintViolationException(Throwable cause) {
        super(cause);
    }

    public DomainConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainConstraintViolationException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
