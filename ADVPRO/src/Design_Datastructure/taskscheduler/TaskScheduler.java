package Design_Datastructure.taskscheduler;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

// TaskScheduler class to manage tasks, dependencies, and worker pool
public class TaskScheduler {
    private static TaskScheduler instance;
    private final PriorityTaskQueue taskQueue = new PriorityTaskQueue();
    private final WorkerPool workerPool;
    private final ConcurrentMap<Task, List<Task>> dependencyGraph = new ConcurrentHashMap<>();
    private final Set<Task> completedTasks = ConcurrentHashMap.newKeySet();

    private TaskScheduler(int poolSize) {
        this.workerPool = new WorkerPool(poolSize);
    }

    public static synchronized TaskScheduler getInstance() {
        if (instance == null) {
            instance = new TaskScheduler(10); // Example pool size
        }
        return instance;
    }

    public void addTask(Task task, List<Task> dependencies) {
        dependencyGraph.put(task, dependencies);
        if (dependencies.isEmpty()) {
            taskQueue.addTask(task);
        }
    }

    public void start() {
        while (!taskQueue.isEmpty() || workerPool.hasPendingTasks()) {
            Task task = taskQueue.getTask();
            if (task != null) {
                workerPool.submitTask(() -> executeTask(task));
            }
        }
    }

    private void executeTask(Task task) {
        task.run();
        completedTasks.add(task);
        dependencyGraph.forEach((t, deps) -> {
            deps.remove(task);
            if (!deps.isEmpty() && !completedTasks.contains(t)) {
                taskQueue.addTask(t);
            }
        });
    }

    public void submitTask(Task task) {
        taskQueue.addTask(task);
    }

    public void shutdown() {
        workerPool.shutdown();
    }
}
