package ratelimiter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketRateLimiter implements RateLimiter {
    private final int maxTokens;
    private final long refillIntervalMillis;
    private final ReentrantLock lock = new ReentrantLock();
    private final AtomicInteger tokens;
    private final int refillRate;

    public TokenBucketRateLimiter(int maxTokens, int refillRate, long refillIntervalMillis) {
        this.maxTokens = maxTokens;
        this.refillIntervalMillis = refillIntervalMillis;
        this.tokens = new AtomicInteger(maxTokens);
        this.refillRate = refillRate;
        refillTokens();
    }

    private void refillTokens() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> tokens.updateAndGet(token -> Math.min(maxTokens, token + refillRate)), refillIntervalMillis, refillIntervalMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean allowRequest(String clientId) {
        lock.lock();
        try {
            int token = tokens.get();
            return token > 0 && tokens.compareAndSet(token, token - 1);
        } finally {
            lock.unlock();
        }
    }
}
