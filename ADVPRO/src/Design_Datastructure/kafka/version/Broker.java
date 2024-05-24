package Design_Datastructure.kafka.version;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Broker {
    private final ConcurrentMap<String, Topic> topics = new ConcurrentHashMap<>();
    private final AtomicInteger nextPartition = new AtomicInteger(0);

    public void createTopic(String topicName, int numberOfPartitions, int queuesPerPartition) {
        topics.putIfAbsent(topicName, new Topic(topicName, numberOfPartitions, queuesPerPartition));
    }

    public void produce(String topicName, Message message) throws InterruptedException {
        Topic topic = topics.get(topicName);
        int partitionId = nextPartition.getAndIncrement() % topic.getPartition(0).getNumberOfQueues();
        topic.getPartition(partitionId).getQueue(partitionId).produce(message);
    }

    public Message consume(String topicName, int partitionId, int queueId) throws InterruptedException {
        Topic topic = topics.get(topicName);
        return topic.getPartition(partitionId).getQueue(queueId).consume();
    }
}
