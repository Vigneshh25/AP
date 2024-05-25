package meetingroom;

import java.util.Date;

public class MeetingRoomFactory {
    public static MeetingRoom createMeetingRoom(String roomId) {
        return new MeetingRoom(roomId);
    }
}

