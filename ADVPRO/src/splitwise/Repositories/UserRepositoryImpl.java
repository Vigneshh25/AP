package splitwise.Repositories;// UserRepositoryImpl.java (Mock implementation)
import splitwise.entities.Expense;
import splitwise.entities.Group;
import splitwise.entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {
    private Map<Long, User> userDatabase = new HashMap<>();

    @Override
    public void save(User user) {
        userDatabase.put(user.getUserId(), user);
    }

    @Override
    public User findById(long userId) {
        return userDatabase.get(userId);
    }

    @Override
    public void delete(User user) {
        userDatabase.remove(user.getUserId());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userDatabase.values());
    }
}


