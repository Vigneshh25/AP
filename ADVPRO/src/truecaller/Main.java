package truecaller;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TrueCallerService service = new TrueCallerService();

        // User registration
        String userId = service.register("john_doe", "password123", "1234567890");
        System.out.println("Registered User ID: " + userId);

        // User login
        String loginUserId = service.login("john_doe", "password123");
        System.out.println("Logged in User ID: " + loginUserId);

        // Add contacts
        String contactId = service.addContact(userId, "Jane Smith", "0987654321");
        System.out.println("Added Contact ID: " + contactId);

        // Identify caller
        String callerName = service.identifyCaller("0987654321");
        System.out.println("Caller Name: " + callerName);

        // Block contact
        boolean isBlocked = service.blockContact(contactId);
        System.out.println("Contact Blocked: " + isBlocked);

        // Report spam
        boolean isReported = service.reportSpam(userId, contactId);
        System.out.println("Contact Reported as Spam: " + isReported);
    }
}
