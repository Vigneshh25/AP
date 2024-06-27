package PaymentGatewaySystem;

import PaymentGatewaySystem.bank.*;
import PaymentGatewaySystem.entity.CardDetails;
import PaymentGatewaySystem.entity.Client;
import PaymentGatewaySystem.paymentprocessor.PaymentProcessorFactory;

import java.util.*;

// WebCrawlerWithSameHostnameMain Class for Testing
public class PaymentGatewaySystem {
    public static void main(String[] args) {
        PaymentGateway pg = PaymentGateway.getInstance();

        // Banks
        Bank hdfc = new HDFCBank();
        Bank icici = new ICICIBank();
        Bank sbi = new SBIBank();

        // Router Configuration
        pg.router.addBank(PaymentMode.CREDIT_CARD, hdfc);
        pg.router.addBank(PaymentMode.CREDIT_CARD, icici);
        pg.router.addBank(PaymentMode.NET_BANKING, icici);
        pg.router.addBank(PaymentMode.NET_BANKING, sbi);
        pg.router.addBank(PaymentMode.UPI, sbi);

        // Processor Factory
        Map<PaymentMode, Bank> bankMap = new HashMap<>();
        bankMap.put(PaymentMode.CREDIT_CARD, hdfc); // Directing all credit card transactions to HDFC for simplicity
        bankMap.put(PaymentMode.NET_BANKING, icici);
        bankMap.put(PaymentMode.UPI, sbi);

        PaymentProcessorFactory processorFactory = new PaymentProcessorFactory(bankMap);
        pg.setProcessorFactory(processorFactory);

        // Adding Clients
        Client flipkart = new Client("Flipkart");
        pg.addClient(flipkart);
        pg.addSupportForPaymode(flipkart, PaymentMode.CREDIT_CARD);
        pg.addSupportForPaymode(flipkart, PaymentMode.UPI);

        // Making a Payment
        CardDetails cardDetails = new CardDetails("1234567890123456","12/25","123");

        boolean paymentStatus = pg.makePayment(flipkart, PaymentMode.CREDIT_CARD, 1000.00, cardDetails);
        System.out.println("Payment status: " + (paymentStatus ? "Success" : "Failure"));

        // Showing Distribution
        pg.showDistribution();
    }
}
