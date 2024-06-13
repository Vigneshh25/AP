package distributedcache;

import java.util.*;

public class Coordinator {
    private static Coordinator instance;
    private Map<String, CacheNode> nodes;
    private ConsistentHashing consistentHashing;

    private Coordinator() {
        nodes = new HashMap<>();
        consistentHashing = new ConsistentHashing();
    }

    public static synchronized Coordinator getInstance() {
        if (instance == null) {
            instance = new Coordinator();
        }
        return instance;
    }

    public void addNode(CacheNode node) {
        nodes.put(node.getId(), node);
        consistentHashing.addNode(node);
    }

    public void removeNode(CacheNode node) {
        nodes.remove(node.getId());
        consistentHashing.removeNode(node);
    }

    public CacheNode getNode(String key) {
        return consistentHashing.getNode(key);
    }
}
