package truecaller;

import java.util.Date;

class User {
    String id;
    String username;
    String password;
    String phoneNumber;
    Date createdAt;

    public User(String id, String username, String password, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.createdAt = new Date();
    }
}
