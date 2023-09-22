import java.util.ArrayList;
import java.util.List;

class Event {
    String name;
    int duration;
    boolean isLightning;

    public Event(String name, int duration, boolean isLightning) {
        this.name = name;
        this.duration = duration;
        this.isLightning = isLightning;
    }
}

public class EventScheduler {
    private static final int START_HOUR = 9;
    private static final int LUNCH_HOUR = 12;
    private static final int LUNCH_DURATION = 60;
    private static final int NETWORKING_START_HOUR = 16;
    private static final int NETWORKING_END_HOUR = 17;

    public static void main(String[] args) {
        List<Event> events = new ArrayList<>();
        events.add(new Event("Welcome event", 30, false));
        events.add(new Event("C programming", 45, false));
        events.add(new Event("Working with Java Beans", 30, false));
        events.add(new Event("Ruby on Rails programming", 60, false));
        events.add(new Event("Introduction to Groovy", 60, false));
        events.add(new Event("Rails Debugging", 45, false));
        events.add(new Event("Tips and tricks in C", 30, false));
        events.add(new Event("Back-end development in MySQL", 50, false));
        events.add(new Event("Sit down and Take notes", 5, true));
        events.add(new Event("Clojure Introduction", 45, false));
        events.add(new Event("Team Management Concepts", 30, false));
        events.add(new Event("Introduction to Java Frameworks", 5, true));
        events.add(new Event("Working with Angular JS", 45, false));
        events.add(new Event("Ruby on Rails programming web development concepts", 60, false));
        events.add(new Event("Introduction to Kotlin Java", 60, false));
        events.add(new Event("Debugging and Testing products", 60, false));
        events.add(new Event("Documenting a software", 40, false));
        events.add(new Event("Server side development", 60, false));

        scheduleEvents(events);
    }

    private static void scheduleEvents(List<Event> events) {
        int currentHour = START_HOUR;
        int currentMinute = 0;
        int day = 1;

        for (Event event : events) {
            if (currentHour >= NETWORKING_START_HOUR && currentHour < NETWORKING_END_HOUR) {
                currentHour = NETWORKING_END_HOUR;
                currentMinute = 0;
            }

            if (currentHour >= LUNCH_HOUR && currentHour < LUNCH_HOUR + LUNCH_DURATION) {
                currentHour = LUNCH_HOUR + LUNCH_DURATION;
                currentMinute = 0;
            }

            if (currentHour >= 12) {
                System.out.println("\nSchedule for Day " + day);
                day++;
                currentHour = START_HOUR;
                currentMinute = 0;
            }

            System.out.printf("%02d:%02d ", currentHour, currentMinute);
            System.out.println(event.name + " " + event.duration + " mins");

            if (event.isLightning) {
                currentMinute += 5;
            } else {
                currentMinute += event.duration;
            }

            currentHour += currentMinute / 60;
            currentMinute %= 60;
        }

        if (currentHour >= NETWORKING_START_HOUR && currentHour < NETWORKING_END_HOUR) {
            currentHour = NETWORKING_END_HOUR;
            currentMinute = 0;
        }

        if (currentHour >= LUNCH_HOUR && currentHour < LUNCH_HOUR + LUNCH_DURATION) {
            currentHour = LUNCH_HOUR + LUNCH_DURATION;
            currentMinute = 0;
        }

        System.out.println("\nSchedule for Day " + day);
        System.out.printf("%02d:%02d ", currentHour, currentMinute);
        System.out.println("Networking Hands-on");
    }
}
