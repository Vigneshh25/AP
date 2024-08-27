package googlecalander;

import googlecalander.model.User;

// Factory for creating User objects
public class UserFactory {
    public static User createUser(String username, String password) {
        return new User(username, password);
    }
}
