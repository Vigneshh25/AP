package splitwise.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// User.java
public class User {
    private long userId;
    private String username;
    private String email;

    public User(long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    // getters and setters
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


