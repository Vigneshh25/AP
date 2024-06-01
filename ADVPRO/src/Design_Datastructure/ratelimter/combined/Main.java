package Design_Datastructure.ratelimter.combined;

import java.time.Instant;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimitStrategy slidingRateLimiter = RateLimiterFactory.createRateLimiter("sliding", 2, 1, 1000);
        RateLimitStrategy tokenRateLimiter = RateLimiterFactory.createRateLimiter("token", 5, 5, 2000);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                boolean allowed = slidingRateLimiter.hit("UserA", Instant.now());
                System.out.println("Sliding Window - Request " + finalI + ": " + (allowed ? "Allowed" : "Denied"));

                allowed = tokenRateLimiter.hit("UserB", Instant.now());
                System.out.println("Token Bucket - Request " + finalI + ": " + (allowed ? "Allowed" : "Denied"));
            }).start();
        }
    }
}
