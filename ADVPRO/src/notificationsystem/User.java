package notificationsystem;

public class User {
    private int id;
    private String contact;
    private NotificationType notificationType;

    public User(int id, String contact, NotificationType notificationType) {
        this.id = id;
        this.contact = contact;
        this.notificationType = notificationType;
    }

    public int getId() {
        return id;
    }

    public String getContact() {
        return contact;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }
}
