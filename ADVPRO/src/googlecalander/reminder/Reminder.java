package googlecalander.reminder;

import googlecalander.model.Event;

import java.time.LocalDateTime;

public class Reminder {
    private Event event;
    private LocalDateTime remindAt;

    public Reminder(Event event, LocalDateTime remindAt) {
        this.event = event;
        this.remindAt = remindAt;
    }

    public void setReminder() {
        // Implementation to set reminder (e.g., scheduling a task)
        System.out.println("Reminder set for " + event.getTitle() + " at " + remindAt);
    }
}
