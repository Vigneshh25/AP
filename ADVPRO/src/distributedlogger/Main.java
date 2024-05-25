package distributedlogger;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        // Setting up log storage and aggregators
        LogStorage storage = new InMemoryLogStorage();
        LogAggregator aggregator = new LogAggregator(storage);
        List<LogAggregator> aggregators = new ArrayList<>();
        aggregators.add(aggregator);

        // Setting up distributed log strategy
        LogStrategy strategy = new DistributedLogStrategy(aggregators);
        logger.setLogStrategy(strategy);

        LogController controller = new LogController(logger);

        // Example log ingestion
        controller.ingestLog("myService", "This is an info log", LogLevel.INFO, "host1");
        controller.ingestLog("myService", "This is an error log", LogLevel.ERROR, "host2");

        // Example log retrieval
        List<LogEvent> logs = controller.retrieveLogs("myService", LogLevel.INFO, new Date(System.currentTimeMillis() - 100000), new Date());
        logs.forEach(System.out::println);
    }
}
