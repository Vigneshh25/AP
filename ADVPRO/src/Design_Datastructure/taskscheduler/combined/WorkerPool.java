package Design_Datastructure.taskscheduler.combined;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// WorkerPool class to manage a pool of worker threads
class WorkerPool {
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

    public void shutdownNow() {
        executor.shutdownNow();
    }

    public int getActiveCount() {
        return executor.getActiveCount();
    }

    public int getPoolSize() {
        return executor.getCorePoolSize();
    }

    public void awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        executor.awaitTermination(timeout, unit);
    }
}
