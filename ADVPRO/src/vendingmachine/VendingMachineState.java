package vendingmachine;

public interface VendingMachineState {
    void insertCoin(int value);
    void selectProduct(String productCode, int quantity);
    void dispenseProduct();
    void cancelTransactions();
}

