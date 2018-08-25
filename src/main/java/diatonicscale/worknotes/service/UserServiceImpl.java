/**
 * User: DiatonicScale
 * Date: 25.08.2018
 */

package diatonicscale.worknotes.service;

import diatonicscale.worknotes.UserServiceException;
import diatonicscale.worknotes.model.User;
import diatonicscale.worknotes.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        User savedUser = repository.save(user);
        if (savedUser == null) {
            LOGGER.error("User not saved");
            throw new UserServiceException("Can't save user");
        }
        return savedUser;
    }

    @Override
    public void update(User user) {
        if (repository.save(user) == null) {
            LOGGER.error("User not updated, id = " + user.getId());
            throw new UserServiceException("Can't update user with id = " + user.getId());
        }
    }

    @Override
    public void delete(int id) {
        if (!repository.delete(id)) {
            LOGGER.error("User not deleted, id = " + id);
            throw new UserServiceException("Can't delete user with id = " + id);
        }
    }

    @Override
    public User get(int id) {
        User user = repository.get(id);
        if (user == null) {
            LOGGER.error("User not found, id = " + id);
            throw new UserServiceException("Can't find user with id = " + id);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
}
