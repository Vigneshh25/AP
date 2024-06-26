package splitwise.service;

import splitwise.Repositories.*;
import splitwise.entities.Expense;
import splitwise.entities.Group;
import splitwise.entities.User;

import java.util.List;

// UserService.java
public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepositoryImpl();
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}

