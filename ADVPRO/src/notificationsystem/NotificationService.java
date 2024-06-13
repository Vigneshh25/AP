package notificationsystem;

import java.util.*;

public class NotificationService {
    private UserService userService;
    private NotificationStore notificationStore;
    private ConfigurationService configurationService;

    public NotificationService(UserService userService, NotificationStore notificationStore, ConfigurationService configurationService) {
        this.userService = userService;
        this.notificationStore = notificationStore;
        this.configurationService = configurationService;
    }

    public void sendNotification(int userId, String message) {
        int notificationLimit = configurationService.getNotificationLimit(userId);
        int notificationsSentToday = notificationStore.getNotificationsSentToday(userId);

        if (notificationsSentToday < notificationLimit) {
            send(userId, message);
            notificationStore.addNotification(userId, message);
        } else {
            deferNotification(userId, message);
        }
    }

    private void send(int userId, String message) {
        // Get user contact details
        User user = userService.getUser(userId);
        NotificationStrategy strategy = NotificationStrategyFactory.getStrategy(user.getNotificationType());
        strategy.sendNotification(user.getContact(), message);
    }

    public void deferNotification(int userId, String message) {
        notificationStore.addDeferredNotification(userId, message);
    }
}
