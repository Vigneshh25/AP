package distributedcache;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {
    private SortedMap<Integer, CacheNode> ring = new TreeMap<>();
    private HashFunction hashFunction = new MD5HashFunction();

    public void addNode(CacheNode node) {
        int hash = hashFunction.hash(node.getId());
        ring.put(hash, node);
    }

    public void removeNode(CacheNode node) {
        int hash = hashFunction.hash(node.getId());
        ring.remove(hash);
    }

    public CacheNode getNode(String key) {
        if (ring.isEmpty()) {
            return null;
        }
        int hash = hashFunction.hash(key);
        if (!ring.containsKey(hash)) {
            SortedMap<Integer, CacheNode> tailMap = ring.tailMap(hash);
            hash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        }
        return ring.get(hash);
    }
}
