package Design_Datastructure.taskscheduler;

import java.util.concurrent.PriorityBlockingQueue;

// PriorityTaskQueue class to manage task queue with priority handling
public class PriorityTaskQueue {
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
