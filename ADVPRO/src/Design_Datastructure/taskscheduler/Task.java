package Design_Datastructure.taskscheduler;

import java.util.concurrent.atomic.AtomicInteger;

// Task class representing individual tasks with priority, retries, and execution logic
public class Task implements Comparable<Task>, Runnable {
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
