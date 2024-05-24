package Design_Datastructure.taskscheduler.versions;

import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskQueue {
    private final ConcurrentLinkedQueue<Runnable> taskQueue;

    public TaskQueue() {
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
