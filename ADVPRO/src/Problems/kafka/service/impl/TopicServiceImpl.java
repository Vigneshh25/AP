package Problems.kafka.service.impl;


import Problems.kafka.model.*;
import Problems.kafka.repository.*;
import Problems.kafka.service.*;
import Problems.kafka.service.routing.strategy.*;
import Problems.kafka.service.routing.strategy.impl.RoundRobinRoutingStrategy;

import java.util.Objects;

public class TopicServiceImpl implements TopicService {
    private TopicRepository topicRepository;
    private RoutingStrategy routingStrategy;

    public TopicServiceImpl() {
        this.topicRepository = new TopicRepository();
        this.routingStrategy = new RoundRobinRoutingStrategy();
    }

    @Override
    public Topic createTopic(String name, int numPartitions) {
        Topic topic = new Topic(name, numPartitions);
        return this.topicRepository.addTopic(topic);
    }

    @Override
    public MessageMetadata publishMessage(Topic topic, Message message) {
        Objects.requireNonNull(topic);
        Objects.requireNonNull(message);

        return this.routingStrategy.route(topic, message);
    }
}
