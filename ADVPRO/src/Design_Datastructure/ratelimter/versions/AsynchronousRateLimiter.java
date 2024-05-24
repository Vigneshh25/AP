package Design_Datastructure.ratelimter.versions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynchronousRateLimiter {
    private final TokenBucket tokenBucket;
    private final RateLimiterQueue rateLimiterQueue;
    private final ExecutorService executorService;

    public AsynchronousRateLimiter(int maxTokens, int refillRate, long refillInterval) {
        this.tokenBucket = new TokenBucket(maxTokens, refillRate, refillInterval);
        this.rateLimiterQueue = new RateLimiterQueue();
        this.executorService = Executors.newCachedThreadPool();
    }

    public <T> CompletableFuture<T> submit(RateLimiterTask<T> task) {
        CompletableFuture<T> future = new CompletableFuture<>();
        rateLimiterQueue.addTask(() -> executeTask(task, future));
        tokenBucket.scheduleTokenReplenishment(this::processQueue);
        processQueue();
        return future;
    }

    private <T> void executeTask(RateLimiterTask<T> task, CompletableFuture<T> future) {
        try {
            T result = task.execute();
            future.complete(result);
        } catch (Exception e) {
            future.completeExceptionally(e);
        }
    }

    private void processQueue() {
        executorService.execute(() -> {
            while (rateLimiterQueue.hasPendingTasks() && tokenBucket.tryConsumeToken()) {
                Runnable task = rateLimiterQueue.pollTask();
                if (task != null) {
                    task.run();
                }
            }
        });
    }
}
