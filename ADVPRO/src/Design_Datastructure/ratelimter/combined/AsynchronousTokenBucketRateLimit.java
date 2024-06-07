package Design_Datastructure.ratelimter.combined;

import java.time.Instant;
import java.util.concurrent.*;

public class AsynchronousTokenBucketRateLimit implements RateLimitStrategy {
    private final TokenBucket tokenBucket;

    public AsynchronousTokenBucketRateLimit(int maxTokens, int refillRate, long timeUnit) {
        this.tokenBucket = new TokenBucket(maxTokens, refillRate, timeUnit);
    }

    public boolean hit(String user, Instant timestamp) {
        return CompletableFuture.supplyAsync(tokenBucket::tryConsume).join();
    }

    public void stop() {
        tokenBucket.stop();
    }
}
