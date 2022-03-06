package org.paymentapp.rest.controller.adapters;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.paymentapp.domain.model.AccountId;
import org.paymentapp.domain.model.Party;
import org.paymentapp.domain.model.TransferParty;

public interface PartyControllerInterface {
    @PostMapping("/add")
    ResponseEntity<UUID> addParty(@RequestBody Party party);

    @GetMapping("/{idType}/{idValue}")
    ResponseEntity<TransferParty> lookupForParty(@PathVariable AccountId idType, @PathVariable String idValue);

}
