package BookMyshow.repositories;

import BookMyshow.entities.User;

public interface UserRepository {
    boolean save(User user);
    User findByEmail(String email);
    void update(User user);
}
