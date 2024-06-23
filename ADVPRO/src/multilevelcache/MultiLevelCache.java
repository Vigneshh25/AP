package multilevelcache;

import java.util.*;

public class MultiLevelCache {
    private final List<Cache> cacheLevels;

    public MultiLevelCache(List<Cache> cacheLevels) {
        this.cacheLevels = cacheLevels;
    }

    public String get(String key) {
        int totalReadTime = 0;
        String value = null;
        
        for (int i = 0; i < cacheLevels.size(); i++) {
            Cache cache = cacheLevels.get(i);
            totalReadTime += cache.getReadTime();
            value = cache.get(key);
            if (value != null) {
                writeToHigherLevels(key, value, i);
                break;
            }
        }
        
        System.out.println("Total read time: " + totalReadTime + "ms");
        return value;
    }

    private void writeToHigherLevels(String key, String value, int startIndex) {
        for (int i = startIndex - 1; i >= 0; i--) {
            Cache cache = cacheLevels.get(i);
            cache.put(key, value);
        }
    }

    public void put(String key, String value) {
        int totalWriteTime = 0;
        
        for (Cache cache : cacheLevels) {
            cache.put(key, value);
            totalWriteTime += cache.getWriteTime();
        }
        
        System.out.println("Total write time: " + totalWriteTime + "ms");
    }
}
