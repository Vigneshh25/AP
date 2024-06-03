package lld.GoogleAuthenticator;

/**
 * Created by Vignesh.V on 31/05/24.
 */ // Data Access Object (DAO) Pattern
interface UserDao {
    void addUser(User user);

    User getUser(String userId);

    void updateUser(User user);

    void removeUser(String userId);
}
