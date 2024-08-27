package googlecalander.api;

import googlecalander.UserFactory;
import googlecalander.model.User;

import java.util.HashMap;
import java.util.Map;

// Singleton Pattern for UserService
public class UserService {
    private static UserService instance;
    private Map<String, User> users = new HashMap<>();

    private UserService() {}

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User register(String username, String password) {
        if (users.containsKey(username)) {
            throw new RuntimeException("User already exists");
        }
        User user = UserFactory.createUser(username, password);
        users.put(username, user);
        return user;
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Invalid credentials");
    }
}

