package Chatsystem.Authentication;

// AuthStrategy Interface
public interface AuthStrategy {
    boolean authenticate(String username, String password);
}

