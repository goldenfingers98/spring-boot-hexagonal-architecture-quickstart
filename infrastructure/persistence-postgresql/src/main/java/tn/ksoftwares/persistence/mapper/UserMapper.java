package tn.ksoftwares.persistence.mapper;

import tn.ksoftwares.domain.model.pojo.User;
import tn.ksoftwares.persistence.entity.UserEntity;

public interface UserMapper {
    
    User toDto(UserEntity entity);
    UserEntity toEntity(User dto);
    
}
