package BookMyshow.services;

import BookMyshow.entities.Booking;
import BookMyshow.entities.Seat;
import BookMyshow.repositories.BookingRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class BookingService {
    private final BookingRepository bookingRepository;
    private final PaymentService paymentService;
    private final ShowService showService;

    public BookingService(BookingRepository bookingRepository, PaymentService paymentService, ShowService showService) {
        this.bookingRepository = bookingRepository;
        this.paymentService = paymentService;
        this.showService = showService;
    }

    public Booking createBooking(String showId, String userId, List<Seat> seats) {
        if (showService.checkAvailability(showId, seats)) {
            double totalAmount = calculateTotalAmount(seats);
            Booking booking = new Booking(UUID.randomUUID().toString(), showId, userId, seats, totalAmount);
            bookingRepository.save(booking);
            paymentService.processPayment(booking);
            return booking;
        }
        throw new IllegalArgumentException("Seats not available");
    }

    public boolean cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking != null) {
            bookingRepository.delete(bookingId);
            paymentService.refundPayment(booking);
            return true;
        }
        return false;
    }

    public Booking getBookingDetails(String bookingId) {
        return bookingRepository.findById(bookingId);
    }

    public List<Booking> getBookingsByUserId(String userId) {
        return Collections.emptyList();
    }

    public List<Booking> getUserBookings(String userId) {
//        return bookingRepository.findByUserId(userId);
        return Collections.emptyList();
    }

    private double calculateTotalAmount(List<Seat> seats) {
        return seats.stream().mapToDouble(Seat::getPrice).sum();
    }
}