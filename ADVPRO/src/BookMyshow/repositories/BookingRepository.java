package BookMyshow.repositories;

import BookMyshow.entities.Booking;

import java.util.HashMap;
import java.util.Map;

public class BookingRepository {
    private final Map<String, Booking> bookings = new HashMap<>();

    public void save(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }

    public Booking findById(String bookingId) {
        return bookings.get(bookingId);
    }

    public void delete(String bookingId) {
        bookings.remove(bookingId);
    }
}
