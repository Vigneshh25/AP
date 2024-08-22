package BookStore.repository;// UserRepository.java
import BookStore.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users = new HashMap<>();

    public void saveUser(User user) {
        if (user != null) {
            users.put(user.getEmail(), user);
        }
    }

    public User findUserByEmail(String email) {
        return users.get(email);
    }
}

