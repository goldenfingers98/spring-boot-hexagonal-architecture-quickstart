package tn.ksoftwares.domain.advice;

import java.lang.reflect.Field;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import tn.ksoftwares.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.domain.model.exception.MalformedFieldException;
import tn.ksoftwares.domain.model.pojo.User;
import tn.ksoftwares.domain.model.utils.Password;
import tn.ksoftwares.domain.ports.api.PasswordEncoder;

@Aspect
@Component
@EnableAspectJAutoProxy
public class UserValidationAOP {

    @Autowired
    private Validator validator;

    @Autowired
    private Logger logger;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @Around("execution(* tn.ksoftwares.domain.service.UserServiceImpl.addUser(..)) && args(tn.ksoftwares.domain.model.pojo.User))")
    public void doValidateUserThenEncodePassword(final ProceedingJoinPoint pjp) throws Throwable {
        // get the user arg
        User user = pjp.getArgs()[0] instanceof User ? (User) pjp.getArgs()[0] : new User();
        // validate the user
        validateUser(user);
        // encode the password
        User userWithEncodedPwd = encodeUserPassword(user);
        // proceed
        pjp.proceed(new Object[]{userWithEncodedPwd});
    }
    
    @SuppressWarnings("squid:S3011")
    private void validateFieldIfNotNull(Field field, Object obj) throws IllegalArgumentException, IllegalAccessException, MalformedFieldException {
        field.setAccessible(true);
        Object fieldValue = field.get(obj);
        if(fieldValue != null) {
            Set<ConstraintViolation<Object>> fieldViolation = validator.validate(fieldValue);
            if (!fieldViolation.isEmpty()) {
                logger.error("User {} \"{}\" violates constraint.", field.getName(), fieldValue);
                throw new MalformedFieldException(fieldViolation.iterator().next().getMessage());
            }
        }
    }

    private void validateUser(User user) throws IllegalArgumentException, IllegalAccessException, DomainConstraintViolationException, MalformedFieldException {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            logger.error("User instance violates contraints.");
            throw new DomainConstraintViolationException(violations.iterator().next().getMessage());
        }
        for (Field field : user.getClass().getDeclaredFields()) {
            validateFieldIfNotNull(field, user);
        }
    }

    private User encodeUserPassword(User user) {
        // get the encoded password
        String encodedPasswordData = passwordEncoder.encode(user.getPassword().getData());
        // update the user's password
        user.setPassword(
            new Password(encodedPasswordData)
        );
        return user;
    }
}
