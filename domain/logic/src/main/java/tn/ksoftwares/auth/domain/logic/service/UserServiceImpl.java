package tn.ksoftwares.auth.domain.logic.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import tn.ksoftwares.auth.domain.logic.ports.api.UserService;
import tn.ksoftwares.auth.domain.logic.ports.spi.UserPersistence;
import tn.ksoftwares.auth.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.auth.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.MalformedFieldException;
import tn.ksoftwares.auth.domain.model.exception.UserIdAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.pojo.User;
import tn.ksoftwares.auth.domain.model.utils.Username;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserPersistence userPersistence;

    @Autowired
    private Logger logger;

    @Override
    public void addUser(User user) throws EmailAlreadyUsedException, UserNameAlreadyUsedException, DomainConstraintViolationException, MalformedFieldException, UserIdAlreadyUsedException {
        logger.info("Adding user {}", user);
        userPersistence.add(user);
        logger.info("User added successfully {}", user);
    }

    @Override
    public void updateUser(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteUser(UUID userId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public User getUserById(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getUserByUserName(Username username) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
