package Design_Datastructure.ratelimter.combined;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TokenBucket {

    private final int maxTokens;
    private final AtomicInteger tokens;
    private final int refillRate;
    private final long refillInterval;
    private final ScheduledExecutorService scheduler;

    public TokenBucket(int maxTokens, int refillRate, long refillInterval) {
        this.maxTokens = maxTokens;
        this.refillRate = refillRate;
        this.refillInterval = refillInterval;
        this.tokens = new AtomicInteger(maxTokens);
        this.scheduler = Executors.newScheduledThreadPool(1);
        scheduleTokenReplenishment();
    }

    public void scheduleTokenReplenishment() {
        scheduler.scheduleAtFixedRate(() -> {
            tokens.updateAndGet(tokens -> Math.min(maxTokens, tokens + refillRate));
        }, refillInterval, refillInterval, TimeUnit.MILLISECONDS);
    }


    public boolean tryConsume() {
        while (true) {
            int currentTokens = tokens.get();
            if (currentTokens == 0) {
                return false;
            }
            if (tokens.compareAndSet(currentTokens, currentTokens - 1)) {
                return true;
            }
        }
    }

    public void stop() {
        scheduler.shutdown();
    }
}
