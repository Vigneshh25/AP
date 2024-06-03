package Design_Datastructure.kafka.version;

public class Main {
    public static void main(String[] args) {
        Broker broker = new Broker();
        broker.createTopic("testTopic", 3, 2);

        Thread producer1 = new Thread(new Producer(broker, "testTopic", "Producer1"));
        Thread producer2 = new Thread(new Producer(broker, "testTopic", "Producer2"));
        Thread consumer1 = new Thread(new Consumer(broker, "testTopic", 2, 0, "Consumer1"));
        Thread consumer2 = new Thread(new Consumer(broker, "testTopic", 2, 1, "Consumer2"));

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        try {
            producer1.join();
            producer2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        consumer1.interrupt();
        consumer2.interrupt();
    }
}
