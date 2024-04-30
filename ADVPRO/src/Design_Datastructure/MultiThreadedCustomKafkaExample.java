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

class Partition {
  private final List < Message > messages;

  public Partition() {
    this.messages = Collections.synchronizedList(new LinkedList < > ());
  }

  public void addMessage(Message message) {
    synchronized(messages) {
      messages.add(message);
    }
  }

  public List < Message > getMessages() {
    synchronized(messages) {
      return new LinkedList < > (messages);
    }
  }
}

class Topic {
  private final Map < Integer, Partition > partitions;
  private final int numPartitions;

  public Topic(int numPartitions) {
    this.partitions = new ConcurrentHashMap < > ();
    this.numPartitions = numPartitions;
    for (int i = 0; i < numPartitions; i++) {
      partitions.put(i, new Partition());
    }
  }

  public void publishMessage(int partitionId, Message message) {
    Partition partition = partitions.get(partitionId);
    if (partition != null) {
      partition.addMessage(message);
    } else {
      System.out.println("Invalid partitionId: " + partitionId);
    }
  }

  public List < Message > consumeMessages(int partitionId) {
    Partition partition = partitions.get(partitionId);
    if (partition != null) {
      return partition.getMessages();
    } else {
      System.out.println("Invalid partitionId: " + partitionId);
      return List.of(); // Return empty list if partition is invalid
    }
  }

  public int getNumPartitions() {
    return numPartitions;
  }
}

class Producer implements Runnable {
  private final Topic topic;
  private final Random random;

  public Producer(Topic topic) {
    this.topic = topic;
    this.random = new Random();
  }

  @Override
  public void run() {
    try {
      while (!Thread.currentThread().isInterrupted()) {
        String content = "Message-" + random.nextInt(100);
        int partitionId = random.nextInt(topic.getNumPartitions());
        topic.publishMessage(partitionId, new Message(content));
        Thread.sleep(random.nextInt(1000)); // Simulate variable production time
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}

class Consumer implements Runnable {
  private final Topic topic;
  private final int partitionId;

  public Consumer(Topic topic, int partitionId) {
    this.topic = topic;
    this.partitionId = partitionId;
  }

  @Override
  public void run() {
    try {
      while (!Thread.currentThread().isInterrupted()) {
        List < Message > messages = topic.consumeMessages(partitionId);
        processMessages(messages);
        Thread.sleep(1000); // Simulate message processing time
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private void processMessages(List < Message > messages) {
    for (Message message: messages) {
      System.out.println("Consumer received message: " + message.getContent());
    }
  }
}

class Message {
  private final String content;

  public Message(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }
}
