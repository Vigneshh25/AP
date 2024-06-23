package multilevelcache;

public interface Cache {
    String get(String key);
    void put(String key, String value);
    void evict();
    int getReadTime();
    int getWriteTime();
    int getCapacity();
    String getType();
}
