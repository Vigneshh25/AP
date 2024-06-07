package meeting;

import java.time.LocalDateTime;

public class Meeting {
    private final String meetingId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final int requiredCapacity;

    public Meeting(String meetingId, LocalDateTime startTime, LocalDateTime endTime, int requiredCapacity) {
        this.meetingId = meetingId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requiredCapacity = requiredCapacity;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getRequiredCapacity() {
        return requiredCapacity;
    }
}
