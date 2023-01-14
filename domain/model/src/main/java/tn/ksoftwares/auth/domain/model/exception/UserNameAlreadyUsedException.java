package tn.ksoftwares.auth.domain.model.exception;

public class UserNameAlreadyUsedException extends AlreadyUsedException {

    public UserNameAlreadyUsedException() {
        super("Username already in use.");
    }


    
}
