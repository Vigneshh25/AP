package Design_Datastructure.kafka.version;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Topic {
    private final String name;
    private final Partition partition;
    private final ConcurrentMap<Integer, Partition> partitions = new ConcurrentHashMap<>();

    public Topic(String name, int numberOfPartitions, int queuesPerPartition) {
        this.name = name;
        this.partition = new Partition();
    }

    public Partition getPartition() {
        return partition;
    }

    public String getName() {
        return name;
    }
}
