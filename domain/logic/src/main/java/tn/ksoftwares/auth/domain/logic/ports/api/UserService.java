package tn.ksoftwares.auth.domain.logic.ports.api;

import java.util.UUID;

import tn.ksoftwares.auth.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.auth.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.MalformedFieldException;
import tn.ksoftwares.auth.domain.model.exception.UserIdAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.pojo.User;
import tn.ksoftwares.auth.domain.model.utils.Username;

public interface UserService {
    
    void addUser(User user) throws EmailAlreadyUsedException, UserNameAlreadyUsedException, DomainConstraintViolationException, MalformedFieldException, UserIdAlreadyUsedException;
    void updateUser(User user);
    void deleteUser(UUID userId);
    User getUserById(UUID id);
    User getUserByUserName(Username username);

}
