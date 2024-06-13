package notificationsystem;

public class EmailNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String contact, String message) {
        // Implement email sending logic here
        System.out.println("Sending email to " + contact + ": " + message);
    }
}
