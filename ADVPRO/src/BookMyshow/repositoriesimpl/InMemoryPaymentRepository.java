package BookMyshow.repositoriesimpl;

import BookMyshow.entities.Payment;
import BookMyshow.repositories.PaymentRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPaymentRepository implements PaymentRepository {
    private Map<String, Payment> payments = new HashMap<>();

    @Override
    public void save(Payment payment) {
        payments.put(payment.getPaymentId(), payment);
    }

    @Override
    public void update(Payment payment) {
        payments.put(payment.getPaymentId(), payment);
    }

    @Override
    public Payment findByBookingId(String bookingId) {
        return payments.values().stream()
                .filter(payment -> payment.getBookingId().equals(bookingId))
                .findFirst()
                .orElse(null);
    }
}
