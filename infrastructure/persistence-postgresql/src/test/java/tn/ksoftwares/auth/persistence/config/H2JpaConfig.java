package tn.ksoftwares.auth.persistence.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import tn.ksoftwares.auth.domain.logic.ports.spi.UserPersistence;
import tn.ksoftwares.auth.persistence.adapter.UserPersistenceImpl;

@Configuration
@EnableJpaRepositories(basePackages = "tn.ksoftwares.auth.persistence.repository")
@EntityScan(basePackages = "tn.ksoftwares.auth.persistence.entity")
@PropertySource("application.yml")
@EnableAutoConfiguration
public class H2JpaConfig {

    @Bean
    public UserPersistence getUserPersistence() {
        return new UserPersistenceImpl();
    }
    
}
