package splitwise.Repositories;

import splitwise.entities.*;

import java.util.List;

// UserRepository.java
public interface UserRepository {
    void save(User user);
    User findById(long userId);
    void delete(User user);
    List<User> findAll();
}

