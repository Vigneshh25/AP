package BookMyshow.repositories;

import BookMyshow.entities.Payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {
    private final Map<String, Payment> payments = new HashMap<>();

    public void save(Payment payment) {
        payments.put(payment.getPaymentId(), payment);
    }

    public void update(Payment payment) {
        payments.put(payment.getPaymentId(), payment);
    }

    public Payment findByBookingId(String bookingId) {
        return payments.values().stream().filter(payment -> payment.getBookingId().equals(bookingId)).findFirst().orElse(null);
    }
}
