package vendingmachine;

public interface VendingMachineObserver {
    void update(String product, int quantity);
}