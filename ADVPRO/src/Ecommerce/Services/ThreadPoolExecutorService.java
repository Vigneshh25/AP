package Ecommerce.Services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorService {
    private static volatile ThreadPoolExecutorService instance;
    private ExecutorService executorService;

    private ThreadPoolExecutorService() {
        executorService = Executors.newFixedThreadPool(10);
    }

    public static ThreadPoolExecutorService getInstance() {
        if (instance == null) {
            synchronized (ThreadPoolExecutorService.class) {
                if (instance == null) {
                    instance = new ThreadPoolExecutorService();
                }
            }
        }
        return instance;
    }

    public void execute(Runnable task) {
        executorService.execute(task);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
