package BookStore;// InMemoryUserRepository.java
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository {
    private Map<String, User> users = new HashMap<>();

    public void saveUser(User user) {
        if (user != null) {
            users.put(user.getEmail(), user);
        }
    }

    public User getUser(String email) {
        return users.get(email);
    }
}

