package meeting;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create rooms
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Room1", 10));
        rooms.add(new Room("Room2", 20));

        // Create AuditLogManager
        AuditLogManager auditLogManager = AuditLogManager.getInstance(7);

        // Create Scheduler
        Scheduler scheduler = new Scheduler(rooms, auditLogManager);

        // Create meetings
        Meeting meeting1 = new Meeting("M1", LocalDateTime.of(2024, 6, 10, 9, 0), LocalDateTime.of(2024, 6, 10, 10, 0), 5);
        Meeting meeting2 = new Meeting("M2", LocalDateTime.of(2024, 6, 10, 9, 0), LocalDateTime.of(2024, 6, 10, 11, 0), 8);
        Meeting meeting3 = new Meeting("M3", LocalDateTime.of(2024, 6, 10, 11, 0), LocalDateTime.of(2024, 6, 10, 12, 0), 5);

        // Schedule meetings
        System.out.println(scheduler.scheduleMeeting(meeting1)); // Should be true
        System.out.println(scheduler.scheduleMeeting(meeting2)); // Should be true
        System.out.println(scheduler.scheduleMeeting(meeting3)); // Should be true

        // Cancel a meeting
        scheduler.cancelMeeting(meeting1);
    }
}
