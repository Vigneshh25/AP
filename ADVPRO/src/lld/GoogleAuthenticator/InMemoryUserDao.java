package lld.GoogleAuthenticator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vignesh.V on 31/05/24.
 */
class InMemoryUserDao implements UserDao {
    private Map<String, User> users;

    public InMemoryUserDao() {
        this.users = new HashMap<>();
    }

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
