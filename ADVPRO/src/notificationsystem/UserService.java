package notificationsystem;

import java.util.*;

public class UserService {
    private Map<Integer, User> users = new HashMap<>();

    public UserService() {
        // Sample users
        users.put(1, new User(1, "user1@example.com", NotificationType.EMAIL));
        users.put(2, new User(2, "1234567890", NotificationType.SMS));
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

    public String getContactDetails(int userId) {
        return users.get(userId).getContact();
    }
}
