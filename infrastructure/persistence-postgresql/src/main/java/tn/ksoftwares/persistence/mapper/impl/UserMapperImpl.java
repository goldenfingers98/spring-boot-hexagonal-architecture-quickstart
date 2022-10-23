package tn.ksoftwares.persistence.mapper.impl;

import tn.ksoftwares.domain.model.pojo.User;
import tn.ksoftwares.domain.model.utils.Email;
import tn.ksoftwares.domain.model.utils.Name;
import tn.ksoftwares.domain.model.utils.Password;
import tn.ksoftwares.domain.model.utils.Username;
import tn.ksoftwares.persistence.entity.UserEntity;
import tn.ksoftwares.persistence.mapper.UserMapper;

public class UserMapperImpl implements UserMapper {

    @Override
    public User toDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        User user = new User();
        user.setId(entity.getId());
        if (entity.getFirstname() != null) {
            user.setFirstname(
                    new Name(entity.getFirstname()));
        }
        if (entity.getMiddlename() != null) {
            user.setMiddlename(
                    new Name(entity.getMiddlename()));
        }
        if (entity.getLastname() != null) {
            user.setLastname(
                    new Name(entity.getLastname()));
        }
        if (entity.getEmail() != null) {
            user.setEmail(
                    new Email(entity.getEmail()));
        }
        if (entity.getUsername() != null) {
            user.setUsername(
                    new Username(entity.getUsername()));
        }
        if (entity.getPassword() != null) {
            user.setPassword(
                    new Password(entity.getPassword()));
        }
        return user;
    }

    @Override
    public UserEntity toEntity(User dto) {
        if (dto == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setFirstname(dto.getFirstname().getData());
        if (dto.getMiddlename() != null) {
            entity.setMiddlename(dto.getMiddlename().getData());
        }
        entity.setLastname(dto.getLastname().getData());
        entity.setUsername(dto.getUsername().getData());
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail().getData());
        }
        entity.setPassword(dto.getPassword().getData());
        return entity;
    }

}
