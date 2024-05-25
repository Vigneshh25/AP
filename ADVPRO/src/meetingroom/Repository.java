package meetingroom;

import java.util.*;

public class Repository {
    private static Repository instance;
    private List<MeetingRoom> meetingRooms;
    private List<Booking> bookings;

    private Repository() {
        meetingRooms = new ArrayList<>();
        bookings = new ArrayList<>();
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public List<MeetingRoom> getMeetingRooms() {
        return meetingRooms;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
}
