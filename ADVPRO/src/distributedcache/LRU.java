package distributedcache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRU implements EvictionPolicy {
    private final int maxSize;
    private final LinkedHashMap<String, String> accessOrder;

    public LRU(int maxSize) {
        this.maxSize = maxSize;
        this.accessOrder = new LinkedHashMap<>(maxSize, 0.75f, true);
    }

    @Override
    public void recordAccess(String key) {
        accessOrder.put(key, key);
    }

    @Override
    public String evict(Map<String, String> dataStore) {
        Iterator<String> it = accessOrder.keySet().iterator();
        if (it.hasNext()) {
            String key = it.next();
            it.remove();
            return key;
        }
        return null;
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }
}
