package Design_Datastructure;

/**
 * Created by Vignesh.V on 08/08/23.
 */
import java.util.LinkedList;

class KeyValuePair<K, V> {
    K key;
    V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class HashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private LinkedList<KeyValuePair<K, V>>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        buckets = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);

        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        for (KeyValuePair<K, V> pair : buckets[index]) {
            if (pair.key.equals(key)) {
                pair.value = value;
                return;
            }
        }

        buckets[index].add(new KeyValuePair<>(key, value));
        size++;
    }

    public V get(K key) {
        int index = getBucketIndex(key);

        if (buckets[index] != null) {
            for (KeyValuePair<K, V> pair : buckets[index]) {
                if (pair.key.equals(key)) {
                    return pair.value;
                }
            }
        }

        return null;
    }

    public void remove(K key) {
        int index = getBucketIndex(key);

        if (buckets[index] != null) {
            for (KeyValuePair<K, V> pair : buckets[index]) {
                if (pair.key.equals(key)) {
                    buckets[index].remove(pair);
                    size--;
                    return;
                }
            }
        }
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println("Size: " + map.size());
        System.out.println("Value for key 'two': " + map.get("two"));

        map.remove("two");
        System.out.println("Size after removal: " + map.size());
    }
}
