package multilevelcache;

import java.util.*;

public class CacheSystemDemo {
    public static void main(String[] args) {
        Cache l1Cache = new LRUCache(5, 1, 2); // RAM
        Cache l2Cache = new LRUCache(10, 5, 10); // Redis
        Cache l3Cache = new LRUCache(20, 50, 100); // DB

        List<Cache> caches = new ArrayList<>();
        caches.add(l1Cache);
        caches.add(l2Cache);
        caches.add(l3Cache);

        MultiLevelCache multiLevelCache = new MultiLevelCache(caches);

        // Put operations
        multiLevelCache.put("key1", "value1");
        multiLevelCache.put("key2", "value2");
        multiLevelCache.put("key3", "value3");

        // Get operations
        System.out.println("Retrieved: " + multiLevelCache.get("key1"));
        System.out.println("Retrieved: " + multiLevelCache.get("key2"));
        System.out.println("Retrieved: " + multiLevelCache.get("key3"));
        System.out.println("Retrieved: " + multiLevelCache.get("key4")); // This should return null
    }
}
