package PaymentGatewaySystem.bank;

import java.util.Random;

// Concrete Bank Implementations
public class HDFCBank implements Bank {
    @Override
    public boolean processPayment(double amount) {
        // Mock processing with random success/failure
        return new Random().nextBoolean();
    }
}
