package distributedcache;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Coordinator {
    private static Coordinator instance;
    private final Map<String, CacheNode> nodes;
    private final ConsistentHashing consistentHashing;
    private final Set<CacheNode> failedNodes;

    private Coordinator() {
        nodes = new HashMap<>();
        consistentHashing = new ConsistentHashing();
        failedNodes = new HashSet<>();
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

    public void markNodeAsFailed(CacheNode node) {
        failedNodes.add(node);
        removeNode(node);
        redistributeData(node);
    }

    private void redistributeData(CacheNode node) {
        for (Map.Entry<String, String> entry : node.getDataStore().entrySet()) {
            CacheNode newNode = getNode(entry.getKey());
            newNode.put(entry.getKey(), entry.getValue());
        }
    }

    public boolean isNodeFailed(CacheNode node) {
        return failedNodes.contains(node);
    }
}
