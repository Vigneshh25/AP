package Design_Datastructure.ratelimter.combined;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;

public class SlidingWindowRateLimit implements RateLimitStrategy {
    private final int rateLimit;
    private final ConcurrentMap<String, ConcurrentLinkedDeque<Request>> userRequestMap;

    public SlidingWindowRateLimit(int limit) {
        this.rateLimit = limit;
        this.userRequestMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean hit(String user, Instant timestamp) {
        userRequestMap.putIfAbsent(user, new ConcurrentLinkedDeque<>());
        ConcurrentLinkedDeque<Request> requests = userRequestMap.get(user);

        synchronized (requests) {
            cleanUpOldRequests(requests, timestamp);
            if (requests.size() < rateLimit) {
                requests.add(new Request(timestamp, 1));
                return true;
            } else {
                return false;
            }
        }
    }

    private void cleanUpOldRequests(ConcurrentLinkedDeque<Request> requests, Instant timestamp) {
        while (!requests.isEmpty() && Duration.between(requests.peekFirst().getTimestamp(), timestamp).getSeconds() >= 1) {
            requests.pollFirst();
        }
    }
}
