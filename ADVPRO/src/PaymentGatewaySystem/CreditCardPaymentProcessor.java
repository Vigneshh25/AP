package PaymentGatewaySystem;

import java.util.Map;

// Credit Card Payment Processor
class CreditCardPaymentProcessor implements PaymentProcessor {
    private Bank bank;

    public CreditCardPaymentProcessor(Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean process(double amount, Map<String, String> details) {
        String cardNumber = details.get("cardNumber");
        String expiry = details.get("expiry");
        String cvv = details.get("cvv");
        if (cardNumber != null && expiry != null && cvv != null) {
            return bank.processPayment(amount);
        }
        return false;
    }
}
