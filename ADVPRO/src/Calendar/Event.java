package Calendar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event {
    private final String eventId;
    private LocalDateTime start;
    private LocalDateTime end;
    private String location;
    private String owner;
    private List<String> userList;
    private String title;
    private Map<String, ResponseStatus> responses;

    public Event(String eventId, LocalDateTime start, LocalDateTime end, String location, String owner, List<String> userList, String title) {
        this.eventId = eventId;
        this.start = start;
        this.end = end;
        this.location = location;
        this.owner = owner;
        this.userList = userList;
        this.title = title;
        this.responses = new HashMap<>();
        for (String user : userList) {
            responses.put(user, ResponseStatus.NEUTRAL);
        }
    }

    public String getEventId() {
        return eventId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, ResponseStatus> getResponses() {
        return responses;
    }

    public void setResponse(String userId, ResponseStatus responseStatus) {
        if (responses.containsKey(userId)) {
            responses.put(userId, responseStatus);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "start=" + start.format(formatter) +
                ", end=" + end.format(formatter);
    }
}

enum ResponseStatus {
    NEUTRAL,
    ACCEPTED,
    REJECTED
}
