package Design_Datastructure.java8.concurrent;

class Resource {
    public String resourceName;

    public Resource(String name) {
        this.resourceName = name;
    }
}

public class DeadlockExample {
    public static void main(String[] args) {
        // Creating two shared resources
        final Resource resource1 = new Resource("Resource1");
        final Resource resource2 = new Resource("Resource2");

        // Thread 1 using an anonymous function (Runnable) trying to lock resource1 first, then resource2
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + " locked " + resource1.resourceName);
                try {
                    // Simulate some work with resource1
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Now, trying to lock resource2
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + " locked " + resource2.resourceName);
                }
            }
        }, "Thread 1");

        // Thread 2 using an anonymous function (Runnable) trying to lock resource2 first, then resource1
        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + " locked " + resource2.resourceName);
                try {
                    // Simulate some work with resource2
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Now, trying to lock resource1
                synchronized (resource1) {
                    System.out.println(Thread.currentThread().getName() + " locked " + resource1.resourceName);
                }
            }
        }, "Thread 2");

        // Start both threads
        thread1.start();
        thread2.start();
    }
}
