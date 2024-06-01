package Design_Datastructure.taskscheduler.combined;

import java.util.UUID;

// TaskWrapper class to wrap tasks with a UUID for identification
class TaskWrapper extends Task {
    private final UUID taskId;

    public TaskWrapper(UUID taskId, Runnable task) {
        super(1, task, 0); // Default priority and no retries
        this.taskId = taskId;
    }

    public UUID getTaskId() {
        return taskId;
    }
}
