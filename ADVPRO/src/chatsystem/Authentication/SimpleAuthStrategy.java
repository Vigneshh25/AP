package chatsystem.Authentication;

import chatsystem.UserManagement.User;
import chatsystem.UserManagement.UserService;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // SimpleAuthStrategy
public class SimpleAuthStrategy implements AuthStrategy {
    private final UserService userService;

    public SimpleAuthStrategy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = userService.findUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
