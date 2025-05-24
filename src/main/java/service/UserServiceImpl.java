package service;

import model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User getUser(Long id) {
        return repo.findById(id);
    }

    @Override
    public void saveUser(User user) {
        repo.save(user);
    }

    @Override
    public void updateUser(User user) {
        repo.update(user);
    }

    @Override
    public void deleteUser(Long id) {
        repo.delete(id);
    }
}