package notificationsystem;

public class Main {
    public static void main(String[] args) {
        // Initialize services
        UserService userService = new UserService();
        NotificationStore notificationStore = NotificationStore.getInstance();
        ConfigurationService configurationService = ConfigurationService.getInstance();
        NotificationService notificationService = new NotificationService(userService, notificationStore, configurationService);
        JobScheduler jobScheduler = new JobScheduler(notificationService);

        // Simulate job completions
        jobScheduler.completeJob(1, 1);
        jobScheduler.completeJob(2, 1);
        jobScheduler.completeJob(3, 1); // This should be deferred
        jobScheduler.completeJob(4, 2);
        jobScheduler.completeJob(5, 2); // This should be deferred

        // Simulate end of day processing
        System.out.println("End of day processing...");
        notificationStore.clearDailyNotifications();

        // Simulate new day job completions
        jobScheduler.completeJob(6, 1); // This should send the deferred message
        jobScheduler.completeJob(7, 2); // This should send the deferred message
    }
}
