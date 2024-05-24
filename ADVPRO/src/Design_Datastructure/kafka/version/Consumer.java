package Design_Datastructure.kafka.version;

public class Consumer implements Runnable {
    private final Broker broker;
    private final String topicName;
    private final int partitionId;
    private final int queueId;
    private final String consumerId;

    public Consumer(Broker broker, String topicName, int partitionId, int queueId, String consumerId) {
        this.broker = broker;
        this.topicName = topicName;
        this.partitionId = partitionId;
        this.queueId = queueId;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = broker.consume(topicName, partitionId, queueId);
                System.out.println(consumerId + " consumed: " + message.getValue());
                Thread.sleep(200);  // Simulate time taken to process message
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
