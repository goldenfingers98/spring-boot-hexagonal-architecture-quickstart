package org.paymentapp.configuration;

import org.paymentapp.persistence.adapter.PartyAdapter;
import org.paymentapp.domain.ports.api.PartyServicePort;
import org.paymentapp.domain.ports.spi.PartyPersistencePort;
import org.paymentapp.domain.service.PartyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PartyConfig {

    @Bean
    public PartyPersistencePort partyPersistence(){
        return new PartyAdapter();
    }

    @Bean
    public PartyServicePort partyService(){
        return new PartyService(partyPersistence());
    }
}