package Problems;

import java.util.LinkedList;
import java.util.Queue;

public class HitCounter {
    private Queue<Integer> hits;

    public HitCounter() {
        hits = new LinkedList<>();
    }

    /**
     * Record a hit.
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        hits.add(timestamp);
        // Clean up old hits outside the 5-minute window
        while (!hits.isEmpty() && hits.peek() <= timestamp - 300) {
            hits.poll();
        }
    }

    /**
     * Return the number of hits in the past 5 minutes.
     * @param timestamp - The current timestamp (in seconds granularity).
     * @return The number of hits in the past 5 minutes.
     */
    public int getHits(int timestamp) {
        // Clean up old hits outside the 5-minute window
        while (!hits.isEmpty() && hits.peek() <= timestamp - 300) {
            hits.poll();
        }
        return hits.size();
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
    }
}
