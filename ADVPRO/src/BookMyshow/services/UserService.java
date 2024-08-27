package BookMyshow.services;

import BookMyshow.entities.User;
import BookMyshow.repositories.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(User user) {
        return userRepository.save(user);
    }

    public boolean login(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    public void updateProfile(User user) {
        userRepository.update(user);
    }

    public User getUserByEmail(String email) { // Implement this method
        return userRepository.findByEmail(email);
    }
}

