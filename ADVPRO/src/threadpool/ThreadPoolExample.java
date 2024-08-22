package threadpool;

/**
 * Created by Vignesh.V on 19/08/24.
 */ // Main Class to test the ThreadPool implementation
public class ThreadPoolExample {

    public static void main(String[] args) {
        ThreadPool threadPool = ThreadPool.getInstance(3);

        // Submitting tasks to ThreadPool
        for (int i = 1; i <= 10; i++) {
            Task task = new Task("Task-" + i);
            threadPool.submitTask(task);
        }

        // Shutting down the thread pool
        threadPool.shutdown();
    }
}
