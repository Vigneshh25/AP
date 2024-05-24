package Design_Datastructure.kafka;

import java.util.Random;

public class Producer implements Runnable {
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
