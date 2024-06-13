package chatsystem.UserManagement;

/**
 * Created by Vignesh.V on 13/06/24.
 */ // UserRepository Interface
public interface UserRepository {
    boolean save(User user);
    User findById(String id);
    User findByUsername(String username);
    User findByEmail(String email);
    void update(User user);
}

