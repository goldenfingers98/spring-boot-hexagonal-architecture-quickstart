package tn.ksoftwares.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import tn.ksoftwares.domain.model.pojo.User;
import tn.ksoftwares.domain.model.utils.Email;
import tn.ksoftwares.domain.model.utils.Name;
import tn.ksoftwares.domain.model.utils.Password;
import tn.ksoftwares.domain.model.utils.Username;
import tn.ksoftwares.persistence.config.TestConfiguration;
import tn.ksoftwares.persistence.entity.UserEntity;

@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void whenUserDtoProvided_thenMapToUserEntity() {
        // when
        User user = new User(
                UUID.randomUUID(),
                new Name("Khaldoun"),
                null,
                new Name("CHTOUROU"),
                new Email("tchtourou21@gmail.com"),
                new Username("goldenfingers98"),
                new Password("Qwerty*123"));
        // expected
        UserEntity expectedUserEntity = new UserEntity(
                user.getId(),
                "Khaldoun",
                null,
                "CHTOUROU",
                "tchtourou21@gmail.com",
                "goldenfingers98",
                "Qwerty*123");
        // then
        UserEntity actualUserEntity = userMapper.toEntity(user);
        assertEquals(actualUserEntity, expectedUserEntity);
    }

    @Test
    void whenUserEntityProvided_tenMapToUserDto() {
        // when
        UserEntity userEntity = new UserEntity(
                UUID.randomUUID(),
                "Khaldoun",
                null,
                "CHTOUROU",
                "tchtourou21@gmail.com",
                "goldenfingers98",
                "Qwerty*123");
        // expected
        User expectedUser = new User(
                userEntity.getId(),
                new Name("Khaldoun"),
                null,
                new Name("CHTOUROU"),
                new Email("tchtourou21@gmail.com"),
                new Username("goldenfingers98"),
                new Password("Qwerty*123"));
        // then
        User actualUser = userMapper.toDto(userEntity);
        assertEquals(expectedUser, actualUser);
    }
}
