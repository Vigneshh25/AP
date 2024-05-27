package PaymentGatewaySystem;

import java.util.Map;

// Net Banking Payment Processor
class NetBankingPaymentProcessor implements PaymentProcessor {
    private Bank bank;

    public NetBankingPaymentProcessor(Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean process(double amount, Map<String, String> details) {
        String username = details.get("username");
        String password = details.get("password");
        if (username != null && password != null) {
            return bank.processPayment(amount);
        }
        return false;
    }
}
