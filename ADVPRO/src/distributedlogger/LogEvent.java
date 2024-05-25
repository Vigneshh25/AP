package distributedlogger;

import java.util.Date;

public class LogEvent {
    private Date timestamp;
    private String source;
    private LogLevel level;
    private String message;
    private String host;

    public LogEvent(Date timestamp, String source, LogLevel level, String message, String host) {
        this.timestamp = timestamp;
        this.source = source;
        this.level = level;
        this.message = message;
        this.host = host;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s: %s", timestamp, level, source, message);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getSource() {
        return source;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    public String getHost() {
        return host;
    }
}
