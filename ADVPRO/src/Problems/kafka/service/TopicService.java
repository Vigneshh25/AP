package Problems.kafka.service;


import Problems.kafka.model.*;

public interface TopicService {
    Topic createTopic(String name, int numPartitions);
    MessageMetadata publishMessage(Topic topic, Message message);
}
