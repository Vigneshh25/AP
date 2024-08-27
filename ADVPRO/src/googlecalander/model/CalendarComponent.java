package googlecalander.model;

import java.util.ArrayList;
import java.util.List;

// Composite Pattern for multiple calendars
public interface CalendarComponent {
    void addEvent(Event event);
    List<Event> getEvents();
}

