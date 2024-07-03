package Ecommerce.Services;

import Ecommerce.Entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static UserService instance;
    private List<User> users;

    private UserService() {
        users = new ArrayList<>();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public User loginUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
