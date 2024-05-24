package Design_Datastructure.kafka.version;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Topic {
    private final String name;
    private final ConcurrentMap<Integer, Partition> partitions = new ConcurrentHashMap<>();

    public Topic(String name, int numberOfPartitions, int queuesPerPartition) {
        this.name = name;
        for (int i = 0; i < numberOfPartitions; i++) {
            partitions.put(i, new Partition(queuesPerPartition));
        }
    }

    public Partition getPartition(int partitionId) {
        return partitions.get(partitionId);
    }

    public String getName() {
        return name;
    }
}
