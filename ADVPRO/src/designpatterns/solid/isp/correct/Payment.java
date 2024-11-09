package designpatterns.solid.isp.correct;


/*
 * interface segregation simply means that we should break larger interfaces into smaller ones.
 * Clients should not be forced to depend upon interfaces that they do not use
 *
 * By applying ISP, you reduce the impact of changes, enhance code maintainability, and improve the
 * overall design of the system.
 * */
// Specific notification interfaces
interface EmailNotificationService {
    void sendEmail(String message);
}

interface SMSNotificationService {
    void sendSMS(String message);
}

interface PushNotificationService {
    void sendPushNotification(String message);
}

// Email notification class implements only what it needs
class EmailNotification implements EmailNotificationService {
    @Override
    public void sendEmail(String message) {
        System.out.println("Email sent: " + message);
    }
}

// SMS notification class implements only what it needs
class SMSNotification implements SMSNotificationService {
    @Override
    public void sendSMS(String message) {
        System.out.println("SMS sent: " + message);
    }
}

// Push notification class implements only what it needs
class PushNotification implements PushNotificationService {
    @Override
    public void sendPushNotification(String message) {
        System.out.println("Push notification sent: " + message);
    }
}

// Main method to demonstrate usage
class Main {
    public static void main(String[] args) {
        EmailNotificationService emailNotification = new EmailNotification();
        emailNotification.sendEmail("Hello via Email!");

        SMSNotificationService smsNotification = new SMSNotification();
        smsNotification.sendSMS("Hello via SMS!");

        PushNotificationService pushNotification = new PushNotification();
        pushNotification.sendPushNotification("Hello via Push Notification!");
    }
}
