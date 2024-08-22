package BookStore.factory;

import BookStore.model.Admin;
import BookStore.model.Customer;
import BookStore.model.User;

public class UserFactory {
    public static User createUser(String type, String name, String email) {
        if ("Customer".equalsIgnoreCase(type)) {
            return new Customer(name, email);
        } else if ("Admin".equalsIgnoreCase(type)) {
            return new Admin(name, email);
        }
        return null;
    }
}
