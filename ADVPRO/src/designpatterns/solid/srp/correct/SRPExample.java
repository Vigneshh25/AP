package designpatterns.solid.srp.correct;

/*
Definition: A class should have only one reason to change, meaning it should have only
one job or responsibility.
*/
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
}

// Separate class responsible for sending emails (not User's responsibility)
class EmailService {
    public void sendEmail(User user, String message) {
        System.out.println("Sending email to: " + user.getEmail() + " with message: " + message);
    }
}

public class SRPExample {
    public static void main(String[] args) {
        User user = new User("John", "john@example.com");
        EmailService emailService = new EmailService();
        emailService.sendEmail(user, "Welcome!");
    }
}
