package notificationsystem;

public class SmsNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String contact, String message) {
        // Implement SMS sending logic here
        System.out.println("Sending SMS to " + contact + ": " + message);
    }
}
