package Ecommerce.Strategies;

import Ecommerce.Entities.Payment;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(Payment payment) {
        // Logic for processing credit card payment
        System.out.println("Paid " + payment.getAmount() + " using Credit Card.");
    }
}
