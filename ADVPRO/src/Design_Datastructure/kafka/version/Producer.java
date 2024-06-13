package Design_Datastructure.kafka.version;

public class Producer implements Runnable {
    private final Broker broker;
    private final String topicName;
    private final String producerId;

    public Producer(Broker broker, String topicName, String producerId) {
        this.broker = broker;
        this.topicName = topicName;
        this.producerId = producerId;
        broker.createTopic(topicName, 3, 2);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                Message message = new Message(producerId, "Message " + i);
                broker.produce(topicName, message);
                System.out.println(producerId + " produced: " + message.getValue());
                Thread.sleep(100);  // Simulate time taken to produce message
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
