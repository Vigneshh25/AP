package meetingroom;

public class EmailNotification implements Observer {
    private String email;

    public EmailNotification(String email) {
        this.email = email;
    }

    @Override
    public void update(String message) {
        // Simulate sending email
        System.out.println("Email sent to " + email + ": " + message);
    }
}
