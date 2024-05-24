package BookMyshow.services;

import BookMyshow.entities.Booking;

public interface PaymentService {
    void processPayment(Booking booking);
    void refundPayment(Booking booking);
}
