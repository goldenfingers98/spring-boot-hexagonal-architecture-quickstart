package tn.ksoftwares.auth.domain.model.exception;

public abstract class AlreadyUsedException extends Exception {

    protected AlreadyUsedException(String message) {
        super(message);
    }
    
}
