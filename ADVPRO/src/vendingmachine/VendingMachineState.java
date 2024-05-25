package vendingmachine;

public interface VendingMachineState {
    void insertCoin(int value);
    void selectProduct(String productCode);
    void dispenseProduct();
    void cancelTransactions();
}
