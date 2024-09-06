package meetingroom;

import java.util.*;

public class BookingController {
    private Repository repository = Repository.getInstance();
    private CalendarInvite calendarInvite = new CalendarInvite();

    public String bookMeetingRoom(String roomId, Date startTime, Date endTime, String[] participants) {
        for (Booking booking : repository.getBookings()) {
            if (booking.getRoomId().equals(roomId) && 
                !(endTime.before(booking.getStartTime()) || startTime.after(booking.getEndTime()))) {
                return "Error: Meeting room " + roomId + " is already booked for the given time slot.";
            }
        }

        String bookingId = UUID.randomUUID().toString();
        Booking booking = BookingFactory.createBooking(bookingId, roomId, startTime, endTime, participants);
        repository.addBooking(booking);
        MeetingRoom meetingRoom = getMeetingRoomById(roomId);

        for (String participant : participants) {
            if (meetingRoom != null) {
                meetingRoom.addObserver(new EmailNotification(participant));
                calendarInvite.sendInvite(participant, startTime, endTime);
            }
        }

        return "Booking successful for meeting room " + roomId + " from " + startTime + " to " + endTime;
    }

    public List<MeetingRoom> getAvailableMeetingRooms(Date startTime, Date endTime) {
        List<MeetingRoom> availableRooms = new ArrayList<>(repository.getMeetingRooms());
        for (Booking booking : repository.getBookings()) {
            if (!(endTime.before(booking.getStartTime()) || startTime.after(booking.getEndTime()))) {
                availableRooms.removeIf(room -> room.getRoomId().equals(booking.getRoomId()));
            }
        }
        return availableRooms;
    }

    private MeetingRoom getMeetingRoomById(String roomId) {
        for (MeetingRoom room : repository.getMeetingRooms()) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    // Additional booking methods...
}
