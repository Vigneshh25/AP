package vendingmachine.service;

public interface PaymentHandler {
    boolean processPayment(int amount);
    String getName();
}

