package tn.ksoftwares.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.ksoftwares.domain.ports.api.PasswordEncoder;
import tn.ksoftwares.domain.ports.api.UserService;
import tn.ksoftwares.domain.ports.spi.UserPersistence;
import tn.ksoftwares.domain.service.UserServiceImpl;
import tn.ksoftwares.persistence.adapter.UserPersistenceImpl;
import tn.ksoftwares.persistence.mapper.UserMapper;
import tn.ksoftwares.persistence.mapper.impl.UserMapperImpl;
import tn.ksoftwares.rest.security.encoder.PasswordEncoderImpl;

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