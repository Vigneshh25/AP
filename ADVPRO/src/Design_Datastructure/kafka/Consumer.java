package Design_Datastructure.kafka;

import java.util.List;

public class Consumer implements Runnable {
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
