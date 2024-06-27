package distributedcache;

public class DistributedCacheSystem {
    public static void main(String[] args) {
        Coordinator coordinator = Coordinator.getInstance();

        // Using LRU policy
        EvictionPolicy lruPolicy = new LRU(3);
        CacheNode node1 = new CacheNode("Node1", lruPolicy);
        CacheNode node2 = new CacheNode("Node2", lruPolicy);

        coordinator.addNode(node1);
        coordinator.addNode(node2);

        CacheNode node = coordinator.getNode("myKey");
        node.put("myKey", "myValue");
        System.out.println(node.get("myKey"));  // Outputs: myValue

        // Testing LFU policy
        EvictionPolicy lfuPolicy = new LFU(3);
        CacheNode node3 = new CacheNode("Node3", lfuPolicy);
        coordinator.addNode(node3);

        node3.put("testKey1", "testValue1");
        node3.put("testKey2", "testValue2");
        node3.get("testKey1");
        node3.get("testKey1");
        node3.put("testKey3", "testValue3"); // Assuming maxSize is reached and an eviction happens
        node3.put("testKey4", "testValue4"); // Assuming another eviction
        System.out.println(node3.get("testKey1"));  // Should be "testValue1" as it was accessed more frequently
        System.out.println(node3.get("testKey2"));  // Should be null as it was least frequently used and evicted
        System.out.println(node3.get("testKey3"));  // Should be "testValue3"
        System.out.println(node3.get("testKey4"));  // Should be "testValue4"

        // Testing node failure handling
        CacheNode node4 = new CacheNode("Node4", lruPolicy);
        coordinator.addNode(node4);

        node4.put("failureKey", "failureValue");
        System.out.println(node4.get("failureKey"));  // Outputs: failureValue

        coordinator.markNodeAsFailed(node4);
        CacheNode newNodeForKey = coordinator.getNode("failureKey");
        System.out.println(newNodeForKey.get("failureKey"));  // Should redistribute and output: failureValue
    }
}
