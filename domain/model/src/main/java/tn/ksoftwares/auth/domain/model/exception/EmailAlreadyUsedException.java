package tn.ksoftwares.auth.domain.model.exception;

public class EmailAlreadyUsedException extends AlreadyUsedException {

    public EmailAlreadyUsedException() {
        super("Email already in use");
    }

   
}
