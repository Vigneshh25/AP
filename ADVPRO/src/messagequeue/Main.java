package messagequeue;

public class Main {
    public static void main(String[] args) {
        int queueSize = 5;
        EventQueue eventQueue = new EventQueue(queueSize);

        Thread producer1 = new Thread(new Producer(eventQueue, "Producer1"));
//        Thread producer2 = new Thread(new Producer(eventQueue, "Producer2"));
        Thread consumer1 = new Thread(new Consumer(eventQueue, "Consumer1"));
//        Thread consumer2 = new Thread(new Consumer(eventQueue, "Consumer2"));

        producer1.start();
//        producer2.start();
        consumer1.start();
//        consumer2.start();
    }
}
