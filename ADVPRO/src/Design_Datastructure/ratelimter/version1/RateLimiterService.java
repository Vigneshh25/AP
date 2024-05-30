package Design_Datastructure.ratelimter.version1;

public class RateLimiterService {
    public static void main(String[] args) {
        int limit = 5; // 5 requests per minute
        RateLimit rateLimit = new RateLimit(limit);
        new RateLimitHelper("UserA", rateLimit).start();
        new RateLimitHelper("UserB", rateLimit).start();
    }
}
