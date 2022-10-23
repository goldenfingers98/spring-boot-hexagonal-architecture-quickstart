package tn.ksoftwares.domain.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import tn.ksoftwares.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.domain.model.exception.UserIdAlreadyUsedException;
import tn.ksoftwares.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.domain.model.exception.MalformedFieldException;
import tn.ksoftwares.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.domain.model.pojo.User;
import tn.ksoftwares.domain.model.utils.Username;
import tn.ksoftwares.domain.ports.api.UserService;
import tn.ksoftwares.domain.ports.spi.UserPersistence;

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