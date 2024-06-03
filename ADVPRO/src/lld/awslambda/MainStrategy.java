package lld.awslambda;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

// Strategy Pattern
interface ScalingStrategy {
    void scale(int newThreads);
}

class ThreadPoolScalingStrategy implements ScalingStrategy {
    private ExecutorService executorService;

    public ThreadPoolScalingStrategy(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void scale(int newThreads) {
        ((ThreadPoolExecutor) executorService).setCorePoolSize(newThreads);
    }
}

class ScalableFunctionExecutor {
    private ExecutorService executorService;
    private ScalingStrategy scalingStrategy;

    public ScalableFunctionExecutor(int initialThreads) {
        executorService = Executors.newFixedThreadPool(initialThreads);
        scalingStrategy = new ThreadPoolScalingStrategy(executorService);
    }

    public void execute(Runnable function) {
        executorService.submit(function);
    }

    public void scale(int newThreads) {
        scalingStrategy.scale(newThreads);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}

// Example usage
public class MainStrategy {
    public static void main(String[] args) {
        ScalableFunctionExecutor executor = new ScalableFunctionExecutor(5); // Initial 5 threads
        Runnable function = () -> System.out.println("Executing function");
        executor.execute(function);
        executor.scale(8); // Adjust thread pool size to 8 threads
        executor.shutdown();
    }
}
