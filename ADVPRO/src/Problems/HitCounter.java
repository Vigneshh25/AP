package Problems;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import java.util.TreeMap;

public class HitCounter {
    private final TreeMap<Integer, Integer> hitsMap;

    public HitCounter() {
        hitsMap = new TreeMap<>();
    }

    /**
     * Record a hit.
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        hitsMap.put(timestamp, hitsMap.getOrDefault(timestamp, 0) + 1);
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * @param timestamp - The current timestamp (in seconds granularity).
     * @return The number of hits in the past 5 minutes.
     */
    public int getHits(int timestamp) {
        int count = 0;
        // Iterate over the tail map of hits within the last 5 minutes
        for (int ts : hitsMap.tailMap(timestamp - 300).keySet()) {
            count += hitsMap.get(ts);
        }
        return count;
    }

    /**
     * Return the number of hits between two timestamps (inclusive).
     * @param startTime - The starting timestamp (in seconds granularity).
     * @param endTime - The ending timestamp (in seconds granularity).
     * @return The number of hits between startTime and endTime (inclusive).
     */
    public int getHitsInRange(int startTime, int endTime) {
        int count = 0;
        // Iterate over the sub map of hits between startTime and endTime (inclusive)
        for (int ts : hitsMap.subMap(startTime, true, endTime, true).keySet()) {
            count += hitsMap.get(ts);
        }
        return count;
    }

    public static void main(String[] args) {
        HitCounter counter = new HitCounter();

        counter.hit(1);     // hit at timestamp 1
        counter.hit(2);     // hit at timestamp 2
        counter.hit(3);     // hit at timestamp 3
        System.out.println(counter.getHits(4));   // get hits at timestamp 4, should return 3

        counter.hit(300);   // hit at timestamp 300
        System.out.println(counter.getHits(300)); // get hits at timestamp 300, should return 4
        System.out.println(counter.getHits(301)); // get hits at timestamp 301, should return 3
        System.out.println(counter.getHits(305)); // get hits at timestamp 305, should return 1

        // Example of counting hits between two timestamps
        System.out.println(counter.getHitsInRange(1, 3)); // should return 3
        System.out.println(counter.getHitsInRange(299, 301)); // should return 4
    }
}
// Simplified example of Event Counting Service using rolling window counters


class EventCountingService {
    // Rolling window counters (per second)
    private ConcurrentHashMap<Long, AtomicLong> eventCounts;

    public EventCountingService() {
        eventCounts = new ConcurrentHashMap<>();
    }

    public void recordEvent(long timestamp) {
        long second = timestamp / 1000; // Assuming timestamp is in milliseconds
        eventCounts.computeIfAbsent(second, k -> new AtomicLong()).incrementAndGet();
    }

    public long getEventCount(long startTime, long endTime) {
        long startSecond = startTime / 1000;
        long endSecond = endTime / 1000;
        long count = 0;

        for (long second = startSecond; second <= endSecond; second++) {
            AtomicLong counter = eventCounts.get(second);
            if (counter != null) {
                count += counter.get();
            }
        }

        return count;
    }
}

