package meeting;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class AuditLog implements Delayed {
    private final String roomId;
    private final String meetingId;
    private final LocalDateTime timestamp;
    private final String action; // e.g., "ADD", "REMOVE"
    private final LocalDateTime expiryTime;

    public AuditLog(String roomId, String meetingId, LocalDateTime timestamp, String action, int retentionDays) {
        this.roomId = roomId;
        this.meetingId = meetingId;
        this.timestamp = timestamp;
        this.action = action;
        this.expiryTime = timestamp.plusDays(retentionDays);
    }

    public String getRoomId() {
        return roomId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(ChronoUnit.NANOS.between(LocalDateTime.now(), expiryTime), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        if (this.expiryTime.isBefore(((AuditLog) other).expiryTime)) {
            return -1;
        }
        if (this.expiryTime.isAfter(((AuditLog) other).expiryTime)) {
            return 1;
        }
        return 0;
    }
}
