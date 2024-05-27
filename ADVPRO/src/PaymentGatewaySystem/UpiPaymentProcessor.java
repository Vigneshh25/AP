package PaymentGatewaySystem;

import java.util.Map;

// UPI Payment Processor
class UpiPaymentProcessor implements PaymentProcessor {
    private Bank bank;

    public UpiPaymentProcessor(Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean process(double amount, Map<String, String> details) {
        String vpa = details.get("vpa");
        if (vpa != null && !vpa.isEmpty()) {
            return bank.processPayment(amount);
        }
        return false;
    }
}
