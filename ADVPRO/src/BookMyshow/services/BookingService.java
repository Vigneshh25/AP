package BookMyshow.services;


import BookMyshow.entities.Booking;
import BookMyshow.entities.Seat;

import java.util.List;

public interface BookingService {
    Booking createBooking(String showId, String userId, List<Seat> seats);
    boolean cancelBooking(String bookingId);
    Booking getBookingDetails(String bookingId);
}
