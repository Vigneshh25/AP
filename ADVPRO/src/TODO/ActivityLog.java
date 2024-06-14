package TODO;

import java.time.LocalDateTime;

public class ActivityLog {
    private final LocalDateTime timestamp;
    private final String description;

    public ActivityLog(LocalDateTime timestamp, String description) {
        this.timestamp = timestamp;
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ActivityLog{" +
                "timestamp=" + timestamp +
                ", description='" + description + '\'' +
                '}';
    }
}
