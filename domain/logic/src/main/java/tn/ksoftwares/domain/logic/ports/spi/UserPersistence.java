package tn.ksoftwares.domain.logic.ports.spi;

import java.util.UUID;

import tn.ksoftwares.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.domain.model.exception.UserIdAlreadyUsedException;
import tn.ksoftwares.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.domain.model.pojo.User;
import tn.ksoftwares.domain.model.utils.Username;

public interface UserPersistence {
    void add(User user) throws EmailAlreadyUsedException, UserNameAlreadyUsedException, DomainConstraintViolationException, UserIdAlreadyUsedException;
    void updateUser(User user);
    void deleteUser(UUID userId);
    User getUserById(UUID userId);
    User getUserByUsername(Username username); 
}
