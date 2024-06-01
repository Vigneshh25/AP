package Design_Datastructure.taskscheduler.combined;

import java.util.UUID;
import java.util.concurrent.PriorityBlockingQueue;

// PriorityTaskQueue class to manage tasks based on priority
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

    public void clear() {
        queue.clear();
    }

    public TaskWrapper getTaskWrapperById(UUID taskId) {
        for (Task task : queue) {
            if (task instanceof TaskWrapper && ((TaskWrapper) task).getTaskId().equals(taskId)) {
                return (TaskWrapper) task;
            }
        }
        return null;
    }

    public boolean removeTaskWrapper(UUID taskId) {
        return queue.removeIf(task -> ((TaskWrapper) task).getTaskId().equals(taskId));
    }
}
