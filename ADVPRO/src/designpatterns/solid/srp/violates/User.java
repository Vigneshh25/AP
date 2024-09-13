package designpatterns.solid.srp.violates;

class User {
    private final String name;
    private final String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Email sending functionality should not be the responsibility of User class
    public void sendEmail(String message) {
        System.out.println("Sending email to: " + this.email + " with message: " + message);
    }
}