package PaymentGatewaySystem;

import java.util.Map;

// Payment Mode Processor Interface
interface PaymentProcessor {
    boolean process(double amount, Map<String, String> details);
}
