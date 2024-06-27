package PaymentGatewaySystem.entity;

import PaymentGatewaySystem.bank.PaymentMode;

import java.util.HashSet;
import java.util.Set;

// Client Class
public class Client {
    private String name;
    private Set<PaymentMode> supportedPaymentModes;

    public Client(String name) {
        this.name = name;
        this.supportedPaymentModes = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<PaymentMode> getSupportedPaymentModes() {
        return supportedPaymentModes;
    }

    public void addPaymentMode(PaymentMode mode) {
        supportedPaymentModes.add(mode);
    }

    public void removePaymentMode(PaymentMode mode) {
        supportedPaymentModes.remove(mode);
    }
}
