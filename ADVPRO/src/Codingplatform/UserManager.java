package Codingplatform;

import java.util.*;

class UserManager {
    private final Map<String, User> users;

    public UserManager() {
        this.users = new HashMap<>();
    }

    public void createUser(String userName) {
        if (!users.containsKey(userName)) {
            User user = new User(userName);
            users.put(userName, user);
        }
    }

    public User getUser(String userName) {
        return users.get(userName);
    }

    public void listUsers(SortOrder sortOrder) {
        List<User> userList = new ArrayList<>(users.values());
        userList.sort((u1, u2) -> sortOrder == SortOrder.ASC ? u1.getScore() - u2.getScore() : u2.getScore() - u1.getScore());
        for (User user : userList) {
            System.out.println(user.getUserName() + ": " + user.getScore());
        }
    }
}
