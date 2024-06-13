package notificationsystem;

import java.util.*;

public class ConfigurationService {
    private static ConfigurationService instance;
    private Map<Integer, Integer> userNotificationLimits = new HashMap<>();

    private ConfigurationService() {
        // Default limit
        userNotificationLimits.put(0, 2); // Default limit for all users
    }

    public static synchronized ConfigurationService getInstance() {
        if (instance == null) {
            instance = new ConfigurationService();
        }
        return instance;
    }

    public int getNotificationLimit(int userId) {
        return userNotificationLimits.getOrDefault(userId, userNotificationLimits.get(0));
    }

    public void setNotificationLimit(int userId, int limit) {
        userNotificationLimits.put(userId, limit);
    }
}
