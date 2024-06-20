package lld.EventCalendarApp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

// User class
class User {
    private String userId;
    private String username;
    private String email;

    public User(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

// Event class
class Event {
    private String eventId;
    private User creator;
    private List<User> attendees;
    private String title;
    private LocalDateTime dateTime;
    private String location;
    private String description;
    private boolean isRecurring;
    private List<String> categories;

    public Event(String eventId, User creator, String title, LocalDateTime dateTime, String location, String description) {
        this.eventId = eventId;
        this.creator = creator;
        this.attendees = new ArrayList<>();
        this.title = title;
        this.dateTime = dateTime;
        this.location = location;
        this.description = description;
        this.isRecurring = false;
        this.categories = new ArrayList<>();
    }

    // Getters and setters
    public String getEventId() {
        return eventId;
    }

    public User getCreator() {
        return creator;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    // Methods for adding and removing attendees, setting event as recurring, etc.
    public void addAttendee(User attendee) {
        attendees.add(attendee);
    }

    public void removeAttendee(User attendee) {
        attendees.remove(attendee);
    }

    public void setAsRecurring(boolean isRecurring) {
        this.isRecurring = isRecurring;
    }
}

// EventManager class
class EventManager {
    private List<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();
    }

    public void createEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void inviteAttendee(Event event, User attendee) {
        event.addAttendee(attendee);
    }

    // Other event management methods
}

// EventSearchStrategy interface (Strategy)
interface EventSearchStrategy {
    List<Event> searchEvents(List<Event> events, String keyword);
}

// KeywordSearchStrategy class (Strategy)
class KeywordSearchStrategy implements EventSearchStrategy {
    @Override
    public List<Event> searchEvents(List<Event> events, String keyword) {
        List<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                event.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(event);
            }
        }
        return result;
    }
}

// DateSearchStrategy class (Strategy)
class DateSearchStrategy implements EventSearchStrategy {
    @Override
    public List<Event> searchEvents(List<Event> events, String date) {
        List<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getDateTime().toLocalDate().equals(date)) {
                result.add(event);
            }
        }
        return result;
    }
}

// SearchManager class
class SearchManager {
    private EventSearchStrategy searchStrategy;

    public void setSearchStrategy(EventSearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<Event> searchEvents(List<Event> events, String keyword) {
        return searchStrategy.searchEvents(events, keyword);
    }

    public List<Event> searchEvents(List<Event> events, LocalDate date) {
        if (searchStrategy instanceof DateSearchStrategy) {
            return ((DateSearchStrategy) searchStrategy).searchEvents(events, String.valueOf(date));
        }
        return new ArrayList<>();
    }
}

// ReminderObserver interface
interface ReminderObserver {
    void onReminder(Event event);
}

// EmailReminderObserver class
class EmailReminderObserver implements ReminderObserver {
    @Override
    public void onReminder(Event event) {
        System.out.println("Email reminder for event: " + event.getTitle());
    }
}

// PushNotificationReminderObserver class
class PushNotificationReminderObserver implements ReminderObserver {
    @Override
    public void onReminder(Event event) {
        System.out.println("Push notification reminder for event: " + event.getTitle());
    }
}

// ReminderManager class (Singleton)
class ReminderManager {
    private static ReminderManager instance;
    private Map<Event, List<ReminderObserver>> observers;

    private ReminderManager() {
        this.observers = new HashMap<>();
    }

    public static synchronized ReminderManager getInstance() {
        if (instance == null) {
            instance = new ReminderManager();
        }
        return instance;
    }

    public void addReminder(Event event, ReminderObserver observer) {
        observers.computeIfAbsent(event, k -> new ArrayList<>()).add(observer);
    }

    public void removeReminder(Event event, ReminderObserver observer) {
        List<ReminderObserver> eventObservers = observers.get(event);
        if (eventObservers != null) {
            eventObservers.remove(observer);
        }
    }

    public void notifyReminders(Event event) {
        List<ReminderObserver> eventObservers = observers.get(event);
        if (eventObservers != null) {
            for (ReminderObserver observer : eventObservers) {
                observer.onReminder(event);
            }
        }
    }
}

// EventCategoriesDecorator class (Decorator)
class EventCategoriesDecorator extends Event {
    private Event event;

    public EventCategoriesDecorator(Event event) {
        super(event.getEventId(), event.getCreator(), event.getTitle(), event.getDateTime(), event.getLocation(), event.getDescription());
        this.event = event;
    }

    @Override
    public List<String> getCategories() {
        List<String> categories = new ArrayList<>(event.getCategories());
        categories.add("Personal");
        return categories;
    }
}

// EventState interface (State)
interface EventState {
    void handleEvent(Event event);
}

// CreatedState class (State)
class CreatedState implements EventState {
    @Override
    public void handleEvent(Event event) {
        System.out.println("Event is in created state: " + event.getTitle());
    }
}

// OngoingState class (State)
class OngoingState implements EventState {
    @Override
    public void handleEvent(Event event) {
        System.out.println("Event is ongoing: " + event.getTitle());
    }
}

// FinishedState class (State)
class FinishedState implements EventState {
    @Override
    public void handleEvent(Event event) {
        System.out.println("Event is finished: " + event.getTitle());
    }
}

// EventStateContext class
class EventStateContext {
    private EventState currentState;

    public EventStateContext() {
        this.currentState = new CreatedState();
    }

    public void setState(EventState state) {
        this.currentState = state;
    }

    public void handleEvent(Event event) {
        currentState.handleEvent(event);
    }
}

// WebCrawlerWithSameHostnameMain Class
public class EventCalendarApp {
    public static void main(String[] args) {
        // Create users
        User user1 = new User("user1", "john.doe", "john.doe@example.com");
        User user2 = new User("user2", "alice.smith", "alice.smith@example.com");

        // Create event objects
        Event event1 = new Event("event1", user1, "Birthday Party", LocalDateTime.of(2023, 7, 15, 18, 0), "Home", "Join us to celebrate John's birthday!");
        Event event2 = new Event("event2", user2, "Team Meeting", LocalDateTime.of(2023, 7, 20, 14, 30), "Office", "Discuss project updates.");

        // Create event manager
        EventManager eventManager = new EventManager();
        eventManager.createEvent(event1);
        eventManager.createEvent(event2);

        // Create reminder manager
        ReminderManager reminderManager = ReminderManager.getInstance();
        reminderManager.addReminder(event1, new EmailReminderObserver());
        reminderManager.addReminder(event2, new PushNotificationReminderObserver());

        // Create search manager
        SearchManager searchManager = new SearchManager();
        searchManager.setSearchStrategy(new KeywordSearchStrategy());
        List<Event> searchResults = searchManager.searchEvents(eventManager.getEvents(), "birthday");
        for (Event event : searchResults) {
            System.out.println("Found event: " + event.getTitle());
        }

        // Add event categories using decorators
        Event eventWithCategory = new EventCategoriesDecorator(event1);
        System.out.println("Event categories: " + eventWithCategory.getCategories());

        // Set event state and handle actions
        EventStateContext stateContext = new EventStateContext();
        stateContext.setState(new OngoingState());
        stateContext.handleEvent(event1);
    }
}
