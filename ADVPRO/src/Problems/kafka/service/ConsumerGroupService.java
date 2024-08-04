package Problems.kafka.service;


import Problems.kafka.model.*;

public interface ConsumerGroupService {
    Partition registerConsumer(Topic topic, Consumer consumer);
    Message getAMessage(Consumer consumer);
}
