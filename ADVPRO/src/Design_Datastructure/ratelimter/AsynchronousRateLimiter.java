package Design_Datastructure.ratelimter;

import java.util.Map;
import java.util.concurrent.*;

public class AsynchronousRateLimiter {
    private Map<String, RateLimitInfo> rateLimits;
    private ExecutorService executor;

    public AsynchronousRateLimiter(int maxThreads) {
        this.rateLimits = new ConcurrentHashMap<>();
        this.executor = Executors.newFixedThreadPool(maxThreads);
    }

    public void setRateLimit(String clientId, int maxRequests, int windowSeconds) {
        RateLimitInfo limitInfo = new RateLimitInfo(maxRequests, windowSeconds);
        rateLimits.put(clientId, limitInfo);
    }

    public CompletableFuture<Boolean> allowRequestAsync(String clientId) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        
        executor.submit(() -> {
            if (!rateLimits.containsKey(clientId)) {
                // No rate limit set for this client, allow the request
                future.complete(true);
                return;
            }

            RateLimitInfo limitInfo = rateLimits.get(clientId);
            long currentTimestamp = System.currentTimeMillis() / 1000; // Get current time in seconds

            // Remove expired requests from the sliding window
            limitInfo.cleanExpiredRequests(currentTimestamp);

            // Check if the number of requests within the window exceeds the maximum allowed
            if (limitInfo.getRequestCount() >= limitInfo.getMaxRequests()) {
                future.complete(false); // Rate limit exceeded
            } else {
                // Allow the request and update the request count
                limitInfo.incrementRequestCount();
                future.complete(true);
            }
        });

        return future;
    }

    private static class RateLimitInfo {
        private int maxRequests;
        private int windowSeconds;
        private Map<Long, Integer> requestTimestamps; // Timestamp -> Request count

        public RateLimitInfo(int maxRequests, int windowSeconds) {
            this.maxRequests = maxRequests;
            this.windowSeconds = windowSeconds;
            this.requestTimestamps = new ConcurrentHashMap<>();
        }

        public int getMaxRequests() {
            return maxRequests;
        }

        public int getRequestCount() {
            return requestTimestamps.values().stream().mapToInt(Integer::intValue).sum();
        }

        public void cleanExpiredRequests(long currentTimestamp) {
            long oldestValidTimestamp = currentTimestamp - windowSeconds;

            // Remove request counts that are outside of the current sliding window
            requestTimestamps.entrySet().removeIf(entry -> entry.getKey() <= oldestValidTimestamp);
        }

        public void incrementRequestCount() {
            long currentTimestamp = System.currentTimeMillis() / 1000; // Get current time in seconds

            // Increment the request count for the current timestamp
            requestTimestamps.put(currentTimestamp, requestTimestamps.getOrDefault(currentTimestamp, 0) + 1);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AsynchronousRateLimiter limiter = new AsynchronousRateLimiter(10); // Max 10 concurrent threads

        // Set rate limit for client "client123" (allowing 100 requests per minute)
        limiter.setRateLimit("client123", 100, 60);

        // Simulate asynchronous requests
        for (int i = 0; i < 110; i++) {
            String clientId = "client123";
            CompletableFuture<Boolean> requestFuture = limiter.allowRequestAsync(clientId);
            
            requestFuture.thenAccept(allowed -> {
                if (allowed) {
                    System.out.println("Request from " + clientId + " allowed");
                } else {
                    System.out.println("Rate limit exceeded for " + clientId);
                }
            });
        }

        // Shutdown the executor service after processing all requests
        limiter.executor.shutdown();
        limiter.executor.awaitTermination(1, TimeUnit.MINUTES); // Wait for tasks to complete
    }
}
