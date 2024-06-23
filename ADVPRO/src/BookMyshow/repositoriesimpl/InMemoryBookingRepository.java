package BookMyshow.repositoriesimpl;

import BookMyshow.entities.Booking;
import BookMyshow.repositories.BookingRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBookingRepository implements BookingRepository {
    private Map<String, Booking> bookings = new HashMap<>();

    @Override
    public void save(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }

    @Override
    public Booking findById(String bookingId) {
        return bookings.get(bookingId);
    }

    @Override
    public void delete(String bookingId) {
        bookings.remove(bookingId);
    }
}
