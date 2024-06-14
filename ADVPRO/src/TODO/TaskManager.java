package TODO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks;
    private List<ActivityLog> activityLogs;

    public TaskManager() {
        tasks = new ArrayList<>();
        activityLogs = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        activityLogs.add(new ActivityLog(LocalDateTime.now(), "Added task: " + task));
    }

    public Task getTask(int taskId) {
        return tasks.stream()
                .filter(task -> task.getId() == taskId)
                .findFirst()
                .orElse(null);
    }

    public void modifyTask(Task modifiedTask) {
        Task task = getTask(modifiedTask.getId());
        if (task != null) {
            task.setDescription(modifiedTask.getDescription());
            task.setDeadline(modifiedTask.getDeadline());
            task.setTags(modifiedTask.getTags());
            activityLogs.add(new ActivityLog(LocalDateTime.now(), "Modified task: " + task));
        }
    }

    public void removeTask(int taskId) {
        Task task = getTask(taskId);
        if (task != null) {
            tasks.remove(task);
            activityLogs.add(new ActivityLog(LocalDateTime.now(), "Removed task: " + task));
        }
    }

    public List<Task> listTasks() {
        return tasks.stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
    }

    public void completeTask(int taskId) {
        Task task = getTask(taskId);
        if (task != null) {
            task.setCompleted(true);
            tasks.remove(task);
            activityLogs.add(new ActivityLog(LocalDateTime.now(), "Completed task: " + task));
        }
    }

    public List<ActivityLog> getActivityLog(LocalDateTime from, LocalDateTime to) {
        return activityLogs.stream()
                .filter(log -> !log.getTimestamp().isBefore(from) && !log.getTimestamp().isAfter(to))
                .collect(Collectors.toList());
    }

    public Statistics getStatistics(LocalDateTime from, LocalDateTime to) {
        long addedTasks = activityLogs.stream()
                .filter(log -> log.getDescription().startsWith("Added task") && 
                        !log.getTimestamp().isBefore(from) && 
                        !log.getTimestamp().isAfter(to))
                .count();

        long completedTasks = activityLogs.stream()
                .filter(log -> log.getDescription().startsWith("Completed task") && 
                        !log.getTimestamp().isBefore(from) && 
                        !log.getTimestamp().isAfter(to))
                .count();

        long missedDeadlines = tasks.stream()
                .filter(task -> task.getDeadline() != null && 
                        task.getDeadline().isBefore(LocalDateTime.now()) && 
                        !task.isCompleted())
                .count();

        return new Statistics(addedTasks, completedTasks, missedDeadlines);
    }
}
