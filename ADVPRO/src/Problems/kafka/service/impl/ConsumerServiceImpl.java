package Problems.kafka.service.impl;

import Problems.kafka.model.*;
import Problems.kafka.service.ConsumerGroupService;
import Problems.kafka.service.ConsumerService;

import java.util.Objects;

public class ConsumerServiceImpl implements ConsumerService {
    private ConsumerGroupService consumerGroupService;

    public ConsumerServiceImpl() {
        this.consumerGroupService = new ConsumerGroupServiceImpl();
    }

    @Override
    public Partition subscribe(Topic topic, Consumer consumer) {
        Objects.requireNonNull(topic);
        Objects.requireNonNull(consumer);

        return this.consumerGroupService.registerConsumer(topic, consumer);
    }

    @Override
    public Message consume(Consumer consumer) {
        return this.consumerGroupService.getAMessage(consumer);
    }
}
