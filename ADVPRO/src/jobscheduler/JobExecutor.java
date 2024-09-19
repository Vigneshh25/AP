package jobscheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JobExecutor {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public void executeJob(Job job, int retryCount, JobLogger jobLogger) {
        executorService.submit(() -> {
            int attempt = 0;
            while (attempt <= retryCount) {
                attempt++;
                long startTime = System.currentTimeMillis();
                try {
                    job.execute();
                    long endTime = System.currentTimeMillis();
                    jobLogger.log(new JobExecutionContext(job.getJobId(), startTime, endTime, true, null));
                    break;
                } catch (Exception e) {
                    if (attempt > retryCount) {
                        long endTime = System.currentTimeMillis();
                        jobLogger.log(new JobExecutionContext(job.getJobId(), startTime, endTime, false, e));
                    }
                }
            }
        });
    }
}
