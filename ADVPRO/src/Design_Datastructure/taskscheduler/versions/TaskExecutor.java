package Design_Datastructure.taskscheduler.versions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutor {
    private final ExecutorService executorService;

    public TaskExecutor(int poolSize) {
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    public void executeTask(Runnable task) {
        executorService.submit(task);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
