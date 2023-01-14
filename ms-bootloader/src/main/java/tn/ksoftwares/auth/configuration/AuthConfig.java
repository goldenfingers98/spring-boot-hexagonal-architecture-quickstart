package tn.ksoftwares.auth.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.ksoftwares.auth.persistence.adapter.UserPersistenceImpl;
import tn.ksoftwares.auth.persistence.mapper.UserMapper;
import tn.ksoftwares.auth.persistence.mapper.impl.UserMapperImpl;
import tn.ksoftwares.auth.rest.security.encoder.PasswordEncoderImpl;
import tn.ksoftwares.auth.domain.logic.ports.api.PasswordEncoder;
import tn.ksoftwares.auth.domain.logic.ports.api.UserService;
import tn.ksoftwares.auth.domain.logic.ports.spi.UserPersistence;
import tn.ksoftwares.auth.domain.logic.service.UserServiceImpl;

@Configuration
public class AuthConfig {

    @Bean
    @Scope("prototype")
    public Logger getLogger(final InjectionPoint ip) {
        Class<?> clazz = ip.getMember().getDeclaringClass();
        return LoggerFactory.getLogger(clazz);
    }

    @Bean
    public ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    @Bean
    public UserMapper getUserMapper() {
        return new UserMapperImpl();
    }

    @Bean
    public UserPersistence getUserPersistence() {
        return new UserPersistenceImpl();
    }

    @Bean
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoderImpl();
    }
}