package tn.ksoftwares.auth.persistence.mapper;

import tn.ksoftwares.auth.persistence.entity.UserEntity;
import tn.ksoftwares.auth.domain.model.pojo.User;

public interface UserMapper {
    
    User toDto(UserEntity entity);
    UserEntity toEntity(User dto);
    
}
