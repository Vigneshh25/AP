package googlecalander.repository;

import googlecalander.model.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.*;

// Repository Pattern for Event storage
public interface EventRepository {
    void save(Event event);
    void delete(Event event);
    Event findById(String eventId);
}

