package chatsystem.UserManagement;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // UserService
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(User user) {
        return userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean addContact(String username, User contact) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.addContact(contact);
            userRepository.update(user);
            return true;
        }
        return false;
    }

    public boolean removeContact(String username, User contact) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.removeContact(contact);
            userRepository.update(user);
            return true;
        }
        return false;
    }
}

