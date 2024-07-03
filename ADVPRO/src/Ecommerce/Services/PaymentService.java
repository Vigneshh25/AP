package Ecommerce.Services;

import Ecommerce.Entities.Payment;
import Ecommerce.Strategies.PaymentStrategy;

public class PaymentService {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(Payment payment) {
        paymentStrategy.pay(payment);
    }
}
