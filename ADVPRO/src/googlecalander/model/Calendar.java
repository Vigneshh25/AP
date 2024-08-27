package googlecalander.model;

import java.util.ArrayList;
import java.util.List;

public class Calendar implements CalendarComponent {
    private String name;
    private List<Event> events = new ArrayList<>();

    public Calendar(String name) {
        this.name = name;
    }

    @Override
    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public List<Event> getEvents() {
        return events;
    }

    public String getName() {
        return name;
    }
}
