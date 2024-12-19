package Chatsystem.Authentication;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // AuthService
public class AuthService {
    private AuthStrategy authStrategy;

    public void setAuthStrategy(AuthStrategy authStrategy) {
        this.authStrategy = authStrategy;
    }

    public boolean authenticate(String username, String password) {
        return authStrategy.authenticate(username, password);
    }
}
