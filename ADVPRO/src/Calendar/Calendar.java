package Calendar;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Calendar {
    private static Calendar instance;
    private final Map<String, List<Event>> userEvents;
    Scanner scanner = new Scanner(System.in);


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
        List<Event> allBusyEvents = new ArrayList<>();
        for (String userId : userIds) {
            List<Event> events = userEvents.get(userId);
            if (events != null) {
                allBusyEvents.addAll(events);
            }
        }

        List<TimeSlot> allBusySlots = new ArrayList<>();
        for (Event event : allBusyEvents) {
            allBusySlots.add(new TimeSlot(event.getStart(), event.getEnd()));
        }

        allBusySlots.sort(Comparator.comparing(TimeSlot::getStart));

        List<TimeSlot> mergedBusySlots = mergeBusySlots(allBusySlots);

        return findFreeSlots(mergedBusySlots, durationInMinutes);
    }

    private List<TimeSlot> mergeBusySlots(List<TimeSlot> busySlots) {
        List<TimeSlot> merged = new ArrayList<>();
        if (busySlots.isEmpty()) {
            return merged;
        }

        TimeSlot current = busySlots.get(0);
        for (int i = 1; i < busySlots.size(); i++) {
            TimeSlot next = busySlots.get(i);
            if (!current.getEnd().isBefore(next.getStart())) {
                current = new TimeSlot(current.getStart(), current.getEnd().isAfter(next.getEnd()) ? current.getEnd() : next.getEnd());
            } else {
                merged.add(current);
                current = next;
            }
        }
        merged.add(current);

        return merged;
    }

    private List<TimeSlot> findFreeSlots(List<TimeSlot> busySlots, int durationInMinutes) {
        List<TimeSlot> freeSlots = new ArrayList<>();
        System.out.println("Enter Day Start Time in YYYY-MM-DD HH:mm");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startDateInput = scanner.nextLine();
        LocalDateTime dayStart = LocalDateTime.parse(startDateInput, dateTimeFormatter);
        System.out.println("Enter Day End Time in YYYY-MM-DD HH:mm");
        String endDateInput = scanner.nextLine();
        LocalDateTime dayEnd = LocalDateTime.parse(endDateInput, dateTimeFormatter);

        LocalDateTime previousEnd = dayStart;
        for (TimeSlot busySlot : busySlots) {
            if (Duration.between(previousEnd, busySlot.getStart()).toMinutes() >= durationInMinutes) {
                freeSlots.add(new TimeSlot(previousEnd, busySlot.getStart()));
            }
            previousEnd = busySlot.getEnd().isAfter(previousEnd) ? busySlot.getEnd() : previousEnd;
        }

        if (Duration.between(previousEnd, dayEnd).toMinutes() >= durationInMinutes) {
            freeSlots.add(new TimeSlot(previousEnd, dayEnd));
        }

        return freeSlots;
    }
}
