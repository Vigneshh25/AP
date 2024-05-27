package PaymentGatewaySystem;

import java.util.Random;

// Concrete Bank Implementations
class HDFCBank implements Bank {
    @Override
    public boolean processPayment(double amount) {
        // Mock processing with random success/failure
        return new Random().nextBoolean();
    }
}
