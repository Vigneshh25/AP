package messagequeue;

public class Producer implements Runnable {
    private final EventQueue eventQueue;
    private final String name;

    public Producer(EventQueue eventQueue, String name) {
        this.eventQueue = eventQueue;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Message message = new Message(name + " - Message " + i);
                eventQueue.enqueue(message);
                System.out.println(name + " produced: " + message.getPayload());
                Thread.sleep(100); // Simulate work
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
