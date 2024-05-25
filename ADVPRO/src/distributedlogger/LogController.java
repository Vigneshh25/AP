package distributedlogger;

import java.util.Date;
import java.util.List;

public class LogController {
    private Logger logger;

    public LogController(Logger logger) {
        this.logger = logger;
    }

    public void ingestLog(String source, String message, LogLevel level, String host) {
        logger.log(message, level, source, host);
    }

    public List<LogEvent> retrieveLogs(String source, LogLevel level, Date startTime, Date endTime) {
        LogStorage storage = getStorage();
        return storage.retrieveLogs(source, level, startTime, endTime);
    }

    private LogStorage getStorage() {
        // Assuming the logger uses DistributedLogStrategy
        DistributedLogStrategy strategy = (DistributedLogStrategy) logger.getLogStrategy();
        LogAggregator aggregator = strategy.getAggregators().get(0); // For simplicity, use the first aggregator
        return aggregator.getLogStorage();
    }
}
