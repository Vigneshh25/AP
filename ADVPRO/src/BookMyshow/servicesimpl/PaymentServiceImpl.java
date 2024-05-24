package BookMyshow.servicesimpl;

import BookMyshow.entities.Booking;
import BookMyshow.entities.Payment;
import BookMyshow.entities.PaymentStatus;
import BookMyshow.gateways.PaymentGateway;
import BookMyshow.repositories.PaymentRepository;
import BookMyshow.services.PaymentService;

import java.util.UUID;

public class PaymentServiceImpl implements PaymentService {
    private PaymentGateway paymentGateway;
    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentGateway paymentGateway, PaymentRepository paymentRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
    }

    @Override
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

    @Override
    public void refundPayment(Booking booking) {
        Payment payment = paymentRepository.findByBookingId(booking.getBookingId());
        if (payment != null && payment.getPaymentStatus() == PaymentStatus.SUCCESS) {
            paymentGateway.refund(payment.getAmount());
            payment.setPaymentStatus(PaymentStatus.REFUNDED);
            paymentRepository.update(payment);
        }
    }
}
