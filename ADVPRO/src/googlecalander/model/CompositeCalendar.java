package googlecalander.model;

import java.util.ArrayList;
import java.util.List;

public class CompositeCalendar implements CalendarComponent {
    private List<CalendarComponent> components = new ArrayList<>();

    @Override
    public void addEvent(Event event) {
        for (CalendarComponent component : components) {
            component.addEvent(event);
        }
    }

    public void addComponent(CalendarComponent component) {
        components.add(component);
    }

    @Override
    public List<Event> getEvents() {
        List<Event> allEvents = new ArrayList<>();
        for (CalendarComponent component : components) {
            allEvents.addAll(component.getEvents());
        }
        return allEvents;
    }
}
