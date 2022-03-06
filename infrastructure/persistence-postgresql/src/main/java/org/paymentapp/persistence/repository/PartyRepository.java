package org.paymentapp.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.paymentapp.persistence.entity.PartyEntity;

@Repository
public interface PartyRepository extends JpaRepository<PartyEntity, UUID>{
    Optional<PartyEntity> findByPhone(Long phone);
}
