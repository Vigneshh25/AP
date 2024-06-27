package PaymentGatewaySystem.paymentprocessor;

import PaymentGatewaySystem.bank.Bank;
import PaymentGatewaySystem.bank.PaymentMode;
import PaymentGatewaySystem.entity.CardDetails;
import PaymentGatewaySystem.entity.PaymentDetails;

import java.util.Map;

// Credit Card Payment Processor
public class CreditCardPaymentProcessor implements PaymentProcessor {
    private Bank bank;

    public CreditCardPaymentProcessor(Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean process(double amount, CardDetails details) {
        String cardNumber =  details.getCardNumber();
        String expiry = details.getExpiry();
        String cvv = details.getCvv();
        if (cardNumber != null && expiry != null && cvv != null) {
            return bank.processPayment(amount);
        }
        return false;
    }
}
