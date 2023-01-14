package tn.ksoftwares.auth.persistence.adapter;

import java.util.UUID;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import tn.ksoftwares.auth.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.auth.domain.model.exception.UserIdAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.auth.domain.model.pojo.User;
import tn.ksoftwares.auth.domain.model.utils.Username;
import tn.ksoftwares.auth.persistence.entity.UserEntity;
import tn.ksoftwares.auth.persistence.entity.constraint.UserConstraint;
import tn.ksoftwares.auth.persistence.mapper.UserMapper;
import tn.ksoftwares.auth.persistence.repository.UserRepository;
import tn.ksoftwares.auth.domain.logic.ports.spi.UserPersistence;

public class UserPersistenceImpl implements UserPersistence {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public void add(User user)
            throws EmailAlreadyUsedException, UserNameAlreadyUsedException, DomainConstraintViolationException, UserIdAlreadyUsedException {
        UserEntity entity = mapper.toEntity(user);
        try {
            userRepository.save(entity);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                String constaintName = ((ConstraintViolationException) e.getCause()).getConstraintName();
                UserConstraint constraint = UserConstraint.valueOf(constaintName.toUpperCase());
                switch (constraint) {
                    case UNIQUE_EMAIL:
                        throw new EmailAlreadyUsedException();
                    case UNIQUE_USENAME:
                        throw new UserNameAlreadyUsedException();
                    case USERS_PKEY:
                        throw new UserIdAlreadyUsedException();
                }
            } else {
                throw e;
            }
        }
    }

    @Override
    public void updateUser(User user) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteUser(UUID userId) {
        // TODO Auto-generated method stub

    }

    @Override
    public User getUserById(UUID userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User getUserByUsername(Username username) {
        // TODO Auto-generated method stub
        return null;
    }

}
