package Ecommerce.Strategies;

import Ecommerce.Entities.Payment;

public interface PaymentStrategy {
    void pay(Payment payment);
}
