package threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

// Singleton Pattern - ThreadPool Class
public class ThreadPool {

    private static ThreadPool instance;
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] workers;
    private final AtomicBoolean isShutDownInitiated;

    // Private Constructor for Singleton
    private ThreadPool(int numberOfThreads) {
        taskQueue = new LinkedBlockingQueue<>();
        workers = new Thread[numberOfThreads];
        isShutDownInitiated = new AtomicBoolean(false);

        for (int i = 0; i < numberOfThreads; i++) {
            workers[i] = new Worker(taskQueue, isShutDownInitiated);
            workers[i].start();
        }
    }

    // Singleton getInstance method
    public static synchronized ThreadPool getInstance(int numberOfThreads) {
        if (instance == null) {
            instance = new ThreadPool(numberOfThreads);
        }
        return instance;
    }

    // Method to submit task
    public void submitTask(Runnable task) {
        if (!isShutDownInitiated.get()) {
            taskQueue.offer(task);
        }
    }

    // Method to shutdown the pool
    public void shutdown() {
        isShutDownInitiated.set(true);
        for (Thread worker : workers) {
            worker.interrupt();
        }
    }
}

