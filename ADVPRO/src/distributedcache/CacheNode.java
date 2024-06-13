package distributedcache;

import java.util.HashMap;
import java.util.Map;

public class CacheNode {
    private String id;
    private Map<String, String> dataStore;
    private EvictionPolicy evictionPolicy;

    public CacheNode(String id, EvictionPolicy evictionPolicy) {
        this.id = id;
        this.dataStore = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public String getId() {
        return id;
    }

    public void put(String key, String value) {
        if (dataStore.size() >= evictionPolicy.getMaxSize()) {
            String evictedKey = evictionPolicy.evict(dataStore);
            dataStore.remove(evictedKey);
        }
        dataStore.put(key, value);
        evictionPolicy.recordAccess(key);
    }

    public String get(String key) {
        if (dataStore.containsKey(key)) {
            evictionPolicy.recordAccess(key);
            return dataStore.get(key);
        }
        return null;
    }
}
