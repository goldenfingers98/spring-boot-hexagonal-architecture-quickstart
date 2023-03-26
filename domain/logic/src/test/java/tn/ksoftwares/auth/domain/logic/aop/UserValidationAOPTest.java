package tn.ksoftwares.auth.domain.logic.aop;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.ksoftwares.auth.domain.logic.advice.UserValidationAOP;
import tn.ksoftwares.auth.domain.logic.config.TestConfiguration;
import tn.ksoftwares.auth.domain.logic.ports.api.UserService;
import tn.ksoftwares.auth.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.auth.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.MalformedFieldException;
import tn.ksoftwares.auth.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.pojo.User;
import tn.ksoftwares.auth.domain.model.utils.Email;
import tn.ksoftwares.auth.domain.model.utils.Name;
import tn.ksoftwares.auth.domain.model.utils.Password;
import tn.ksoftwares.auth.domain.model.utils.Username;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
class UserValidationAOPTest {

    @Autowired
    private UserService userService;

    private UserService userServiceProxy;

    @BeforeEach
    void init() {
        // init the aspect factory
        AspectJProxyFactory aopFactory = new AspectJProxyFactory(userService);
        UserValidationAOP aspect = new UserValidationAOP();
        aopFactory.addAspect(aspect);
        userServiceProxy = aopFactory.getProxy();
    }

    @Test
    void testDoValidateUser() throws EmailAlreadyUsedException, UserNameAlreadyUsedException,
            DomainConstraintViolationException, MalformedFieldException {
        // when
        User user = new User(
                UUID.randomUUID(),
                new Name("Khaldoun"),
                null,
                new Name("Chtourou"),
                new Email("tchtourou21@gmail.com"),
                new Username("goldenfingers98"),
                new Password("Qwerty*123"));
        // then
        Password passwordBeforeHashing = user.getPassword();
        userServiceProxy.saveUser(user);
        // test should succeed if the validation succeeded
        assertNotEquals("Password should be encoded.", user.getPassword(), passwordBeforeHashing);
    }

    @Test
    void testDoValidateUserThenEncodePasswordNullAttribute() {
        // when
        User user = new User(
                UUID.randomUUID(),
                null, // firstname must not be null
                null,
                new Name("CHTOUROU"),
                new Email("tchtourou21@gmail.com"),
                new Username("goldenfingers98"),
                new Password("Qwerty*123"));

        // then
        assertThrows(String.format("Test should throw a %s.", DomainConstraintViolationException.class),
                DomainConstraintViolationException.class, () -> {
                    userServiceProxy.saveUser(user);
                });
    }

    @Test
    void testDoValidateUserThenEncodePasswordInvalidName() {
        // when
        User user = new User(
                UUID.randomUUID(),
                new Name("khaldoun"), // name should start with uppercase
                null,
                new Name("CHTOUROU"),
                new Email("tchtourou21@gmail.com"),
                new Username("goldenfingers98"),
                new Password("Qwerty*123"));
        // then
        assertThrows(String.format("Test should throw a %s.", MalformedFieldException.class),
                MalformedFieldException.class, () -> {
                    userServiceProxy.saveUser(user);
                });
    }
}
