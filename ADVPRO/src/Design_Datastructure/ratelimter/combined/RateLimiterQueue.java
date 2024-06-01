package Design_Datastructure.ratelimter.combined;

import java.util.concurrent.ConcurrentLinkedQueue;

public class RateLimiterQueue {
    private final ConcurrentLinkedQueue<Runnable> taskQueue;

    public RateLimiterQueue() {
        this.taskQueue = new ConcurrentLinkedQueue<>();
    }

    public void addTask(Runnable task) {
        taskQueue.add(task);
    }

    public Runnable pollTask() {
        return taskQueue.poll();
    }

    public boolean hasPendingTasks() {
        return !taskQueue.isEmpty();
    }
}
