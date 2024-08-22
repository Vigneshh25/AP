package vendingmachine.service;

public class CardPaymentHandler implements PaymentHandler {

    @Override
    public boolean processPayment(int amount) {
        // Implement card payment processing logic
        System.out.println("Processing card payment of " + amount + " cents");
        return true;
    }

    @Override
    public String getName() {
        return "Card";
    }
}
