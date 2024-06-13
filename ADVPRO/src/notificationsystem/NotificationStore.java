package notificationsystem;

import java.util.*;

public class NotificationStore {
    private static NotificationStore instance;
    private Map<Integer, List<String>> notificationsSentToday = new HashMap<>();
    private Map<Integer, List<String>> deferredNotifications = new HashMap<>();

    private NotificationStore() {}

    public static synchronized NotificationStore getInstance() {
        if (instance == null) {
            instance = new NotificationStore();
        }
        return instance;
    }

    public int getNotificationsSentToday(int userId) {
        return notificationsSentToday.getOrDefault(userId, Collections.emptyList()).size();
    }

    public void addNotification(int userId, String message) {
        notificationsSentToday.computeIfAbsent(userId, k -> new ArrayList<>()).add(message);
    }

    public void addDeferredNotification(int userId, String message) {
        deferredNotifications.computeIfAbsent(userId, k -> new ArrayList<>()).add(message);
    }

    public List<String> getDeferredNotifications(int userId) {
        return deferredNotifications.getOrDefault(userId, Collections.emptyList());
    }

    public void clearDailyNotifications() {
        notificationsSentToday.clear();
        deferredNotifications.forEach((userId, messages) -> {
            notificationsSentToday.put(userId, new ArrayList<>(messages));
        });
        deferredNotifications.clear();
    }
}
