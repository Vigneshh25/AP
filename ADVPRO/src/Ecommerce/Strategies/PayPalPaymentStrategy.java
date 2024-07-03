package Ecommerce.Strategies;

import Ecommerce.Entities.Payment;

public class PayPalPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(Payment payment) {
        // Logic for processing PayPal payment
        System.out.println("Paid " + payment.getAmount() + " using PayPal.");
    }
}
