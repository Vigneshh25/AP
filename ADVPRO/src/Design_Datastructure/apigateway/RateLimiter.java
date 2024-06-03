package Design_Datastructure.apigateway;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private final ConcurrentHashMap<String, Long> requestCounts;
    private final long limit;
    private final long timeFrame;

    public RateLimiter(long limit, long timeFrame, TimeUnit timeUnit) {
        this.requestCounts = new ConcurrentHashMap<>();
        this.limit = limit;
        this.timeFrame = timeUnit.toMillis(timeFrame);
    }

    public boolean isAllowed(String clientId) {
        long currentTime = System.currentTimeMillis();
        requestCounts.merge(clientId, currentTime, (prev, curr) -> 
            (curr - prev < timeFrame) ? prev + 1 : curr);
        return requestCounts.get(clientId) <= limit;
    }
}
