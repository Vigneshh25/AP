package BookMyshow.repositories;

import BookMyshow.entities.Booking;

public interface BookingRepository {
    void save(Booking booking);
    Booking findById(String bookingId);
    void delete(String bookingId);
}
