package splitwise.factory;

import splitwise.entities.User;

public class UserFactory {

    public static User createUser(long id, String name, String email) {
        return new User(id, name, email);
    }
}
