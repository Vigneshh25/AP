package BookMyshow.servicesimpl;

import BookMyshow.entities.Booking;
import BookMyshow.entities.Seat;
import BookMyshow.repositories.BookingRepository;
import BookMyshow.services.BookingService;
import BookMyshow.services.PaymentService;
import BookMyshow.services.ShowService;

import java.util.List;
import java.util.UUID;

public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    private PaymentService paymentService;
    private ShowService showService;

    public BookingServiceImpl(BookingRepository bookingRepository, PaymentService paymentService, ShowService showService) {
        this.bookingRepository = bookingRepository;
        this.paymentService = paymentService;
        this.showService = showService;
    }

    @Override
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

    @Override
    public boolean cancelBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking != null) {
            bookingRepository.delete(bookingId);
            paymentService.refundPayment(booking);
            return true;
        }
        return false;
    }

    @Override
    public Booking getBookingDetails(String bookingId) {
        return bookingRepository.findById(bookingId);
    }

    private double calculateTotalAmount(List<Seat> seats) {
        return seats.stream().mapToDouble(Seat::getPrice).sum();
    }
}