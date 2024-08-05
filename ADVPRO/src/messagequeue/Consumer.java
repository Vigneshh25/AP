package messagequeue;

public class Consumer implements Runnable {
    private final EventQueue eventQueue;
    private final String name;

    public Consumer(EventQueue eventQueue, String name) {
        this.eventQueue = eventQueue;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = eventQueue.dequeue();
                System.out.println(name + " consumed: " + message.getPayload());
                Thread.sleep(150); // Simulate work
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
