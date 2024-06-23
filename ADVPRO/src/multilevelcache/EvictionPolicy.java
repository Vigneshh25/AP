package multilevelcache;

public interface EvictionPolicy {
    void evict(Cache cache);
}
