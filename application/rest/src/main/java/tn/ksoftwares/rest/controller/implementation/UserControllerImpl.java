package tn.ksoftwares.rest.controller.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import tn.ksoftwares.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.domain.model.exception.MalformedFieldException;
import tn.ksoftwares.domain.model.exception.UserIdAlreadyUsedException;
import tn.ksoftwares.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.domain.model.pojo.User;
import tn.ksoftwares.domain.ports.api.UserService;
import tn.ksoftwares.rest.controller.apdater.UserController;

@RestController
@RequestMapping(value = "/api/users")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Void> addUser(User user) {
        try {
            userService.addUser(user);
        } catch (DomainConstraintViolationException | MalformedFieldException | UserIdAlreadyUsedException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (EmailAlreadyUsedException | UserNameAlreadyUsedException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
