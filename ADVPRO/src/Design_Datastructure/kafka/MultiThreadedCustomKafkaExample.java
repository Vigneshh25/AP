package Design_Datastructure.kafka;

import java.util.*;
import java.util.concurrent.*;

public class MultiThreadedCustomKafkaExample {
  public static void main(String[] args) {
    Topic topic = new Topic(3);

    // Create multiple producers and consumers
    int numProducers = 2;
    int numConsumers = 3;

    List < Thread > producerThreads = new ArrayList < > ();
    List < Thread > consumerThreads = new ArrayList < > ();

    // Start producer threads
    for (int i = 0; i < numProducers; i++) {
      Producer producer = new Producer(topic);
      Thread producerThread = new Thread(producer);
      producerThreads.add(producerThread);
      producerThread.start();
    }

    // Start consumer threads
    for (int i = 0; i < numConsumers; i++) {
      Consumer consumer = new Consumer(topic, i % topic.getNumPartitions());
      Thread consumerThread = new Thread(consumer);
      consumerThreads.add(consumerThread);
      consumerThread.start();
    }

    // Wait for threads to finish
    try {
      Thread.sleep(10000); // Run for 10 seconds
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Interrupt all threads to stop execution
    for (Thread producerThread: producerThreads) {
      producerThread.interrupt();
    }
    for (Thread consumerThread: consumerThreads) {
      consumerThread.interrupt();
    }
  }
}



