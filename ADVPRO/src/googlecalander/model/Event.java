package googlecalander.model;

import java.time.LocalDateTime;
import java.util.List;

public class Event {
    private String id;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<User> attendees;

    public Event(String id, String title, LocalDateTime startTime, LocalDateTime endTime, List<User> attendees) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attendees = attendees;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<User> getAttendees() {
        return attendees;
    }
}
