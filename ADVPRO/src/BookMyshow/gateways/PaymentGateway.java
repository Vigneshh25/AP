package BookMyshow.gateways;

public interface PaymentGateway {
    boolean charge(double amount);
    void refund(double amount);
}
