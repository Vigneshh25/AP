package Problems.kafka.service.routing.strategy;


import Problems.kafka.model.*;

public interface RoutingStrategy {
    MessageMetadata route(Topic topic, Message message);
}
