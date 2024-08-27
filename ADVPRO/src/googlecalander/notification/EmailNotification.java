package googlecalander.notification;

public class EmailNotification implements Notification {
    private String email;

    public EmailNotification(String email) {
        this.email = email;
    }

    @Override
    public void send(String message) {
        // Implementation for sending email
        System.out.println("Email sent to " + email + ": " + message);
    }
}
