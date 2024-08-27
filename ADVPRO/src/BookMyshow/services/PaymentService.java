package BookMyshow.services;

import BookMyshow.entities.Booking;
import BookMyshow.entities.Payment;
import BookMyshow.entities.PaymentStatus;
import BookMyshow.gateways.PaymentGateway;
import BookMyshow.repositories.PaymentRepository;

import java.util.UUID;

public class PaymentService {
    private final PaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentGateway paymentGateway, PaymentRepository paymentRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
    }

    public void processPayment(Booking booking) {
        Payment payment = new Payment(UUID.randomUUID().toString(), booking.getBookingId(), booking.getTotalAmount(), PaymentStatus.PENDING);
        paymentRepository.save(payment);
        if (paymentGateway.charge(booking.getTotalAmount())) {
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
        } else {
            payment.setPaymentStatus(PaymentStatus.FAILED);
        }
        paymentRepository.update(payment);
    }

    public void refundPayment(Booking booking) {
        Payment payment = paymentRepository.findByBookingId(booking.getBookingId());
        if (payment != null && payment.getPaymentStatus() == PaymentStatus.SUCCESS) {
            paymentGateway.refund(payment.getAmount());
            payment.setPaymentStatus(PaymentStatus.REFUNDED);
            paymentRepository.update(payment);
        }
    }
}
