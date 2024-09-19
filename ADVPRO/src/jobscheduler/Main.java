package jobscheduler;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        JobRegistry jobRegistry = new JobRegistry();
        jobRegistry.registerJobType("EmailJob", EmailJob.class);

        JobScheduler jobScheduler = new JobScheduler();

        Job emailJob = jobRegistry.createJob("EmailJob");

        // Schedule the job to run at fixed intervals of 10 seconds with a retry count of 3
        jobScheduler.scheduleAtFixedRate(emailJob, 0, 10, TimeUnit.SECONDS, 3);

        // Optionally shut down the scheduler after some time (for testing purposes)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            jobScheduler.shutdown();
            System.out.println("Scheduler shutdown.");
        }));
    }
}
