package BookStore.model;

public class User implements OrderObserver {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public void update() {
        System.out.println("Notification received by " + name);
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Email: " + email);
    }

    public String getEmail() {
        return email;
    }
}
