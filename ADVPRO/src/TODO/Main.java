package TODO;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Add tasks
        taskManager.addTask(new Task("Finish project report", LocalDateTime.of(2024, 6, 20, 17, 0), Arrays.asList("work")));
        taskManager.addTask(new Task("Buy groceries", null, Arrays.asList("personal", "shopping")));
        taskManager.addTask(new Task("Doctor's appointment", LocalDateTime.of(2024, 6, 15, 10, 30), Arrays.asList("health")));

        // List tasks
        System.out.println("Tasks:");
        taskManager.listTasks().forEach(System.out::println);

        // Complete a task
        taskManager.completeTask(2);

        // Modify a task
        Task modifiedTask = new Task("Buy groceries and cook dinner", null, Arrays.asList("personal", "shopping"));
        modifiedTask.setCompleted(false);  // just to ensure we are modifying the existing task
        modifiedTask.setId(2);  // setting the existing task's ID
        taskManager.modifyTask(modifiedTask);

        // List tasks again
        System.out.println("\nTasks after modifications:");
        taskManager.listTasks().forEach(System.out::println);

        // Get activity log
        System.out.println("\nActivity log:");
        taskManager.getActivityLog(LocalDateTime.of(2024, 6, 1, 0, 0), LocalDateTime.now()).forEach(System.out::println);

        // Get statistics
        Statistics stats = taskManager.getStatistics(LocalDateTime.of(2024, 6, 1, 0, 0), LocalDateTime.now());
        System.out.println("\nStatistics:");
        System.out.println(stats);
    }
}
