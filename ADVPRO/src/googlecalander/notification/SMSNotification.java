package googlecalander.notification;

public class SMSNotification implements Notification {
    private String phoneNumber;

    public SMSNotification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String message) {
        // Implementation for sending SMS
        System.out.println("SMS sent to " + phoneNumber + ": " + message);
    }
}
