package Design_Datastructure;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ElementStore {
    private final int ttlInSeconds;
    private final Map<String, Integer> elementCount;
    private final PriorityQueue<Element> expirationQueue;
    private final ReentrantLock lock;

    // Element class to hold the key and its expiration time
    private class Element {
        String key;
        long expirationTime;

        Element(String key, long expirationTime) {
            this.key = key;
            this.expirationTime = expirationTime;
        }
    }

    public ElementStore(int ttlInSeconds) {
        this.ttlInSeconds = ttlInSeconds;
        this.elementCount = new ConcurrentHashMap<>();
        this.expirationQueue = new PriorityQueue<>(Comparator.comparingLong(e -> e.expirationTime));
        this.lock = new ReentrantLock();
        startCleanupThread();
    }

    public void put(String key) {
        long currentTime = System.currentTimeMillis();
        long expirationTime = currentTime + ttlInSeconds * 1000;

        lock.lock();
        try {
            elementCount.put(key, elementCount.getOrDefault(key, 0) + 1);
            expirationQueue.offer(new Element(key, expirationTime));
        } finally {
            lock.unlock();
        }
    }

    public Map<String, Integer> get() {
        lock.lock();
        try {
            cleanupExpiredElements();
            return elementCount;
        } finally {
            lock.unlock();
        }
    }

    private void cleanupExpiredElements() {
        long currentTime = System.currentTimeMillis();

        while (!expirationQueue.isEmpty() && expirationQueue.peek().expirationTime <= currentTime) {
            Element expiredElement = expirationQueue.poll();
            elementCount.put(expiredElement.key, elementCount.get(expiredElement.key) - 1);
            if (elementCount.get(expiredElement.key) <= 0) {
                elementCount.remove(expiredElement.key);
            }
        }
    }

    private void startCleanupThread() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::cleanupExpiredElements, ttlInSeconds, ttlInSeconds, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        ElementStore store = new ElementStore(2);

        store.put("A");
        store.put("B");
        Thread.sleep(1000);
        store.put("A");
        Thread.sleep(1000);
        store.put("A");

        System.out.println(store.get()); // Expected: {A=2, B=1}
        Thread.sleep(1000);
        System.out.println(store.get()); // Expected: {A=2}
    }
}
