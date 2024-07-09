//package PaymentGatewaySystem.paymentprocessor;
//
//import PaymentGatewaySystem.bank.Bank;
//import PaymentGatewaySystem.entity.CardDetails;
//
//import java.util.Map;
//
//// UPI Payment Processor
//public class UpiPaymentProcessor implements PaymentProcessor {
//    private Bank bank;
//
//    public UpiPaymentProcessor(Bank bank) {
//        this.bank = bank;
//    }
//
//    @Override
//    public boolean process(double amount, CardDetails details) {
//        String vpa = details.h("vpa");
//        if (vpa != null && !vpa.isEmpty()) {
//            return bank.processPayment(amount);
//        }
//        return false;
//    }
//}
