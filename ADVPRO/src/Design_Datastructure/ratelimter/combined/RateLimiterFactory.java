package Design_Datastructure.ratelimter.combined;

public class RateLimiterFactory {
    public static RateLimitStrategy createRateLimiter(String type, int limit, int refillRate, long refillInterval) {
        switch (type) {
            case "sliding":
                return new SlidingWindowRateLimit(limit);
            case "token":
                return new AsynchronousTokenBucketRateLimit(limit, refillRate, refillInterval);
            default:
                throw new IllegalArgumentException("Invalid rate limiter type");
        }
    }
}
