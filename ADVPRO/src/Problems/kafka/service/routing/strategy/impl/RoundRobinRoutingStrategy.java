package Problems.kafka.service.routing.strategy.impl;


import Problems.kafka.model.*;
import Problems.kafka.service.routing.strategy.RoutingStrategy;

import java.util.Random;

public class RoundRobinRoutingStrategy implements RoutingStrategy {
    @Override
    public MessageMetadata route(Topic topic, Message message) {
        Partition partition = topic.getPartitions().get(new Random().nextInt(topic.getMaxPartitions()));
        int atOffset = partition.addMessage(message);
        return new MessageMetadata(partition, atOffset);
    }
}
