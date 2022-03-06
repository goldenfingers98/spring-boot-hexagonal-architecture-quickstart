package org.paymentapp.domain.service;

import java.util.UUID;

import org.paymentapp.domain.model.Party;
import org.paymentapp.domain.ports.api.PartyServicePort;
import org.paymentapp.domain.ports.spi.PartyPersistencePort;

public class PartyService implements PartyServicePort{

    private PartyPersistencePort partyPersistencePort;

    public PartyService(PartyPersistencePort partyPersistencePort){
        this.partyPersistencePort = partyPersistencePort;
    }

    @Override
    public UUID addParty(Party party) {
        UUID id = partyPersistencePort.addParty(party);
        return id;
    }

    @Override
    public Party getPartyById(UUID id) {
        Party party = partyPersistencePort.getPartyById(id);
        return party;
    }

    @Override
    public Party getPartyByPhone(Long phone) {
        Party party = partyPersistencePort.getPartyByPhone(phone);
        return party;
    }
    
}
