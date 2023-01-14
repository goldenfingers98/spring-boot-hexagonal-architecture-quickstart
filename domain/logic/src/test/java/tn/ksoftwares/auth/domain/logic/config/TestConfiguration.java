package tn.ksoftwares.auth.domain.logic.config;

import javax.validation.Validator;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import tn.ksoftwares.auth.domain.logic.ports.api.PasswordEncoder;
import tn.ksoftwares.auth.domain.logic.ports.api.UserService;
import tn.ksoftwares.auth.domain.logic.ports.spi.UserPersistence;
import tn.ksoftwares.auth.domain.logic.service.UserServiceImpl;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "tn.ksoftwares.auth.domain.logic.advice")
public class TestConfiguration {
    
    @Bean
    @Scope("prototype")
    public Logger getLogger(final InjectionPoint ip) {
        Class<?> clazz = ip.getMember().getDeclaringClass();
        return LoggerFactory.getLogger(clazz);
    }
    
    @Bean
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Bean
    public Validator getValidator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public UserPersistence getUserPersistence() {
        return Mockito.mock(UserPersistence.class);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return Mockito.mock(PasswordEncoder.class);
    }
    
}
