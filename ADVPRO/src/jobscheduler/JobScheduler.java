package jobscheduler;

import java.util.concurrent.*;

public class JobScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);  // Handle concurrent jobs
    private final JobExecutor jobExecutor = new JobExecutor();
    private final JobLogger jobLogger = new JobLogger();

    public void scheduleAtFixedRate(Job job, long initialDelay, long period, TimeUnit unit, int retryCount) {
        scheduler.scheduleAtFixedRate(() -> jobExecutor.executeJob(job, retryCount, jobLogger), initialDelay, period, unit);
    }

    public void scheduleOnce(Job job, long delay, TimeUnit unit, int retryCount) {
        scheduler.schedule(() -> jobExecutor.executeJob(job, retryCount, jobLogger), delay, unit);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
