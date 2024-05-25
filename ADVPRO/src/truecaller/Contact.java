package truecaller;

import java.util.Date;

class Contact {
    String id;
    String userId;
    String contactName;
    String contactNumber;
    boolean isBlocked;
    boolean isSpam;
    Date createdAt;

    public Contact(String id, String userId, String contactName, String contactNumber) {
        this.id = id;
        this.userId = userId;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.isBlocked = false;
        this.isSpam = false;
        this.createdAt = new Date();
    }
}
