package Design_Datastructure.taskscheduler.versions;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AsynchronousTaskScheduler scheduler = new AsynchronousTaskScheduler(2, 4);

        Runnable task1 = () -> System.out.println("Executing Task 1 at " + System.currentTimeMillis());
        Runnable task2 = () -> System.out.println("Executing Task 2 at " + System.currentTimeMillis());

        // Schedule tasks with different schedules
        scheduler.schedule(task1, 2, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(task2, 1, 3, TimeUnit.SECONDS);

        // Allow some time for tasks to execute
        Thread.sleep(10000);

        // Shut down the scheduler
        scheduler.shutdown();
    }
}
