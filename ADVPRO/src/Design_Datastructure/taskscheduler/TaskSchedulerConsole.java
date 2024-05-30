package Design_Datastructure.taskscheduler;

import java.util.*;

public class TaskSchedulerConsole {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TaskScheduler scheduler = TaskScheduler.getInstance();

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. Start Scheduler");
            System.out.println("3. Shutdown Scheduler");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTask(scheduler);
                    break;
                case 2:
                    scheduler.start();
                    break;
                case 3:
                    scheduler.shutdown();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTask(TaskScheduler scheduler) {
        System.out.print("Enter task priority: ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter max retries: ");
        int maxRetries = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        Task task = new Task(priority, () -> System.out.println("Executing Task: " + description), maxRetries);

        System.out.print("Enter number of dependencies: ");
        int numDeps = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Task> dependencies = new ArrayList<>();
        for (int i = 0; i < numDeps; i++) {
            System.out.print("Enter dependency task priority: ");
            int depPriority = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // For simplicity, assume dependencies are already defined tasks with given priority
            // In a real scenario, you would manage tasks with unique identifiers
            dependencies.add(new Task(depPriority, () -> {}, 0));
        }

        scheduler.addTask(task, dependencies);
    }
}
