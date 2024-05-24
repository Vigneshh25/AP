package Design_Datastructure.taskscheduler.versions;

import java.util.concurrent.*;

public class ScheduledTask implements Runnable {
    private final Runnable task;
    private final long delay;
    private final TimeUnit unit;
    private final AsynchronousTaskScheduler scheduler;

    public ScheduledTask(Runnable task, long delay, TimeUnit unit, AsynchronousTaskScheduler scheduler) {
        this.task = task;
        this.delay = delay;
        this.unit = unit;
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        scheduler.schedule(task, delay, unit);
    }
}
