package org.paymentapp.domain.ports.spi;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.paymentapp.domain.model.Party;

public interface PartyPersistencePort {

    UUID addParty(Party party);

    Party getPartyById(UUID id) throws EntityNotFoundException;

    Party getPartyByPhone(Long phone) throws EntityNotFoundException;
}
