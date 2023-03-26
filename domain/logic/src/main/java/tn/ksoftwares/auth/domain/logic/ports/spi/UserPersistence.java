package tn.ksoftwares.auth.domain.logic.ports.spi;

import tn.ksoftwares.auth.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.auth.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.pojo.User;

public interface UserPersistence {
    void add(User user) throws EmailAlreadyUsedException, UserNameAlreadyUsedException, DomainConstraintViolationException;
}
