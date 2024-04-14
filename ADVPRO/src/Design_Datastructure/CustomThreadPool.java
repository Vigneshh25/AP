import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {
    private final int poolSize;
    private final BlockingQueue<Runnable> taskQueue;
    private final List<WorkerThread> threads;

    public CustomThreadPool(int poolSize) {
        this.poolSize = poolSize;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threads = new ArrayList<>();

        // Create and start worker threads
        for (int i = 0; i < poolSize; i++) {
            WorkerThread workerThread = new WorkerThread();
            workerThread.start();
            threads.add(workerThread);
        }
    }

    public void submit(Runnable task) {
        try {
            taskQueue.put(task); // Add task to the queue
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        for (WorkerThread thread : threads) {
            thread.stopThread(); // Signal worker threads to stop
        }
    }

    private class WorkerThread extends Thread {
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                try {
                    Runnable task = taskQueue.take(); // Retrieve task from the queue
                    task.run(); // Execute the task
                } catch (InterruptedException e) {
                    running = false;
                    Thread.currentThread().interrupt();
                }
            }
        }

        public void stopThread() {
            running = false;
            interrupt(); // Interrupt the thread to wake it up from blocking operation
        }
    }

    public static void main(String[] args) {
        // Create a custom thread pool with 3 worker threads
        CustomThreadPool threadPool = new CustomThreadPool(3);

        // Submit tasks to the thread pool
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            threadPool.submit(() -> {
                System.out.println("Task " + taskId + " executed by thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // Simulate task execution time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Shutdown the thread pool
        threadPool.shutdown();
    }
}
