package Problems.kafka.worker;


import Problems.kafka.model.*;
import Problems.kafka.service.*;

import java.util.Random;

public class ProducerWorker implements Runnable {
    Topic topic;
    Producer producer;
    TopicService topicService;

    public ProducerWorker(Topic topic, Producer producer, TopicService topicService) {
        this.topic = topic;
        this.producer = producer;
        this.topicService = topicService;
    }

    @Override
    public void run() {
        while (true) {
            Message message = new Message("random message " + new Random().nextInt(100000));
            System.out.println("[Thread - " + Thread.currentThread().getName() + "] Producer + " + producer.toString() + "producing message = "
                    + message + " to topic " + topic.getName());
            MessageMetadata messageMetadata = this.topicService.publishMessage(topic, message);
            System.out.println("Message published to: " + messageMetadata);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
