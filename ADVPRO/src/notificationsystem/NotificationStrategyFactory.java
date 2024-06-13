package notificationsystem;

public class NotificationStrategyFactory {
    public static NotificationStrategy getStrategy(NotificationType type) {
        switch (type) {
            case EMAIL:
                return new EmailNotificationStrategy();
            case SMS:
                return new SmsNotificationStrategy();
            default:
                throw new IllegalArgumentException("Unknown notification type: " + type);
        }
    }
}
