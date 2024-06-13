package distributedcache;

public class DistributedCacheSystem {
    public static void main(String[] args) {
        Coordinator coordinator = Coordinator.getInstance();

        EvictionPolicy lruPolicy = new LRU(100);
        CacheNode node1 = new CacheNode("Node1", lruPolicy);
        CacheNode node2 = new CacheNode("Node2", lruPolicy);

        coordinator.addNode(node1);
        coordinator.addNode(node2);

        CacheNode node = coordinator.getNode("myKey");
        node.put("myKey", "myValue");

        CacheNode retrievalNode = coordinator.getNode("myKey");
        System.out.println(retrievalNode.get("myKey"));  // Outputs: myValue
    }
}
