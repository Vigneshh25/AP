package googlecalander.recurrence;

import googlecalander.model.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DailyRecurrenceStrategy implements RecurrenceStrategy {
    @Override
    public List<Event> generateRecurringEvents(Event event) {
        List<Event> events = new ArrayList<>();
        LocalDateTime nextOccurrence = event.getStartTime().plusDays(1);
        for (int i = 0; i < 10; i++) { // Generating 10 daily events for simplicity
            events.add(new Event(event.getId() + "_" + i, event.getTitle(), nextOccurrence, event.getEndTime().plusDays(i), event.getAttendees()));
            nextOccurrence = nextOccurrence.plusDays(1);
        }
        return events;
    }
}
