package BookMyshow.repositoriesimpl;

import BookMyshow.entities.User;
import BookMyshow.repositories.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> users = new HashMap<>();

    @Override
    public boolean save(User user) {
        if (users.containsKey(user.getEmail())) {
            return false;
        }
        users.put(user.getEmail(), user);
        return true;
    }

    @Override
    public User findByEmail(String email) {
        return users.get(email);
    }

    @Override
    public void update(User user) {
        users.put(user.getEmail(), user);
    }
}
