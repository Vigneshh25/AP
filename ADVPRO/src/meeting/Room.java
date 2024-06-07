package meeting;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Room {
    private final String roomId;
    private final int capacity;
    private final PriorityQueue<Meeting> schedule;

    public Room(String roomId, int capacity) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.schedule = new PriorityQueue<>((m1, m2) -> m1.getStartTime().compareTo(m2.getStartTime()));
    }

    public String getRoomId() {
        return roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean canAccommodate(Meeting meeting) {
        if (meeting.getRequiredCapacity() > capacity) {
            return false;
        }

        for (Meeting m : schedule) {
            if (meeting.getStartTime().isBefore(m.getEndTime()) && meeting.getEndTime().isAfter(m.getStartTime())) {
                return false;
            }
        }
        return true;
    }

    public void addMeeting(Meeting meeting) {
        schedule.add(meeting);
    }

    public boolean removeMeeting(Meeting meeting) {
        return schedule.remove(meeting);
    }

    public List<Meeting> getSchedule() {
        return new ArrayList<>(schedule);
    }
}
