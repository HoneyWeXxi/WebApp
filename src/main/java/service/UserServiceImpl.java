package service;

import dao.UserDao;
import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        return userDao.findById(id);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        checkUserValid(user);
        userDao.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        checkUserValid(user);
        userDao.update(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        userDao.delete(id);
    }

    private void checkUserValid(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }
        String validationError = user.validate();
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);
        }
    }
}