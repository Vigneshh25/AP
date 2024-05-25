package distributedlogger;

import java.util.Date;
import java.util.List;

public class ElasticsearchLogStorage implements LogStorage {
    @Override
    public void store(LogEvent event) {
        // Implement storing to Elasticsearch here
    }

    @Override
    public List<LogEvent> retrieveLogs(String source, LogLevel level, Date startTime, Date endTime) {
        return null;
    }
}
