package Design_Datastructure.taskscheduler.combined;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class Main
    {

        public static void main(String[] args) throws InterruptedException {
            TaskScheduler scheduler = TaskScheduler.getInstance();

            Task task1 = TaskFactory.createTask(1, () -> System.out.println("Executing Task 1"), 3);
            Task task2 = TaskFactory.createTask(2, () -> System.out.println("Executing Task 2"), 2);
            Task task3 = TaskFactory.createTask(3, () -> System.out.println("Executing Task 3"), 1);

            List<Task> dependenciesForTask3 = new ArrayList<>();
            dependenciesForTask3.add(task1);
            dependenciesForTask3.add(task2);

            scheduler.addTask(task1, new ArrayList<>());
            scheduler.addTask(task2, new ArrayList<>());
            scheduler.addTask(task3, dependenciesForTask3);

            scheduler.scheduleTask(() -> System.out.println("Scheduled Task 1 completed"));
            scheduler.schedule(() -> System.out.println("Scheduled Task 2 completed after 2 seconds"), 2, TimeUnit.SECONDS);

            scheduler.start();
            scheduler.shutdown(true);

            System.out.println("Scheduler shutdown gracefully");
        }
    }