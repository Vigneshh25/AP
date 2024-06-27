package distributedcache;

public class JavaHashFunction implements HashFunction {
    @Override
    public int hash(String key) {
        return key.hashCode();
    }
}