package meetingroom;

import java.util.Date;

public class Booking {
    private String bookingId;
    private String roomId;
    private Date startTime;
    private Date endTime;
    private String[] participants;

    public Booking(String bookingId, String roomId, Date startTime, Date endTime, String[] participants) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String[] getParticipants() {
        return participants;
    }
}
