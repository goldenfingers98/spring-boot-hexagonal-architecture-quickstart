package tn.ksoftwares.domain.ports.api;

import java.util.UUID;

import tn.ksoftwares.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.domain.model.exception.UserIdAlreadyUsedException;
import tn.ksoftwares.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.domain.model.exception.MalformedFieldException;
import tn.ksoftwares.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.domain.model.pojo.User;
import tn.ksoftwares.domain.model.utils.Username;

public interface UserService {
    
    void addUser(User user) throws EmailAlreadyUsedException, UserNameAlreadyUsedException, DomainConstraintViolationException, MalformedFieldException, UserIdAlreadyUsedException;
    void updateUser(User user);
    void deleteUser(UUID userId);
    User getUserById(UUID id);
    User getUserByUserName(Username username);

}
