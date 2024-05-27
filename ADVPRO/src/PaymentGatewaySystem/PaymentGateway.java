package PaymentGatewaySystem;

import java.util.*;

// Payment Gateway Class
class PaymentGateway {
    private List<Client> clients;
    private Set<PaymentMode> supportedPaymentModes;
    Router router;
    private PaymentProcessorFactory processorFactory;

    private static PaymentGateway instance;

    private PaymentGateway() {
        this.clients = new ArrayList<>();
        this.supportedPaymentModes = new HashSet<>();
        this.router = new Router();
    }

    public static synchronized PaymentGateway getInstance() {
        if (instance == null) {
            instance = new PaymentGateway();
        }
        return instance;
    }

    public void setProcessorFactory(PaymentProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public boolean hasClient(Client client) {
        return clients.contains(client);
    }

    public void addSupportForPaymode(PaymentMode mode) {
        supportedPaymentModes.add(mode);
    }

    public void addSupportForPaymode(Client client, PaymentMode mode) {
        client.addPaymentMode(mode);
    }

    public void removePaymode(PaymentMode mode) {
        supportedPaymentModes.remove(mode);
    }

    public void removePaymode(Client client, PaymentMode mode) {
        client.removePaymentMode(mode);
    }

    public void showSupportedPaymodes(Client client) {
        System.out.println("Supported payment modes for client " + client.getName() + ": " + client.getSupportedPaymentModes());
    }

    public void showSupportedPaymodes() {
        System.out.println("Supported payment modes: " + supportedPaymentModes);
    }

    public void showDistribution() {
        router.showDistribution();
    }

    public boolean makePayment(Client client, PaymentMode mode, double amount, Map<String, String> details) {
        if (!hasClient(client)) {
            System.out.println("Client not found");
            return false;
        }

        if (!client.getSupportedPaymentModes().contains(mode)) {
            System.out.println("Payment mode not supported by client");
            return false;
        }

        Bank bank = router.getBank(mode);
        PaymentProcessor processor = processorFactory.getProcessor(mode);
        boolean success = processor.process(amount, details);

        if (success) {
            router.recordSuccess(bank);
        }

        return success;
    }
}
