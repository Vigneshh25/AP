package distributedcache;

import java.util.*;

public class LFU implements EvictionPolicy {
    private final int maxSize;
    private final Map<String, Integer> frequencyMap;
    private final PriorityQueue<String> minHeap;

    public LFU(int maxSize) {
        this.maxSize = maxSize;
        this.frequencyMap = new HashMap<>();
        this.minHeap = new PriorityQueue<>((a, b) -> frequencyMap.get(a) - frequencyMap.get(b));
    }

    @Override
    public void recordAccess(String key) {
        frequencyMap.put(key, frequencyMap.getOrDefault(key, 0) + 1);
        minHeap.remove(key);
        minHeap.offer(key);
    }

    @Override
    public String evict(Map<String, String> dataStore) {
        if (!minHeap.isEmpty()) {
            String key = minHeap.poll();
            frequencyMap.remove(key);
            return key;
        }
        return null;
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }
}
