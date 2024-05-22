import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// Task class representing individual tasks with priority, retries, and execution logic
class Task implements Comparable<Task>, Runnable {
    private final int priority;
    private final Runnable action;
    private final int maxRetries;
    private final AtomicInteger currentRetry = new AtomicInteger(0);

    public Task(int priority, Runnable action, int maxRetries) {
        this.priority = priority;
        this.action = action;
        this.maxRetries = maxRetries;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public void run() {
        try {
            action.run();
        } catch (Exception e) {
            if (currentRetry.incrementAndGet() <= maxRetries) {
                System.out.println("Retrying task: " + this);
                TaskScheduler.getInstance().submitTask(this);
            } else {
                handleFailure(e);
            }
        }
    }

    private void handleFailure(Exception e) {
        System.err.println("Task failed after " + maxRetries + " retries: " + e.getMessage());
    }

    @Override
    public String toString() {
        return "Task{" +
                "priority=" + priority +
                ", maxRetries=" + maxRetries +
                ", currentRetry=" + currentRetry +
                '}';
    }
}

// PriorityTaskQueue class to manage task queue with priority handling
class PriorityTaskQueue {
    private final PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

    public void addTask(Task task) {
        queue.put(task);
    }

    public Task getTask() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// WorkerPool class to manage a pool of worker threads for task execution
class WorkerPool {
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

// TaskScheduler class to manage tasks, dependencies, and worker pool
class TaskScheduler {
    private static TaskScheduler instance;
    private final PriorityTaskQueue taskQueue = new PriorityTaskQueue();
    private final WorkerPool workerPool;
    private final ConcurrentMap<Task, List<Task>> dependencyGraph = new ConcurrentHashMap<>();

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
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.getTask();
            workerPool.submitTask(() -> executeTask(task));
        }
    }

    private void executeTask(Task task) {
        task.run();
        dependencyGraph.forEach((t, deps) -> {
            deps.remove(task);
            if (deps.isEmpty()) {
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

// Main class demonstrating the usage of TaskScheduler
public class Main {
    public static void main(String[] args) {
        TaskScheduler scheduler = TaskScheduler.getInstance();

        // Define some tasks with different priorities and retry limits
        Task task1 = new Task(1, () -> System.out.println("Executing Task 1"), 3);
        Task task2 = new Task(2, () -> System.out.println("Executing Task 2"), 2);
        Task task3 = new Task(3, () -> System.out.println("Executing Task 3"), 1);

        // Define dependencies (task3 depends on task1 and task2)
        List<Task> dependenciesForTask3 = new ArrayList<>();
        dependenciesForTask3.add(task1);
        dependenciesForTask3.add(task2);

        // Add tasks to scheduler
        scheduler.addTask(task1, new ArrayList<>());
        scheduler.addTask(task2, new ArrayList<>());
        scheduler.addTask(task3, dependenciesForTask3);

        // Start the scheduler
        scheduler.start();

        // Shutdown after all tasks are done (in a real scenario, you'd have a better shutdown mechanism)
        scheduler.shutdown();
    }
}
