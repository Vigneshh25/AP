package distributedlogger;

import java.util.*;

public interface LogStorage {
    void store(LogEvent event);
    List<LogEvent> retrieveLogs(String source, LogLevel level, Date startTime, Date endTime);
}
