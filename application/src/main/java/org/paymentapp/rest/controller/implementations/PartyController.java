package org.paymentapp.rest.controller.implementations;

import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.paymentapp.domain.ports.api.PartyServicePort;
import org.paymentapp.domain.model.AccountId;
import org.paymentapp.domain.model.Party;
import org.paymentapp.domain.model.TransferParty;
import org.paymentapp.rest.controller.adapters.PartyControllerInterface;

@RestController
@RequestMapping(path = "/api/parties")
public class PartyController implements PartyControllerInterface {

    private Logger logger = LoggerFactory.getLogger(PartyController.class);

    @Autowired
    private PartyServicePort partyService;

    @Override
    public ResponseEntity<UUID> addParty(Party party) {
        UUID id = null;
        // log
        logger.info("Party adding into repository");
        try {
            id = partyService.addParty(party);
        } catch (EntityExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<TransferParty> lookupForParty(AccountId idType, String idValue) {
        // log
        logger.info(String.format("Party lookup for id type %s and id value %s",idType,idValue));
        // init the response
        TransferParty responseBody = new TransferParty();
        // fetching the party
        Party party = new Party();
        try {
            if(idType == AccountId.MSISDN){
                Long phone;
                try {
                    phone = Long.parseLong(idValue);
                } catch (NumberFormatException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Malformed id value");
                }
                party = partyService.getPartyByPhone(phone);
                responseBody.setIdType(AccountId.MSISDN);
            }
            else if(idType == AccountId.ACCOUNT_ID){
                UUID id;
                try {
                    id = UUID.fromString(idValue);
                } catch (IllegalArgumentException e) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Malformed id value");
                }
                party = partyService.getPartyById(id);
                responseBody.setIdType(AccountId.ACCOUNT_ID);
            }
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Party not found");
        }
        // prepatring the response
        TransferParty.copyProperties(responseBody, party, idValue);    

        return ResponseEntity.ok(responseBody);
    }
    
}
