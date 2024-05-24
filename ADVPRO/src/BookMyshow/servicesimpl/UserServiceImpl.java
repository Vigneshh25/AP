package BookMyshow.servicesimpl;

import BookMyshow.entities.User;
import BookMyshow.repositories.UserRepository;
import BookMyshow.services.UserService;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean login(String email, String password) {
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public void updateProfile(User user) {
        userRepository.update(user);
    }

    @Override
    public User getUserByEmail(String email) { // Implement this method
        return userRepository.findByEmail(email);
    }
}

