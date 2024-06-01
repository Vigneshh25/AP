package Design_Datastructure.ratelimter.combined;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucket {
    private final int maxTokens;
    private final AtomicInteger availableTokens;
    private final int refillRate;
    private final long refillInterval;
    private final ScheduledExecutorService scheduler;

    public TokenBucket(int maxTokens, int refillRate, long refillInterval) {
        this.maxTokens = maxTokens;
        this.availableTokens = new AtomicInteger(maxTokens);
        this.refillRate = refillRate;
        this.refillInterval = refillInterval;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public boolean tryConsumeToken() {
        int tokens = availableTokens.get();
        if (tokens > 0 && availableTokens.compareAndSet(tokens, tokens - 1)) {
            return true;
        }
        return false;
    }

    public void scheduleTokenReplenishment(Runnable task) {
        scheduler.scheduleAtFixedRate(() -> {
            availableTokens.updateAndGet(tokens -> Math.min(maxTokens, tokens + refillRate));
            task.run();
        }, refillInterval, refillInterval, TimeUnit.MILLISECONDS);
    }
}
