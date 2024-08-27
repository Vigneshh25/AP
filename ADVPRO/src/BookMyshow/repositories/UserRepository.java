package BookMyshow.repositories;

import BookMyshow.entities.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public boolean save(User user) {
        if (users.containsKey(user.getEmail())) {
            return false;
        }
        users.put(user.getEmail(), user);
        return true;
    }

    public User findByEmail(String email) {
        return users.get(email);
    }

    public void update(User user) {
        users.put(user.getEmail(), user);
    }
}
