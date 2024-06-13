package chatsystem.UserManagement;

import java.util.*;


public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private List<User> contacts;

    public User() {
        this.id = UUID.randomUUID().toString();
        this.contacts = new ArrayList<>();
    }

    // Getters and setters...


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContacts(List<User> contacts) {
        this.contacts = contacts;
    }

    public List<User> getContacts() {
        return contacts;
    }

    public void addContact(User user) {
        contacts.add(user);
    }

    public void removeContact(User user) {
        contacts.remove(user);
    }
}

