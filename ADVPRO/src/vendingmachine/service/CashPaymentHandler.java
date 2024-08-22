package vendingmachine.service;

public class CashPaymentHandler implements PaymentHandler {
    @Override
    public boolean processPayment(int amount) {
        // Implement cash payment processing logic
        System.out.println("Processing cash payment of " + amount + " cents");
        return true;
    }

    @Override
    public String getName() {
        return "Cash";
    }
}
