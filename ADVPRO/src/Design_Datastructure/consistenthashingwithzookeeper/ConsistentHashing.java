package Design_Datastructure.consistenthashingwithzookeeper;

import java.util.SortedMap;
import java.util.TreeMap;

class ConsistentHashing {
    private final int numReplicas;
    private final SortedMap<Integer, String> circle = new TreeMap<>();

    public ConsistentHashing(int numReplicas) {
        this.numReplicas = numReplicas;
    }

    private int hash(String key) {
        return key.hashCode() & 0x7fffffff; // Ensure non-negative integer
    }

    public void addInstance(String instance) {
        for (int i = 0; i < numReplicas; i++) {
            circle.put(hash(instance + i), instance);
        }
    }

    public void removeInstance(String instance) {
        for (int i = 0; i < numReplicas; i++) {
            circle.remove(hash(instance + i));
        }
    }

    public String getInstance(String key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = hash(key);
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, String> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

    public SortedMap<Integer, String> getCircle() {
        return circle;
    }
}