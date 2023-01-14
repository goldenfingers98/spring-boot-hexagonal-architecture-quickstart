package tn.ksoftwares.auth.domain.model.exception;

public class UserIdAlreadyUsedException extends AlreadyUsedException {

    public UserIdAlreadyUsedException() {
        super("Duplicated user ID.");
    }

    
    
}
