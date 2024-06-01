package Design_Datastructure.ratelimter.combined;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynchronousTokenBucketRateLimit implements RateLimitStrategy {
    private final TokenBucket tokenBucket;
    private final RateLimiterQueue rateLimiterQueue;
    private final ExecutorService executorService;

    public AsynchronousTokenBucketRateLimit(int maxTokens, int refillRate, long refillInterval) {
        this.tokenBucket = new TokenBucket(maxTokens, refillRate, refillInterval);
        this.rateLimiterQueue = new RateLimiterQueue();
        this.executorService = Executors.newCachedThreadPool();
    }

    @Override
    public boolean hit(String user, Instant timestamp) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        rateLimiterQueue.addTask(() -> executeTask(user, future));
        tokenBucket.scheduleTokenReplenishment(this::processQueue);
        processQueue();
        return future.join();
    }

    private void executeTask(String user, CompletableFuture<Boolean> future) {
        try {
            future.complete(tokenBucket.tryConsumeToken());
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
