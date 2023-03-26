package tn.ksoftwares.auth.domain.logic.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import tn.ksoftwares.auth.domain.logic.ports.api.UserService;
import tn.ksoftwares.auth.domain.logic.ports.spi.UserPersistence;
import tn.ksoftwares.auth.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.auth.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.MalformedFieldException;
import tn.ksoftwares.auth.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.pojo.User;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserPersistence userPersistence;

    @Autowired
    private Logger logger;

    @Override
    public void saveUser(User user) throws EmailAlreadyUsedException, UserNameAlreadyUsedException, DomainConstraintViolationException, MalformedFieldException {
        logger.info("Adding user {}", user);
        userPersistence.add(user);
        logger.info("User added successfully {}", user);
    }
    
}
