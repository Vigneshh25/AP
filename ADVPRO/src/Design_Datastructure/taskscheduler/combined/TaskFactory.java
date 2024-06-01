package Design_Datastructure.taskscheduler.combined;

// TaskFactory class to create tasks
class TaskFactory {
    public static Task createTask(int priority, Runnable action, int maxRetries) {
        return new Task(priority, action, maxRetries);
    }
}
