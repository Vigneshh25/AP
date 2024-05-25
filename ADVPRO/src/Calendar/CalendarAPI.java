package Calendar;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class CalendarAPI {
    private final Calendar calendar;

    public CalendarAPI() {
        this.calendar = Calendar.getInstance();
    }

    public void createEvent(Event event) {
        calendar.addEvent(event);
    }

    public void updateEvent(String eventId, Event updatedEvent) {
        calendar.updateEvent(eventId, updatedEvent);
    }

    public void deleteEvent(String eventId) {
        calendar.deleteEvent(eventId);
    }

    public void acceptEvent(String eventId, String userId) {
        calendar.setResponse(eventId, userId, ResponseStatus.ACCEPTED);
    }

    public void rejectEvent(String eventId, String userId) {
        calendar.setResponse(eventId, userId, ResponseStatus.REJECTED);
    }

    public List<Event> getCalendar(String userId) {
        return calendar.getUserEvents(userId);
    }

    public Event getEventDetails(String eventId) {
        return calendar.getEventDetails(eventId);
    }

    public List<TimeSlot> findCommonFreeSlot(List<String> userIds, int durationInMinutes) {
        return calendar.findCommonFreeSlots(userIds, durationInMinutes);
    }

    public static void main(String[] args) {
        CalendarAPI api = new CalendarAPI();

        Event event1 = new Event("1", LocalDateTime.of(2023, 6, 1, 10, 0), LocalDateTime.of(2023, 6, 1, 11, 0), "Room 101", "Alice", Arrays.asList("Alice", "Bob"), "Team Meeting");
        Event event2 = new Event("2", LocalDateTime.of(2023, 6, 2, 15, 0), LocalDateTime.of(2023, 6, 2, 16, 0), "Room 102", "Bob", Arrays.asList("Alice", "Bob", "Charlie"), "Project Update");

        api.createEvent(event1);
        api.createEvent(event2);

        System.out.println("Alice's Calendar: " + api.getCalendar("Alice"));
        System.out.println("Bob's Calendar: " + api.getCalendar("Bob"));
        System.out.println("Charlie's Calendar: " + api.getCalendar("Charlie"));

        api.acceptEvent("1", "Bob");
        api.rejectEvent("2", "Charlie");

        System.out.println("Event 1 Details: " + api.getEventDetails("1"));
        System.out.println("Event 2 Details: " + api.getEventDetails("2"));

        List<TimeSlot> commonSlots = api.findCommonFreeSlot(Arrays.asList("Alice", "Bob"), 60);
        System.out.println("Common Free Slots for Alice and Bob: " + commonSlots);
    }
}
