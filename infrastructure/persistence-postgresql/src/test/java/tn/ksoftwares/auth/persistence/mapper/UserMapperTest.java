package tn.ksoftwares.auth.persistence.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import nl.jqno.equalsverifier.EqualsVerifier;
import tn.ksoftwares.auth.persistence.entity.UserEntity;
import tn.ksoftwares.auth.domain.model.pojo.User;
import tn.ksoftwares.auth.domain.model.utils.Email;
import tn.ksoftwares.auth.domain.model.utils.Name;
import tn.ksoftwares.auth.domain.model.utils.Password;
import tn.ksoftwares.auth.domain.model.utils.Username;
import tn.ksoftwares.auth.persistence.config.TestConfiguration;

@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testToEntity() {
        // when
        User user = new User(
                UUID.randomUUID(),
                new Name("Khaldoun"),
                new Name("middlename"),
                new Name("CHTOUROU"),
                new Email("tchtourou21@gmail.com"),
                new Username("goldenfingers98"),
                new Password("Qwerty*123"));
        // expected
        UserEntity expectedUserEntity = new UserEntity(
                user.getId(),
                "Khaldoun",
                "middlename",
                "CHTOUROU",
                "tchtourou21@gmail.com",
                "goldenfingers98",
                "Qwerty*123");
        // then
        UserEntity actualUserEntity = userMapper.toEntity(user);
        assertEquals("Mapped entity should be equal to the expected one.", actualUserEntity, expectedUserEntity);
    }

    @Test
    void testToDto() {
        // when
        UserEntity userEntity = new UserEntity(
                UUID.randomUUID(),
                "Khaldoun",
                "middlename",
                "CHTOUROU",
                "tchtourou21@gmail.com",
                "goldenfingers98",
                "Qwerty*123");
        // expected
        User expectedUser = new User(
                userEntity.getId(),
                new Name("Khaldoun"),
                new Name("middlename"),
                new Name("CHTOUROU"),
                new Email("tchtourou21@gmail.com"),
                new Username("goldenfingers98"),
                new Password("Qwerty*123"));
        // then
        User actualUser = userMapper.toDto(userEntity);
        assertEquals("Mapped dto should be equal to the expected one", expectedUser, actualUser);
    }

    @Test
    void testNullMapping() {
        assertNull("Null mapping should return a null object.", userMapper.toDto(null));
        assertNull("Null mapping should return a null object.", userMapper.toEntity(null));
    }

    @Test
    void testEqualsHashCodeContract() {
        EqualsVerifier.forClass(UserEntity.class).verify();
    }

}
