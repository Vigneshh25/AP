package googlecalander.repository;

import googlecalander.model.Event;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEventRepository implements EventRepository {
    private Map<String, Event> events = new HashMap<>();

    @Override
    public void save(Event event) {
        events.put(event.getId(), event);
    }

    @Override
    public void delete(Event event) {
        events.remove(event.getId());
    }

    @Override
    public Event findById(String eventId) {
        return events.get(eventId);
    }
}
