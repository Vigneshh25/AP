package distributedlogger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryLogStorage implements LogStorage {
    private List<LogEvent> logEvents = new ArrayList<>();

    @Override
    public void store(LogEvent event) {
        logEvents.add(event);
    }

    @Override
    public List<LogEvent> retrieveLogs(String source, LogLevel level, Date startTime, Date endTime) {
        return logEvents.stream()
                .filter(event -> (source == null || event.getSource().equals(source)) &&
                        (level == null || event.getLevel() == level) &&
                        (startTime == null || event.getTimestamp().after(startTime)) &&
                        (endTime == null || event.getTimestamp().before(endTime)))
                .collect(Collectors.toList());
    }
}
