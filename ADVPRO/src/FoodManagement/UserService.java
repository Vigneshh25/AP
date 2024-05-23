package FoodManagement;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private User currentUser;
    private final ReentrantLock userLock = new ReentrantLock();

    private UserService() {}

    public static UserService getInstance() {
        return INSTANCE;
    }

    public void registerUser(String name, String gender, String phoneNumber, String pincode) {
        userLock.lock();
        try {
            if (users.containsKey(phoneNumber)) {
                System.out.println("User already exists.");
                return;
            }
            users.put(phoneNumber, new User(name, gender, phoneNumber, pincode));
            System.out.println("User registered successfully.");
        } finally {
            userLock.unlock();
        }
    }

    public void loginUser(String phoneNumber) {
        userLock.lock();
        try {
            if (!users.containsKey(phoneNumber)) {
                System.out.println("User not found.");
                return;
            }
            currentUser = users.get(phoneNumber);
            System.out.println("User logged in successfully.");
        } finally {
            userLock.unlock();
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }
}
