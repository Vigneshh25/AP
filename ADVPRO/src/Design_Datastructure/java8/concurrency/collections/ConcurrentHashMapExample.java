package Design_Datastructure.java8.concurrency.collections;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Map for Concurrent access. Sacrifices some aspects of synchronization to
 * achieve better performance.
 * Locks it's values with Lock Striping.
 * Lock Striping divides the protected region through several locks.
 * - Don't throw ConcurrentModificationException
 * - size() and isEmpty() can be incorrect. Don't rely on them.
 * - Supports atomic operations, don't need client side locking.
 * - Readers can access concurrently, and iterator have weak consistency.
 */

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        System.out.println("=== ConcurrentHashMap ===");
        Random random = new Random();
        ConcurrentHashMap<UUID, Integer> valuesPerUuid = new ConcurrentHashMap<>();
        // atomic operations
        valuesPerUuid.putIfAbsent(UUID.randomUUID(), random.nextInt(100));

        // simulating concurrent access - no duplicates should be inserted
        for (int i = 0; i < 100; i++) {
            if (i % 6 == 0) {
                // write
                executor.execute(() -> {
                    UUID uuid = UUID.randomUUID();
                    Integer value = random.nextInt(10);
                    System.out.println("Added " + uuid + " - " + value);
                    valuesPerUuid.putIfAbsent(uuid, value);
                });
            } else {
                // read
                executor.execute(() -> System.out.println("Printed " + valuesPerUuid.values().toString()));
            }
        }
        // Finishing
        executor.shutdown();
        try {
            executor.awaitTermination(2000, TimeUnit.SECONDS);
            // space for other examples
            Thread.sleep(2000);
            System.out.println("\n\n\n\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}