package lld.GoogleAuthenticator.version;

import java.util.HashMap;
import java.util.Map;

interface UserDao {
    void addUser(User user);
    User getUser(String userId);
    void updateUser(User user);
    void removeUser(String userId);
}

class User {
    private final String userId;
    private final String name;
    private final String secretKey;

    public User(String userId, String name, String secretKey) {
        this.userId = userId;
        this.name = name;
        this.secretKey = secretKey;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSecretKey() {
        return secretKey;
    }
}

class InMemoryUserDao implements UserDao {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public User getUser(String userId) {
        return users.get(userId);
    }

    @Override
    public void updateUser(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public void removeUser(String userId) {
        users.remove(userId);
    }
}
