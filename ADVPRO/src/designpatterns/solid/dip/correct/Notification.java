package designpatterns.solid.dip.correct;


/*
    High-level modules should not depend on low-level modules. Both should depend on abstractions

    Abstractions should not depend on details. Details (concrete implementations) should depend on
    abstractions.

    instead of high-level modules depending on low-level modules, both will depend on abstractions.
    Every dependency in the design should target an interface or an abstract class.
    No dependency should target a concrete class.

    High-Level Module: A module that contains complex logic and coordinates activities.
    It typically defines the overall policy of the system.
    Low-Level Module: A module that deals with detailed operations or implementations.
    It usually performs specific tasks, such as accessing databases or external APIs.

    By adhering to the Dependency Inversion Principle, you reduce the coupling between modules,
    making it easier to change or extend parts of the system without impacting others.
* */
// Abstraction
public interface Notification {
    void send(String message);
}

// Low-level module
class EmailService implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Email sent: " + message);
    }
}

// Another low-level module
class SMSService implements Notification {
    @Override
    public void send(String message) {
        System.out.println("SMS sent: " + message);
    }
}

// High-level module
class NotificationService {
    private final Notification notification;

    // Dependency Injection through constructor
    public NotificationService(Notification notification) {
        this.notification = notification;
    }

    public void notify(String message) {
        notification.send(message);
    }
}

// Main method to demonstrate the usage
class Main {
    public static void main(String[] args) {
        Notification emailService = new EmailService();
        NotificationService notificationService = new NotificationService(emailService);
        notificationService.notify("Hello, World!");

        // If we want to use SMS service instead
        Notification smsService = new SMSService();
        NotificationService notificationServiceSMS = new NotificationService(smsService);
        notificationServiceSMS.notify("Hello via SMS!");
    }
}

