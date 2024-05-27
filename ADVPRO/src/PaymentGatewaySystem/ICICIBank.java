package PaymentGatewaySystem;

import java.util.Random;

class ICICIBank implements Bank {
    @Override
    public boolean processPayment(double amount) {
        return new Random().nextBoolean();
    }
}
