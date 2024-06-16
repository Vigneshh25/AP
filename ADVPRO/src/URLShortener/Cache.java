package URLShortener;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public interface Cache {
    String get(String key);
    void put(String key, String value);
}

class CacheImpl implements Cache {
    private final Map<String, String> cache = new ConcurrentHashMap<>();
    private static CacheImpl instance;

    private CacheImpl() {}

    public static CacheImpl getInstance() {
        if (instance == null) {
            instance = new CacheImpl();
        }
        return instance;
    }

    @Override
    public String get(String key) {
        return cache.get(key);
    }

    @Override
    public void put(String key, String value) {
        cache.put(key, value);
    }
}
