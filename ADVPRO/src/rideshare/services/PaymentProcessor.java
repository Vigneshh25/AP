package rideshare.services;

public class PaymentProcessor {
    public static void processPayment(String userId, double amount) {
        System.out.println("Processed payment of " + amount + " for user: " + userId);
    }

    // Optional: Implement refund method if needed
    public static void processRefund(String userId, double amount) {
        System.out.println("Processed refund of " + amount + " for user: " + userId);
    }
}
