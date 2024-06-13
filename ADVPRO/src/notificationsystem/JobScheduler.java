package notificationsystem;

public class JobScheduler {
    private NotificationService notificationService;

    public JobScheduler(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void completeJob(int jobId, int userId) {
        // Notify user on job completion
        String message = "Your job with ID: " + jobId + " has been completed.";
        notificationService.sendNotification(userId, message);
    }
}
