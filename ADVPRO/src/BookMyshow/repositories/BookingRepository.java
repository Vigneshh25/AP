package BookMyshow.repositories;

import BookMyshow.entities.Booking;

import java.util.List;

public interface BookingRepository {
    void save(Booking booking);
    Booking findById(String bookingId);
    void delete(String bookingId);
}
