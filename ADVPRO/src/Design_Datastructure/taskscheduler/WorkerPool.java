package Design_Datastructure.taskscheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

// WorkerPool class to manage a pool of worker threads for task execution
public class WorkerPool {
    private final ThreadPoolExecutor executor;

    public WorkerPool(int poolSize) {
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
    }

    public void submitTask(Runnable task) {
        executor.execute(task);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
