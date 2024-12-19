package Chatsystem.UserManagement;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> usersById = new HashMap<>();
    private Map<String, User> usersByUsername = new HashMap<>();
    private Map<String, User> usersByEmail = new HashMap<>();

    @Override
    public boolean save(User user) {
        if (usersByEmail.containsKey(user.getEmail())) {
            return false;
        }
        usersById.put(user.getId(), user);
        usersByUsername.put(user.getUsername(), user);
        usersByEmail.put(user.getEmail(), user);
        return true;
    }

    @Override
    public User findById(String id) {
        return usersById.get(id);
    }

    @Override
    public User findByUsername(String username) {
        return usersByUsername.get(username);
    }

    @Override
    public User findByEmail(String email) {
        return usersByEmail.get(email);
    }

    @Override
    public void update(User user) {
        usersById.put(user.getId(), user);
        usersByUsername.put(user.getUsername(), user);
        usersByEmail.put(user.getEmail(), user);
    }
}

