package tn.ksoftwares.auth.persistence.adapter;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import tn.ksoftwares.auth.domain.logic.ports.spi.UserPersistence;
import tn.ksoftwares.auth.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.auth.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.pojo.User;
import tn.ksoftwares.auth.persistence.entity.UserEntity;
import tn.ksoftwares.auth.persistence.entity.constraint.UserConstraint;
import tn.ksoftwares.auth.persistence.mapper.UserMapper;
import tn.ksoftwares.auth.persistence.repository.UserRepository;

public class UserPersistenceImpl implements UserPersistence {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public void add(User user)
            throws EmailAlreadyUsedException, UserNameAlreadyUsedException, DomainConstraintViolationException {
        UserEntity entity = mapper.toEntity(user);
        try {
            userRepository.save(entity);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                String constaintName = ((ConstraintViolationException) e.getCause()).getConstraintName();
                UserConstraint constraint = UserConstraint.getConstraintByName(constaintName.toUpperCase());
                if (constraint == UserConstraint.UNIQUE_EMAIL) {
                        throw new EmailAlreadyUsedException();
                    } else if (constraint == UserConstraint.UNIQUE_USERNAME) {
                        throw new UserNameAlreadyUsedException();
                }
            }
            throw e;
        }
    }

}
