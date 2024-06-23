package multilevelcache;

import java.util.*;

public class LRUCache implements Cache {
    private final int capacity;
    private final int readTime;
    private final int writeTime;
    private final Map<String, String> cache;
    private final LinkedHashMap<String, String> lru;

    public LRUCache(int capacity, int readTime, int writeTime) {
        this.capacity = capacity;
        this.readTime = readTime;
        this.writeTime = writeTime;
        this.cache = new HashMap<>();
        this.lru = new LinkedHashMap<String,String>(capacity, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    @Override
    public String get(String key) {
        if (cache.containsKey(key)) {
            String value = cache.get(key);
            lru.get(key); // Update LRU order
            return value;
        }
        return null;
    }

    @Override
    public void put(String key, String value) {
        if (cache.size() >= capacity) {
            evict();
        }
        cache.put(key, value);
        lru.put(key, value);
    }

    @Override
    public void evict() {
        String eldestKey = lru.entrySet().iterator().next().getKey();
        cache.remove(eldestKey);
        lru.remove(eldestKey);
    }

    @Override
    public int getReadTime() {
        return readTime;
    }

    @Override
    public int getWriteTime() {
        return writeTime;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public String getType() {
        return "LRU";
    }
}
