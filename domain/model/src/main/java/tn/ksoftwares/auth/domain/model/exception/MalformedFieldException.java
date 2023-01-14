package tn.ksoftwares.auth.domain.model.exception;

public class MalformedFieldException extends Exception {

    public MalformedFieldException() {
    }

    public MalformedFieldException(String message) {
        super(message);
    }

    public MalformedFieldException(Throwable cause) {
        super(cause);
    }

    public MalformedFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
