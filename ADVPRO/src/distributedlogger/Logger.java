package distributedlogger;

import java.util.Date;

public class Logger {
    private static Logger instance;
    private LogStrategy logStrategy;

    private Logger() {}

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void setLogStrategy(LogStrategy strategy) {
        this.logStrategy = strategy;
    }

    public LogStrategy getLogStrategy() {
        return logStrategy;
    }

    public void log(String message, LogLevel level, String source, String host) {
        LogEvent logEvent = new LogEvent(new Date(), source, level, message, host);
        logStrategy.log(logEvent);
    }
}
