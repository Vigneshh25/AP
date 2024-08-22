package vendingmachine.model;

public interface VendingMachineObserver {
    void update(String product, int quantity);
}