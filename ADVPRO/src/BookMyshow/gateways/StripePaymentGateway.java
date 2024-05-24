package BookMyshow.gateways;

public class StripePaymentGateway implements PaymentGateway {
    @Override
    public boolean charge(double amount) {
        // Implement payment logic
        return true;
    }

    @Override
    public void refund(double amount) {
        // Implement refund logic
    }
}
