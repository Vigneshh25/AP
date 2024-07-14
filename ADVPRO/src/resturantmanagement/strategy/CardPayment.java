package resturantmanagement.strategy;

public class CardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Card with additional charges.");
    }
}
