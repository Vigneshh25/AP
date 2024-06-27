package distributedcache;

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {
    private final SortedMap<Integer, CacheNode> ring = new TreeMap<>();
    private final HashFunction hashFunction = new JavaHashFunction();

    public void addNode(CacheNode node) {
        int hash = hashFunction.hash(node.getId());
        ring.put(hash, node);
        System.out.println("Added node " + node.getId() + " with hash " + hash);
    }

    public void removeNode(CacheNode node) {
        int hash = hashFunction.hash(node.getId());
        ring.remove(hash);
        System.out.println("Removed node " + node.getId() + " with hash " + hash);
    }

    public CacheNode getNode(String key) {
        if (ring.isEmpty()) {
            return null;
        }
        int hash = hashFunction.hash(key);
        System.out.println("Key " + key + " has hash " + hash);
        if (!ring.containsKey(hash)) {
            SortedMap<Integer, CacheNode> tailMap = ring.tailMap(hash);
            hash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        }
        return ring.get(hash);
    }
}
