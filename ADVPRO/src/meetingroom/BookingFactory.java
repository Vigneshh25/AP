package meetingroom;

import java.util.Date;

public class BookingFactory {
    public static Booking createBooking(String bookingId, String roomId, Date startTime, Date endTime, String[] participants) {
        return new Booking(bookingId, roomId, startTime, endTime, participants);
    }
}
