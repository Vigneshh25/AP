package Design_Datastructure.java8.concurrency.collections;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Replacement for synchronized list. Based on the immutable object concept.
 * Use when reading is far more common than writing.
 * Creates a new copy every time that the list is modified, only synchronizing
 * briefly to ensure array content visibility.
 * iterator returns a snapshot of the current state of the collection.
 * Supports atomic operations.
 */

public class CopyOnWriteArrayListExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        System.out.println("=== CopyOnWriteArrayList ===");
        Random random = new Random();
        // No ConcurrentModificationException
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 100; i++) {
            if (i % 8 == 0) {
                // write
                executor.execute(() -> {
                    Integer value = random.nextInt(10);
                    System.err.println("Added " + value);
                    copyOnWriteArrayList.add(value);
                });
            } else {
                // read
                executor.execute(() -> {
                    StringBuilder sb = new StringBuilder();
                    for (Integer vv : copyOnWriteArrayList) {
                        sb.append(vv).append(" ");
                    }
                    System.out.println("Reading " + sb.toString());
                });
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