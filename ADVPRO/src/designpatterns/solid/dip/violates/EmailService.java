package designpatterns.solid.dip.violates;

// Low-level module
public class EmailService {
    public void sendEmail(String message) {
        System.out.println("Email sent: " + message);
    }
}

// High-level module
class NotificationService {
    private EmailService emailService;

    public NotificationService() {
        this.emailService = new EmailService(); // Direct dependency
    }

    public void notify(String message) {
        emailService.sendEmail(message);
    }
}



