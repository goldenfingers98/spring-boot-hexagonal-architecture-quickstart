package tn.ksoftwares.auth.domain.logic.ports.spi;

import java.util.UUID;

import tn.ksoftwares.auth.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.auth.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.UserIdAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.pojo.User;
import tn.ksoftwares.auth.domain.model.utils.Username;

public interface UserPersistence {
    void add(User user) throws EmailAlreadyUsedException, UserNameAlreadyUsedException, DomainConstraintViolationException, UserIdAlreadyUsedException;
    void updateUser(User user);
    void deleteUser(UUID userId);
    User getUserById(UUID userId);
    User getUserByUsername(Username username); 
}
