package Design_Datastructure.taskscheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

// WorkerPool class to manage a pool of worker threads for task execution
import java.util.concurrent.*;

public class WorkerPool {
    private final ThreadPoolExecutor executor;

    public WorkerPool(int poolSize) {
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
    }

    public void submitTask(Runnable task) {
        executor.execute(task);
    }

    public boolean hasPendingTasks() {
        return executor.getActiveCount() > 0 || !executor.getQueue().isEmpty();
    }

    public void shutdown() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Executor did not terminate");
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

