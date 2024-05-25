package distributedlogger;

public class LogAggregator {
    private LogStorage logStorage;

    public LogAggregator(LogStorage logStorage) {
        this.logStorage = logStorage;
    }

    public void aggregate(LogEvent event) {
        logStorage.store(event);
    }

    public LogStorage getLogStorage() {
        return logStorage;
    }
}
