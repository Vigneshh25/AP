package Design_Datastructure.kafka;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Topic {
  private final Map< Integer, Partition > partitions;
  private final int numPartitions;

  public Topic(int numPartitions) {
    this.partitions = new ConcurrentHashMap< >();
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
      return Collections.EMPTY_LIST; // Return empty list if partition is invalid
    }
  }

  public int getNumPartitions() {
    return numPartitions;
  }
}