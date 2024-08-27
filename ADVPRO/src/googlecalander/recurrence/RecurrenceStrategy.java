package googlecalander.recurrence;

import googlecalander.model.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Strategy Pattern for recurring events
public interface RecurrenceStrategy {
    List<Event> generateRecurringEvents(Event event);
}

