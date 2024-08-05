package ratelimiter;

public class Main {
    public static void main(String[] args) {
        RateLimiter fixedWindowRateLimiter = RateLimiterFactory.createRateLimiter("fixed", 10, 60000);
        RateLimiter slidingWindowRateLimiter = RateLimiterFactory.createRateLimiter("sliding", 10, 60000);
        RateLimiter tokenBucketRateLimiter = RateLimiterFactory.createRateLimiter("token", 3, 2000); // 10 tokens, refill every 1 second

        // Testing Fixed Window Rate Limiter
        System.out.println("Fixed Window Rate Limiter:");
        for (int i = 0; i < 12; i++) {
            System.out.println(fixedWindowRateLimiter.allowRequest("client1"));
        }

        // Testing Sliding Window Rate Limiter
        System.out.println("Sliding Window Rate Limiter:");
        for (int i = 0; i < 12; i++) {
            System.out.println(slidingWindowRateLimiter.allowRequest("client2"));
        }

        // Testing Token Bucket Rate Limiter
        System.out.println("Token Bucket Rate Limiter:");
        for (int i = 0; i < 15; i++) {
            System.out.println(tokenBucketRateLimiter.allowRequest("client3"));
            try {
                Thread.sleep(200); // Sleep for 200 milliseconds between requests
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
