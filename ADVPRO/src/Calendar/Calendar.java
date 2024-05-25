package Calendar;

import java.util.*;

public class Calendar {
    private static Calendar instance;
    private final Map<String, List<Event>> userEvents;

    private Calendar() {
        this.userEvents = new HashMap<>();
    }

    public static synchronized Calendar getInstance() {
        if (instance == null) {
            instance = new Calendar();
        }
        return instance;
    }

    public void addEvent(Event event) {
        for (String userId : event.getUserList()) {
            userEvents.putIfAbsent(userId, new ArrayList<>());
            userEvents.get(userId).add(event);
        }
    }

    public void updateEvent(String eventId, Event updatedEvent) {
        deleteEvent(eventId);
        addEvent(updatedEvent);
    }

    public void deleteEvent(String eventId) {
        for (List<Event> events : userEvents.values()) {
            events.removeIf(event -> event.getEventId().equals(eventId));
        }
    }

    public void setResponse(String eventId, String userId, ResponseStatus responseStatus) {
        for (List<Event> events : userEvents.values()) {
            for (Event event : events) {
                if (event.getEventId().equals(eventId)) {
                    event.setResponse(userId, responseStatus);
                }
            }
        }
    }

    public List<Event> getUserEvents(String userId) {
        return userEvents.getOrDefault(userId, Collections.emptyList());
    }

    public Event getEventDetails(String eventId) {
        for (List<Event> events : userEvents.values()) {
            for (Event event : events) {
                if (event.getEventId().equals(eventId)) {
                    return event;
                }
            }
        }
        return null;
    }

    public List<TimeSlot> findCommonFreeSlots(List<String> userIds, int durationInMinutes) {
        // Implement logic to find common free slots
        // This requires merging the busy time slots of all users and finding gaps of at least 'durationInMinutes'
        return new ArrayList<>();
    }
}
