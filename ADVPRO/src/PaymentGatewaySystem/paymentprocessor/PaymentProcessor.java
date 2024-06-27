package PaymentGatewaySystem.paymentprocessor;

import PaymentGatewaySystem.entity.PaymentDetails;

import java.util.Map;

// Payment Mode Processor Interface
public interface PaymentProcessor {
    boolean process(double amount, PaymentDetails details);
}
