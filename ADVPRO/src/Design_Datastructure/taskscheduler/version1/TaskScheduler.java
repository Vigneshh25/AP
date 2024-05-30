package Design_Datastructure.taskscheduler.version1;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskScheduler {

    private final int maxWorkers;
    private final ExecutorService workerPool;
    private final Queue<TaskWrapper> taskQueue;
    private final Map<UUID, TaskWrapper> runningTasks;
    private final AtomicInteger activeWorkers;

    public TaskScheduler(int maxWorkers) {
        this.maxWorkers = maxWorkers;
        this.workerPool = Executors.newCachedThreadPool();
        this.taskQueue = new ConcurrentLinkedQueue<>();
        this.runningTasks = new ConcurrentHashMap<>();
        this.activeWorkers = new AtomicInteger(0);
    }

    public UUID scheduleTask(Runnable task) {
        UUID taskId = UUID.randomUUID();
        TaskWrapper taskWrapper = new TaskWrapper(taskId, task);
        taskQueue.offer(taskWrapper);
        assignTasks();
        return taskId;
    }

    private void assignTasks() {
        while (activeWorkers.get() < maxWorkers && !taskQueue.isEmpty()) {
            TaskWrapper taskWrapper = taskQueue.poll();
            if (taskWrapper != null) {
                runningTasks.put(taskWrapper.getTaskId(), taskWrapper);
                activeWorkers.incrementAndGet();
                workerPool.submit(() -> {
                    try {
                        taskWrapper.getTask().run();
                    } finally {
                        runningTasks.remove(taskWrapper.getTaskId());
                        activeWorkers.decrementAndGet();
                        assignTasks();
                    }
                });
            }
        }
    }

    public boolean cancelTask(UUID taskId) {
        TaskWrapper taskWrapper = runningTasks.get(taskId);
        if (taskWrapper != null) {
            return false; // Task is running and cannot be cancelled
        }
        return taskQueue.removeIf(task -> task.getTaskId().equals(taskId));
    }

    public void shutdown(boolean graceful) {
        if (graceful) {
            workerPool.shutdown();
            try {
                workerPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            workerPool.shutdownNow();
            taskQueue.clear();
        }
    }

    private static class TaskWrapper {
        private final UUID taskId;
        private final Runnable task;

        public TaskWrapper(UUID taskId, Runnable task) {
            this.taskId = taskId;
            this.task = task;
        }

        public UUID getTaskId() {
            return taskId;
        }

        public Runnable getTask() {
            return task;
        }
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler(3);

        UUID task1 = scheduler.scheduleTask(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Task 1 completed");
            } catch (InterruptedException e) {
                System.out.println("Task 1 interrupted");
            }
        });

        UUID task2 = scheduler.scheduleTask(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Task 2 completed");
            } catch (InterruptedException e) {
                System.out.println("Task 2 interrupted");
            }
        });

        scheduler.scheduleTask(() -> System.out.println("Task 3 completed"));

        boolean cancelled = scheduler.cancelTask(task1);
        System.out.println("Task 1 cancelled: " + cancelled);

        scheduler.shutdown(true);
        System.out.println("Scheduler shutdown gracefully");
    }
}
