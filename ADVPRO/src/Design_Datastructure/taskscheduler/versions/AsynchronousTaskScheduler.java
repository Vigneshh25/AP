package Design_Datastructure.taskscheduler.versions;

import java.util.concurrent.*;
import java.util.*;

public class AsynchronousTaskScheduler {
    private final ScheduledExecutorService scheduler;
    private final ExecutorService taskExecutor;

    public AsynchronousTaskScheduler(int corePoolSize, int maxPoolSize) {
        this.scheduler = Executors.newScheduledThreadPool(corePoolSize);
        this.taskExecutor = Executors.newFixedThreadPool(maxPoolSize);
    }

    public ScheduledFuture<?> schedule(Runnable task, long delay, TimeUnit unit) {
        return scheduler.schedule(() -> taskExecutor.submit(task), delay, unit);
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        return scheduler.scheduleAtFixedRate(() -> taskExecutor.submit(task), initialDelay, period, unit);
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable task, long initialDelay, long delay, TimeUnit unit) {
        return scheduler.scheduleWithFixedDelay(() -> taskExecutor.submit(task), initialDelay, delay, unit);
    }

    public void shutdown() {
        scheduler.shutdown();
        taskExecutor.shutdown();
    }

    public List<Runnable> shutdownNow() {
        scheduler.shutdownNow();
        return taskExecutor.shutdownNow();
    }
}
