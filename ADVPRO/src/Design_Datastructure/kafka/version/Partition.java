package Design_Datastructure.kafka.version;

public class Partition {
    private final MessageQueue queue;

    public Partition() {
        this.queue = new MessageQueue();
    }

    public MessageQueue getQueue() {
        return queue;
    }

}
