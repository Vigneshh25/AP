package Design_Datastructure.taskscheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

// BusRoute.shortestpath.Design_Datastructure.filesystem.Main class demonstrating the usage of TaskScheduler
public class TaskSchedulerMain {
    public static void main(String[] args) {
        TaskScheduler scheduler = TaskScheduler.getInstance();

        // Define some tasks with different priorities and retry limits
        Task task1 = new Task(1, () -> System.out.println("Executing Task 1"), 3);
        Task task2 = new Task(2, () -> System.out.println("Executing Task 2"), 2);
        Task task3 = new Task(3, () -> System.out.println("Executing Task 3"), 1);

        // Define dependencies (task3 depends on task1 and task2)
        List<Task> dependenciesForTask3 = new ArrayList<>();
        dependenciesForTask3.add(task1);
        dependenciesForTask3.add(task2);

        // Add tasks to scheduler
        scheduler.addTask(task1, new ArrayList<>());
        scheduler.addTask(task2, new ArrayList<>());
        scheduler.addTask(task3, dependenciesForTask3);

        // Start the scheduler
        scheduler.start();

        // Shutdown after all tasks are done (in a real scenario, you'd have a better shutdown mechanism)
        scheduler.shutdown();
    }
}
