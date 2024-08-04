package Problems.kafka.service;


import Problems.kafka.model.*;

public interface ConsumerService {
    Partition subscribe(Topic topic, Consumer consumer);
    Message consume(Consumer consumer); // Clarify if you need to fetch in bulk
}
