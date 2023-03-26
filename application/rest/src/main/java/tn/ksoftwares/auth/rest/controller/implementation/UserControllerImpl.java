package tn.ksoftwares.auth.rest.controller.implementation;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import tn.ksoftwares.auth.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.auth.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.MalformedFieldException;
import tn.ksoftwares.auth.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.pojo.User;
import tn.ksoftwares.auth.rest.controller.apdater.UserController;
import tn.ksoftwares.auth.domain.logic.ports.api.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserControllerImpl implements UserController {

    @Autowired
    private Logger logger;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Void> addUser(User user) {
        try {
            userService.saveUser(user);
        } catch (DomainConstraintViolationException | MalformedFieldException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (EmailAlreadyUsedException | UserNameAlreadyUsedException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> healthCheck() {
        logger.debug("health check.");
        return null;
    }
}
