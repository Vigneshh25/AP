package ratelimiter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final Map<String, ClientRequestInfo> requestCounts = new HashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    public FixedWindowRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    @Override
    public boolean allowRequest(String clientId) {
        lock.lock();
        try {
            long currentTime = System.currentTimeMillis();
            ClientRequestInfo clientRequestInfo = requestCounts.get(clientId);
            if (clientRequestInfo == null) {
                requestCounts.put(clientId, new ClientRequestInfo(currentTime, 1));
                return true;
            }
            if (currentTime - clientRequestInfo.lastRequestTime >= windowSizeInMillis) {
                clientRequestInfo.lastRequestTime = currentTime;
                clientRequestInfo.requestCount = 1;
                return true;
            }
            if (clientRequestInfo.requestCount < maxRequests) {
                clientRequestInfo.requestCount++;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
