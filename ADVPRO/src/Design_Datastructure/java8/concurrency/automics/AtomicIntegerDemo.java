package Design_Datastructure.java8.concurrency.automics;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    public static void main(String[] args) {

        // Initialize AtomicInteger
        AtomicInteger atomicInt = new AtomicInteger(0);

        // Basic operations
        System.out.println("Initial value: " + atomicInt.get());

        // Increment and get the value
        System.out.println("Increment and get: " + atomicInt.incrementAndGet());

        // Get and increment (returns the old value, then increments)
        System.out.println("Get and increment: " + atomicInt.getAndIncrement());
        System.out.println("Value after getAndIncrement: " + atomicInt.get());

        // Decrement and get the value
        System.out.println("Decrement and get: " + atomicInt.decrementAndGet());

        // Get and decrement (returns the old value, then decrements)
        System.out.println("Get and decrement: " + atomicInt.getAndDecrement());
        System.out.println("Value after getAndDecrement: " + atomicInt.get());

        // Add and get the value
        System.out.println("Add 5 and get: " + atomicInt.addAndGet(5));

        // Get and add (returns the old value, then adds)
        System.out.println("Get and add 3: " + atomicInt.getAndAdd(3));
        System.out.println("Value after getAndAdd: " + atomicInt.get());

        // Set a new value
        atomicInt.set(10);
        System.out.println("Set value to 10: " + atomicInt.get());

        // Compare and set (CAS operation)
        boolean isUpdated = atomicInt.compareAndSet(10, 15);
        System.out.println("Compare and set (10 -> 15): " + isUpdated);
        System.out.println("Value after compareAndSet: " + atomicInt.get());

        // Attempt a failed CAS operation
        isUpdated = atomicInt.compareAndSet(10, 20);
        System.out.println("Attempt to compare and set (10 -> 20): " + isUpdated);
        System.out.println("Value after failed compareAndSet: " + atomicInt.get());

        // Get and update using a lambda expression
        int updatedValue = atomicInt.getAndUpdate(x -> x * 2);
        System.out.println("Get and update (multiply by 2): old value = " + updatedValue + ", new value = " + atomicInt.get());

        // Update and get using a lambda expression
        updatedValue = atomicInt.updateAndGet(x -> x + 5);
        System.out.println("Update and get (add 5): new value = " + updatedValue);

        // Get and accumulate (a custom accumulation operation)
        int accumulatedValue = atomicInt.getAndAccumulate(3, (x, y) -> x + y * 2);
        System.out.println("Get and accumulate (add 3 * 2): old value = " + accumulatedValue + ", new value = " + atomicInt.get());

        // Accumulate and get (another custom accumulation operation)
        accumulatedValue = atomicInt.accumulateAndGet(4, (x, y) -> x + y * 3);
        System.out.println("Accumulate and get (add 4 * 3): new value = " + accumulatedValue);

        // Demonstrating atomic operations in a multi-threaded environment
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                atomicInt.incrementAndGet();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final value after concurrent increments
        System.out.println("Final value after concurrent increments: " + atomicInt.get());
    }
}
