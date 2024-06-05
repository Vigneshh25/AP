package Design_Datastructure;

import java.util.*;
import java.util.HashMap;

public class LFUCache<K, V> {
    private final int capacity;
    private final Map<K, CacheNode<K, V>> cache;
    private final Map<Integer, LinkedHashSet<K>> frequencyMap;
    private int minFrequency;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.frequencyMap = new HashMap<>();
        this.minFrequency = 0;
    }

    public V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }

        CacheNode<K, V> node = cache.get(key);
        updateFrequency(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (capacity == 0) {
            return;
        }

        if (cache.containsKey(key)) {
            CacheNode<K, V> node = cache.get(key);
            node.value = value;
            updateFrequency(node);
        } else {
            if (cache.size() >= capacity) {
                evict();
            }

            CacheNode<K, V> newNode = new CacheNode<>(key, value);
            cache.put(key, newNode);
            addToFrequencyMap(newNode);
            minFrequency = 1;
        }
    }

    private void evict() {
        LinkedHashSet<K> minFreqSet = frequencyMap.get(minFrequency);
        K keyToRemove = minFreqSet.iterator().next();
        minFreqSet.remove(keyToRemove);
        cache.remove(keyToRemove);
    }

    private void updateFrequency(CacheNode<K, V> node) {
        int frequency = node.frequency;
        LinkedHashSet<K> freqSet = frequencyMap.get(frequency);
        freqSet.remove(node.key); // Remove the key from the current frequency set

        if (frequency == minFrequency && freqSet.isEmpty()) {
            minFrequency++; // Increment minFrequency if the current set is empty
        }

        node.frequency++;
        LinkedHashSet<K> newFreqSet = frequencyMap.computeIfAbsent(node.frequency, k -> new LinkedHashSet<>());
        newFreqSet.add(node.key);
    }

    private void addToFrequencyMap(CacheNode<K, V> node) {
        LinkedHashSet<K> freqSet = frequencyMap.computeIfAbsent(1, k -> new LinkedHashSet<>());
        freqSet.add(node.key);
    }

    private static class CacheNode<K, V> {
        K key;
        V value;
        int frequency = 1;

        CacheNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LFUCache<Integer, String> cache = new LFUCache<>(2);
        cache.put(1, "A");
        cache.put(2, "B");
        System.out.println(cache.get(1)); // Output: A
        cache.put(3, "C");
        System.out.println(cache.get(2)); // Output: null
        System.out.println(cache.get(3)); // Output: C
        cache.put(4, "D");
        System.out.println(cache.get(1)); // Output: null
        System.out.println(cache.get(3)); // Output: C
        System.out.println(cache.get(4)); // Output: D
    }
}
