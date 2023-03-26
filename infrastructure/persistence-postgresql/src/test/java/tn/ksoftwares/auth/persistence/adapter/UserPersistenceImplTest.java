package tn.ksoftwares.auth.persistence.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.ksoftwares.auth.domain.logic.ports.spi.UserPersistence;
import tn.ksoftwares.auth.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.auth.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.pojo.User;
import tn.ksoftwares.auth.domain.model.utils.Email;
import tn.ksoftwares.auth.domain.model.utils.Name;
import tn.ksoftwares.auth.domain.model.utils.Password;
import tn.ksoftwares.auth.domain.model.utils.Username;
import tn.ksoftwares.auth.persistence.config.H2JpaConfig;
import tn.ksoftwares.auth.persistence.config.TestConfiguration;
import tn.ksoftwares.auth.persistence.entity.UserEntity;
import tn.ksoftwares.auth.persistence.mapper.UserMapper;
import tn.ksoftwares.auth.persistence.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestConfiguration.class, H2JpaConfig.class })
class UserPersistenceImplTest {

    private User user;
    private User conflictUser;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPersistence userPersistence;

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void setup(TestInfo testInfo) {
        user = new User();
        user.setFirstname(new Name("DummyName"));
        user.setLastname(new Name("DummyLastName"));
        user.setEmail(new Email("dummy.dummier@dummydomain.org"));
        user.setUsername(new Username("DummyUsername"));
        user.setPassword(new Password("qwerty*123"));

        if (testInfo.getDisplayName().contains("ConstraintViolation")) {
            conflictUser = new User();
            BeanUtils.copyProperties(user, conflictUser);
            UserEntity userEntity = userRepository.save(userMapper.toEntity(user));
            user.setId(userEntity.getId());
        }
    }

    @AfterEach
    void tearDown() {
        if (user.getId() != null) {
            userRepository.deleteById(user.getId());
        }
    }

    @Test
    void testSave() throws EmailAlreadyUsedException, UserNameAlreadyUsedException,
            DomainConstraintViolationException {
        // save the user
        userPersistence.add(user);
        // retrieve the saved user
        userRepository.findByUsername(user.getUsername().getData()).ifPresentOrElse(
                it -> {
                    user.setId(it.getId());
                    assertEquals("Persisted user should be equal to the expected one", user, userMapper.toDto(it));
                },
                () -> assertNotNull("Persisted user should exist.", null));
    }

    @Test
    void testSaveThenUniqueEmailConstraintViolation()
            throws EmailAlreadyUsedException, UserNameAlreadyUsedException,
            DomainConstraintViolationException {
        assertThrows(String.format("%s must be thrown", EmailAlreadyUsedException.class.getSimpleName()),
                EmailAlreadyUsedException.class, () -> {
                    // save the user
                    userPersistence.add(conflictUser);
                });

    }

    @Test
    void testSaveThenUniqueUsernameConstraintViolation()
            throws EmailAlreadyUsedException, UserNameAlreadyUsedException,
            DomainConstraintViolationException {
        // givin
        conflictUser.setEmail(new Email("another.email@dummydomain.org"));
        assertThrows(String.format("%s must be thrown", UserNameAlreadyUsedException.class.getSimpleName()),
                UserNameAlreadyUsedException.class, () -> {
                    // save the user
                    userPersistence.add(conflictUser);
                });

    }

}
