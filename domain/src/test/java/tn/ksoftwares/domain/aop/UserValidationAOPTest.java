package tn.ksoftwares.domain.aop;

import static org.junit.Assert.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.ksoftwares.domain.advice.UserValidationAOP;
import tn.ksoftwares.domain.config.TestConfiguration;
import tn.ksoftwares.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.domain.model.exception.MalformedFieldException;
import tn.ksoftwares.domain.model.pojo.User;
import tn.ksoftwares.domain.model.utils.Email;
import tn.ksoftwares.domain.model.utils.Name;
import tn.ksoftwares.domain.model.utils.Password;
import tn.ksoftwares.domain.model.utils.Username;
import tn.ksoftwares.domain.ports.api.UserService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class UserValidationAOPTest {

    @Autowired
    private UserService userService;

    private UserService userServiceProxy;

    @BeforeEach
    public void init() {
        // init the aspect factory
        AspectJProxyFactory aopFactory = new AspectJProxyFactory(userService);
        UserValidationAOP aspect = new UserValidationAOP();
        aopFactory.addAspect(aspect);
        userServiceProxy = aopFactory.getProxy();
    }

    @Test
    public void testDoValidateUserThenEncodePassword_nullAttribute() {
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
        assertThrows(DomainConstraintViolationException.class, () -> {
            userServiceProxy.addUser(user);
        });
    }

    @Test
    public void testDoValidateUserThenEncodePassword_InvalidName() {
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
        assertThrows(MalformedFieldException.class, () -> {
            userServiceProxy.addUser(user);
        });
    }
}
