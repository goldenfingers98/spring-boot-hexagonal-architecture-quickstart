package tn.ksoftwares.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.ksoftwares.persistence.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    
}
