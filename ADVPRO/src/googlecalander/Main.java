package googlecalander;


import googlecalander.api.*;
import googlecalander.model.*;
import googlecalander.model.User;
import googlecalander.notification.EmailNotification;
import googlecalander.notification.Notification;
import googlecalander.notification.SMSNotification;
import googlecalander.reminder.Reminder;
import googlecalander.repository.*;
import googlecalander.reminder.Command;
import googlecalander.reminder.SetReminderCommand;
import googlecalander.util.TimeZoneAdapter;
import googlecalander.recurrence.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {
        // User authentication
        UserService userService = UserService.getInstance();
        User user = userService.register("john_doe", "password123");

        // Event management
        EventRepository eventRepository = new InMemoryEventRepository();
        Event event = new Event("1", "Meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1), new ArrayList<>());
        eventRepository.save(event);

        // Recurring events
        RecurrenceStrategy recurrenceStrategy = new DailyRecurrenceStrategy();
        List<Event> recurringEvents = recurrenceStrategy.generateRecurringEvents(event);
        for (Event e : recurringEvents) {
            eventRepository.save(e);
        }

        // Invitations
        InvitationService invitationService = new InvitationService();
        invitationService.invite(user, event);

        // Reminders
        Reminder reminder = new Reminder(event, LocalDateTime.now().plusMinutes(30));
        Command reminderCommand = new SetReminderCommand(reminder);
        reminderCommand.execute();

        // Multiple Calendars
        Calendar calendar = new Calendar("Work Calendar");
        calendar.addEvent(event);

        CalendarComponent compositeCalendar = new CompositeCalendar();
        compositeCalendar.addEvent(event);

        // Sharing and Permissions
        ShareableCalendar sharedCalendar = new SharedCalendar(calendar);
        sharedCalendar.share(user, Permission.FULL_ACCESS);

        // Time Zone Support
        TimeZoneAdapter timeZoneAdapter = new TimeZoneAdapter(ZoneId.of("America/New_York"));
        ZonedDateTime zonedDateTime = timeZoneAdapter.convertToTimeZone(LocalDateTime.now());
        System.out.println("Converted Time: " + zonedDateTime);

        // Notifications
        Notification emailNotification = new EmailNotification("user@example.com");
        emailNotification.send("Event Reminder");

        Notification smsNotification = new SMSNotification("1234567890");
        smsNotification.send("Event Reminder");
    }
}

