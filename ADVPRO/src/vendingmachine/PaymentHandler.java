package vendingmachine;

public interface PaymentHandler {
    boolean processPayment(int amount);
}

class CardPaymentHandler implements PaymentHandler {
    @Override
    public boolean processPayment(int amount) {
        // Implement card payment processing logic
        System.out.println("Processing card payment of " + amount + " cents");
        return true;
    }
}

class UPIPaymentHandler implements PaymentHandler {
    @Override
    public boolean processPayment(int amount) {
        // Implement UPI payment processing logic
        System.out.println("Processing UPI payment of " + amount + " cents");
        return true;
    }
}

class CashPaymentHandler implements PaymentHandler {
    @Override
    public boolean processPayment(int amount) {
        // Implement cash payment processing logic
        System.out.println("Processing cash payment of " + amount + " cents");
        return true;
    }
}
