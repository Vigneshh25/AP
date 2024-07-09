//package PaymentGatewaySystem.paymentprocessor;
//
//import PaymentGatewaySystem.bank.Bank;
//import PaymentGatewaySystem.entity.NetBankingDetails;
//
//import java.util.Map;
//
//// Net Banking Payment Processor
//public class NetBankingPaymentProcessor implements PaymentProcessor {
//    private Bank bank;
//
//    public NetBankingPaymentProcessor(Bank bank) {
//        this.bank = bank;
//    }
//
//    @Override
//    public boolean process(double amount, NetBankingDetails details) {
//        String username = details.getBankName();
//        String password = details.getPassword();
//        if (username != null && password != null) {
//            return bank.processPayment(amount);
//        }
//        return false;
//    }
//}
