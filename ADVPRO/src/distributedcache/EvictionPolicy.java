package distributedcache;
import java.util.*;

public interface EvictionPolicy {
    void recordAccess(String key);
    String evict(Map<String, String> dataStore);
    int getMaxSize();
}

