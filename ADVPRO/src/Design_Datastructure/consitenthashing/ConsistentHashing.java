package Design_Datastructure.consitenthashing;

import distributedcache.HashFunction;

import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing<T> {

    private final HashFunction hashFunction;
    private final int numberOfReplicas;
    private final NavigableMap<Integer, T> circle = new TreeMap<>();

    public ConsistentHashing(HashFunction hashFunction, int numberOfReplicas) {
        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;
    }

    public void addNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            String virtualNodeKey = node.toString() + "#" + i;
            circle.put(hashFunction.hash(virtualNodeKey), node);
        }
    }

    public void removeNode(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            String virtualNodeKey = node.toString() + "#" + i;
            circle.remove(hashFunction.hash(virtualNodeKey));
        }
    }

    public T getNode(String key) {
        if (circle.isEmpty()) {
            return null;
        }

        int hash = hashFunction.hash(key);
        SortedMap<Integer, T> tailMap = circle.tailMap(hash);
        int nodeHash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        return circle.get(nodeHash);
    }

    public void printCircle() {
        for (Integer key : circle.keySet()) {
            System.out.println(key + " => " + circle.get(key));
        }
    }
}
