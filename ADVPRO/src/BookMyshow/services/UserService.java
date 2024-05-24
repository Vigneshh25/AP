package BookMyshow.services;

import BookMyshow.entities.User;

public interface UserService {
    boolean register(User user);
    boolean login(String email, String password);
    void updateProfile(User user);
    User getUserByEmail(String email); // Add this method
}

