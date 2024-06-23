package votingsystem;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private final Map<String, User> users = new HashMap<>();

    public boolean register(User user) {
        if (users.containsKey(user.getId())) {
            return false; // User already exists
        }
        users.put(user.getId(), user);
        return true;
    }

    public User authenticate(String username, String password) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                return user;
            }
        }
        return null; // Authentication failed
    }
}
