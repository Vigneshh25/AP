package designpatterns.solid.isp.violates;

// General notification interface
interface NotificationService {
    void sendEmail(String message);
    void sendSMS(String message);
    void sendPushNotification(String message);
}

// A class that only needs to send emails
class EmailNotification implements NotificationService {
    @Override
    public void sendEmail(String message) {
        System.out.println("Email sent: " + message);
    }

    @Override
    public void sendSMS(String message) {
        // Not applicable
        throw new UnsupportedOperationException("SMS not supported");
    }

    @Override
    public void sendPushNotification(String message) {
        // Not applicable
        throw new UnsupportedOperationException("Push notification not supported");
    }
}
