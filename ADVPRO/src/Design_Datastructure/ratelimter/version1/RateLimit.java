package Design_Datastructure.ratelimter.version1;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;

/**
 * Implementation of Sliding window algorithm with timestamp and counter
 * (example: redis hash)
 */
public class RateLimit {
    private final int rateLimit;
    private final ConcurrentMap<String, ConcurrentLinkedDeque<Request>> userRequestMap;

    public RateLimit(int limit) {
        this.rateLimit = limit;
        this.userRequestMap = new ConcurrentHashMap<>();
    }

    /**
     * Thread safe block being invoked by multiple threads
     * @param user username
     * @param timestamp timestamp of request
     * @return request allowed true/false
     */
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
        while (!requests.isEmpty() && Duration.between(requests.peekFirst().getTimestamp(), timestamp).getSeconds() >= 60) {
            requests.pollFirst();
        }
    }
}
