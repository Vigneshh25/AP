package vendingmachine.service;

public class UPIPaymentHandler implements PaymentHandler {
    @Override
    public boolean processPayment(int amount) {
        // Implement UPI payment processing logic
        System.out.println("Processing UPI payment of " + amount + " cents");
        return true;
    }

    @Override
    public String getName() {
        return "UPI";
    }
}
