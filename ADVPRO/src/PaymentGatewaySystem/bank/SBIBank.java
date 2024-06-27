package PaymentGatewaySystem.bank;

import java.util.Random;

public class SBIBank implements Bank {
    @Override
    public boolean processPayment(double amount) {
        return new Random().nextBoolean();
    }
}
