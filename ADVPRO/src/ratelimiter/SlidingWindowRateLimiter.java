package ratelimiter;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SlidingWindowRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final Map<String, Queue<Long>> requestTimestamps = new HashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    public SlidingWindowRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    @Override
    public boolean allowRequest(String clientId) {
        lock.lock();
        try {
            long currentTime = System.currentTimeMillis();
            requestTimestamps.putIfAbsent(clientId, new LinkedList<>());

            Queue<Long> timestamps = requestTimestamps.get(clientId);
            while (!timestamps.isEmpty() && currentTime - timestamps.peek() > windowSizeInMillis) {
                timestamps.poll();
            }

            if (timestamps.size() < maxRequests) {
                timestamps.add(currentTime);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
