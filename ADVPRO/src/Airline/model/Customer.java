    package Airline.model;

public class Customer implements Observer {
    private String email;

    public Customer(String email) {
        this.email = email;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification to " + email + ": " + message);
    }
}
