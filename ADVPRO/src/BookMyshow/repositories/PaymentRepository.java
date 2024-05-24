package BookMyshow.repositories;

import BookMyshow.entities.Payment;

public interface PaymentRepository {
    void save(Payment payment);
    void update(Payment payment);
    Payment findByBookingId(String bookingId);
}
