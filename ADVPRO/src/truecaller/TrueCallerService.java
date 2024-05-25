package truecaller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class TrueCallerService {
    Map<String, User> users = new HashMap<>();
    Map<String, Contact> contacts = new HashMap<>();
    Map<String, SpamReport> spamReports = new HashMap<>();

    public String register(String username, String password, String phoneNumber) {
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, username, password, phoneNumber);
        users.put(userId, user);
        return userId;
    }

    public String login(String username, String password) {
        for (User user : users.values()) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return user.id;
            }
        }
        return null;
    }

    public String addContact(String userId, String contactName, String contactNumber) {
        String contactId = UUID.randomUUID().toString();
        Contact contact = new Contact(contactId, userId, contactName, contactNumber);
        contacts.put(contactId, contact);
        return contactId;
    }

    public boolean deleteContact(String contactId) {
        return contacts.remove(contactId) != null;
    }

    public boolean blockContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact != null) {
            contact.isBlocked = true;
            return true;
        }
        return false;
    }

    public boolean unblockContact(String contactId) {
        Contact contact = contacts.get(contactId);
        if (contact != null) {
            contact.isBlocked = false;
            return true;
        }
        return false;
    }

    public boolean reportSpam(String reporterId, String contactId) {
        String reportId = UUID.randomUUID().toString();
        SpamReport spamReport = new SpamReport(reportId, reporterId, contactId);
        spamReports.put(reportId, spamReport);
        Contact contact = contacts.get(contactId);
        if (contact != null) {
            contact.isSpam = true;
        }
        return true;
    }

    public String identifyCaller(String phoneNumber) {
        for (Contact contact : contacts.values()) {
            if (contact.contactNumber.equals(phoneNumber)) {
                return contact.contactName;
            }
        }
        for (User user : users.values()) {
            if (user.phoneNumber.equals(phoneNumber)) {
                return user.username;
            }
        }
        return "Unknown Caller";
    }
}
