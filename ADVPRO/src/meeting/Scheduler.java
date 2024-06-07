package meeting;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Scheduler {
    private final List<Room> rooms;
    private final AuditLogManager auditLogManager;
    private final Lock lock = new ReentrantLock();

    public Scheduler(List<Room> rooms, AuditLogManager auditLogManager) {
        this.rooms = rooms;
        this.auditLogManager = auditLogManager;
    }

    public boolean scheduleMeeting(Meeting meeting) {
        lock.lock();
        try {
            Room bestRoom = null;
            for (Room room : rooms) {
                if (room.canAccommodate(meeting) && (bestRoom == null || isBetterRoom(room, bestRoom, meeting))) {
                    bestRoom = room;
                }
            }
            if (bestRoom != null) {
                bestRoom.addMeeting(meeting);
                auditLogManager.addLog(new AuditLog(bestRoom.getRoomId(), meeting.getMeetingId(), LocalDateTime.now(), "ADD", auditLogManager.getRetentionDays()));
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private boolean isBetterRoom(Room room, Room bestRoom, Meeting meeting) {
        // Minimize spillage logic: prefer room with the smallest remaining free time after the meeting
        LocalDateTime meetingEndTime = meeting.getEndTime();
        LocalDateTime bestRoomNextMeetingStartTime = bestRoom.getSchedule().stream()
                .filter(m -> m.getStartTime().isAfter(meetingEndTime))
                .map(Meeting::getStartTime)
                .findFirst()
                .orElse(LocalDateTime.MAX);
        LocalDateTime roomNextMeetingStartTime = room.getSchedule().stream()
                .filter(m -> m.getStartTime().isAfter(meetingEndTime))
                .map(Meeting::getStartTime)
                .findFirst()
                .orElse(LocalDateTime.MAX);
        return roomNextMeetingStartTime.isBefore(bestRoomNextMeetingStartTime);
    }

    public void cancelMeeting(Meeting meeting) {
        lock.lock();
        try {
            for (Room room : rooms) {
                if (room.removeMeeting(meeting)) {
                    auditLogManager.addLog(new AuditLog(room.getRoomId(), meeting.getMeetingId(), LocalDateTime.now(), "REMOVE", auditLogManager.getRetentionDays()));
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
