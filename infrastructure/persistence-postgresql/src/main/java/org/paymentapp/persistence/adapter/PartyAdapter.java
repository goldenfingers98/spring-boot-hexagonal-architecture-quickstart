package org.paymentapp.persistence.adapter;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.paymentapp.domain.model.Party;
import org.paymentapp.domain.ports.spi.PartyPersistencePort;
import org.paymentapp.persistence.entity.PartyEntity;
import org.paymentapp.persistence.repository.PartyRepository;

public class PartyAdapter implements PartyPersistencePort {

    @Autowired
    private PartyRepository partyRepository;

    @Override
    public UUID addParty(Party party) {
        UUID id = null;
        // check duplicate phone numbers
        Optional<PartyEntity> temp;
        temp = partyRepository.findByPhone(party.getPhone());
        if(temp.isPresent()){
            throw new EntityExistsException("A party already exists with the given phone number.");
        } else {

            PartyEntity partyEntity = new PartyEntity();
            BeanUtils.copyProperties(party, partyEntity);
            id = partyRepository.save(partyEntity).getId();
        }
        return id;
    }

    @Override
    public Party getPartyById(UUID id) {
        Party party = new Party();
        PartyEntity partyEntity;
        Optional<PartyEntity> optional = partyRepository.findById(id);
        partyEntity = optional.isPresent() ? optional.get() : null;
        if (partyEntity == null) {
            throw new EntityNotFoundException();
        }
        BeanUtils.copyProperties(partyEntity, party);
        return party;
    }

    @Override
    public Party getPartyByPhone(Long phone) {
        Party party = new Party();
        PartyEntity partyEntity;
        Optional<PartyEntity> optional = partyRepository.findByPhone(phone);
        partyEntity = optional.isPresent() ? optional.get() : null;
        if (partyEntity == null) {
            throw new EntityNotFoundException();
        }
        BeanUtils.copyProperties(partyEntity, party);
        return party;
    }

}
