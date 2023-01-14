package tn.ksoftwares.persistence.adapter;

import java.util.UUID;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import tn.ksoftwares.domain.model.exception.DomainConstraintViolationException;
import tn.ksoftwares.domain.model.exception.UserIdAlreadyUsedException;
import tn.ksoftwares.domain.model.exception.EmailAlreadyUsedException;
import tn.ksoftwares.domain.model.exception.UserNameAlreadyUsedException;
import tn.ksoftwares.domain.model.pojo.User;
import tn.ksoftwares.domain.model.utils.Username;
import tn.ksoftwares.domain.logic.ports.spi.UserPersistence;
import tn.ksoftwares.persistence.entity.UserEntity;
import tn.ksoftwares.persistence.entity.constraint.UserConstraint;
import tn.ksoftwares.persistence.mapper.UserMapper;
import tn.ksoftwares.persistence.repository.UserRepository;

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
