package Design_Datastructure.taskscheduler.combined;

import java.util.*;
import java.util.concurrent.*;

public class TaskScheduler {
    private static TaskScheduler instance;
    private final PriorityTaskQueue taskQueue;
    private final WorkerPool workerPool;
    private final ConcurrentMap<Task, List<Task>> dependencyGraph;
    private final Set<Task> completedTasks;
    private final ScheduledExecutorService scheduler;
    private final ExecutorService taskExecutor;
    private final Map<UUID, TaskWrapper> runningTasks;
    private final Semaphore semaphore;

    private TaskScheduler(int poolSize, int corePoolSize, int maxPoolSize) {
        this.taskQueue = new PriorityTaskQueue();
        this.workerPool = new WorkerPool(poolSize);
        this.dependencyGraph = new ConcurrentHashMap<>();
        this.completedTasks = ConcurrentHashMap.newKeySet();
        this.scheduler = Executors.newScheduledThreadPool(corePoolSize);
        this.taskExecutor = Executors.newFixedThreadPool(maxPoolSize);
        this.runningTasks = new ConcurrentHashMap<>();
        this.semaphore = new Semaphore(poolSize);
    }

    public static synchronized TaskScheduler getInstance() {
        if (instance == null) {
            instance = new TaskScheduler(10, 2, 4); // Example pool sizes
        }
        return instance;
    }

    public void addTask(Task task, List<Task> dependencies) {
        dependencyGraph.put(task, dependencies);
        if (dependencies.isEmpty()) {
            taskQueue.addTask(task);
        }
    }

    public void submitTask(Task task) {
        taskQueue.addTask(task);
        assignTasks();
    }

    public UUID scheduleTask(Runnable task) {
        UUID taskId = UUID.randomUUID();
        TaskWrapper taskWrapper = new TaskWrapper(taskId, task);
        taskQueue.addTask(taskWrapper);
        assignTasks();
        return taskId;
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

    public boolean cancelTask(UUID taskId) {
        TaskWrapper taskWrapper = runningTasks.get(taskId);
        if (taskWrapper != null) {
            return false; // Task is running and cannot be cancelled
        }
        return taskQueue.removeTaskWrapper(taskId);
    }

    private void assignTasks() {
        while (!taskQueue.isEmpty()) {
            try {
                semaphore.acquire(); // Wait for a worker thread to become available
                Task task = taskQueue.getTask();
                if (task != null) {
                    runningTasks.put(((TaskWrapper) task).getTaskId(), (TaskWrapper) task);
                    workerPool.submitTask(() -> {
                        try {
                            task.run();
                        } finally {
                            runningTasks.remove(((TaskWrapper) task).getTaskId());
                            semaphore.release(); // Release the permit for the worker thread
                            assignTasks();
                        }
                    });
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void shutdown(boolean graceful) {
        if (graceful) {
            workerPool.shutdown();
            try {
                workerPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            workerPool.shutdownNow();
            taskQueue.clear();
        }
        scheduler.shutdown();
        taskExecutor.shutdown();
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
            if (deps.isEmpty() && !completedTasks.contains(t)) {
                taskQueue.addTask(t);
            }
        });
    }
}