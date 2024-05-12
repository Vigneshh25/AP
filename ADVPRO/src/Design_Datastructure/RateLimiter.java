import java.util.Map;
import java.util.concurrent.*;

public class RateLimiter {
    private final long maxBucketSize;
    private static long refillRate;
    private ScheduledExecutorService scheduler;
    private final Map<String, TokenBucket> buckets;

    public RateLimiter(long maxBucketSize, long initialRefillRate) {
        this.maxBucketSize = maxBucketSize;
        this.refillRate = initialRefillRate;
        this.buckets = new ConcurrentHashMap<>();
        this.scheduler = Executors.newScheduledThreadPool(1);
        scheduleRefillTask();
    }

    public synchronized void updateRefillRate(long newRefillRate) {
        this.refillRate = newRefillRate;
        rescheduleRefillTask();
    }

    private void rescheduleRefillTask() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown(); // Shutdown existing scheduler
        }
        this.scheduler = Executors.newScheduledThreadPool(1); // Create new scheduler
        scheduleRefillTask(); // Schedule refill task with the updated rate
    }

    public boolean allowRequest(String userId) {
        TokenBucket bucket = buckets.computeIfAbsent(userId, k -> new TokenBucket(maxBucketSize));
        return bucket.tryConsume();
    }

    private void scheduleRefillTask() {
        scheduler.scheduleAtFixedRate(() -> {
            for (TokenBucket bucket : buckets.values()) {
                bucket.refill();
            }
        }, 0, 1, TimeUnit.SECONDS); // Refill tokens every second
    }

    private static class TokenBucket {
        private final long maxBucketSize;
        private long currentBucketSize;
        private final Semaphore semaphore;

        public TokenBucket(long maxBucketSize) {
            this.maxBucketSize = maxBucketSize;
            this.currentBucketSize = maxBucketSize;
            this.semaphore = new Semaphore(1); // Initialize with 1 permit
        }

        public boolean tryConsume() {
            return semaphore.tryAcquire();
        }

        public void refill() {
            long tokensToAdd = (maxBucketSize - currentBucketSize) + refillRate;
            currentBucketSize = Math.min(currentBucketSize + tokensToAdd, maxBucketSize);
            semaphore.release((int) tokensToAdd);
        }
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter(10, 5);

        // Simulate requests
        for (int i = 0; i < 15; i++) {
            String userId = "user123";
            boolean allowed = rateLimiter.allowRequest(userId);
            if (allowed) {
                System.out.println("Request " + (i + 1) + " allowed for user " + userId);
            } else {
                System.out.println("Request " + (i + 1) + " blocked for user " + userId);
            }

            try {
                Thread.sleep(1000); // Pause for 1 second between requests
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Update refill rate dynamically after some time
        rateLimiter.updateRefillRate(10); // Update refill rate to 10 tokens per second
    }
}
