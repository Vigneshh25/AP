package tes.awslambda;

import java.util.concurrent.Semaphore;

// Singleton Pattern
class ConcurrencyController {
    private static volatile ConcurrencyController instance;
    private Semaphore semaphore;

    private ConcurrencyController(int maxConcurrentExecutions) {
        semaphore = new Semaphore(maxConcurrentExecutions);
    }

    public static ConcurrencyController getInstance(int maxConcurrentExecutions) {
        if (instance == null) {
            synchronized (ConcurrencyController.class) {
                if (instance == null) {
                    instance = new ConcurrencyController(maxConcurrentExecutions);
                }
            }
        }
        return instance;
    }

    public void execute(Function function) throws InterruptedException {
        semaphore.acquire();
        try {
            function.execute();
        } finally {
            semaphore.release();
        }
    }
}

// Example usage
public class MainConcurrencyController {
    public static void main(String[] args) throws InterruptedException {
        ConcurrencyController controller = ConcurrencyController.getInstance(3); // Max 3 concurrent executions
        Function function = new SimpleFunction();
        controller.execute(function);
    }
}
