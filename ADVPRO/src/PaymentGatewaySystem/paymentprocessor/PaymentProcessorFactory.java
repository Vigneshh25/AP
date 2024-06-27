package PaymentGatewaySystem.paymentprocessor;

import PaymentGatewaySystem.bank.Bank;
import PaymentGatewaySystem.bank.PaymentMode;

import java.util.Map;

// Payment Processor Factory
public class PaymentProcessorFactory {
    private Map<PaymentMode, Bank> bankMap;

    public PaymentProcessorFactory(Map<PaymentMode, Bank> bankMap) {
        this.bankMap = bankMap;
    }

    public PaymentProcessor getProcessor(PaymentMode mode) {
        switch (mode) {
            case UPI:
                return new UpiPaymentProcessor(bankMap.get(PaymentMode.UPI));
            case CREDIT_CARD:
                return new CreditCardPaymentProcessor(bankMap.get(PaymentMode.CREDIT_CARD));
            case NET_BANKING:
                return new NetBankingPaymentProcessor(bankMap.get(PaymentMode.NET_BANKING));
            default:
                throw new IllegalArgumentException("Unsupported payment mode");
        }
    }
}
