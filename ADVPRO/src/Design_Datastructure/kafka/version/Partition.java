package Design_Datastructure.kafka.version;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Partition {
    private final List<MessageQueue> queues = new CopyOnWriteArrayList<>();

    public Partition(int numberOfQueues) {
        for (int i = 0; i < numberOfQueues; i++) {
            queues.add(new MessageQueue());
        }
    }

    public MessageQueue getQueue(int index) {
        return queues.get(index);
    }

    public int getNumberOfQueues() {
        return queues.size();
    }
}
