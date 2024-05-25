package vendingmachine;

public class ProductSelectedState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public ProductSelectedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int value) {
        System.out.println("Product already selected.");
    }

    @Override
    public void selectProduct(String productCode) {
        System.out.println("Product already selected.");
    }

    @Override
    public void dispenseProduct() {
        Product product = vendingMachine.getSelectedProduct();
        vendingMachine.dispenseProduct(product);
        vendingMachine.setState(vendingMachine.getNoCoinInsertedState());
    }

    @Override
    public void cancelTransactions() {
        int refundedAmount = vendingMachine.cancelTransactions();
        System.out.println("Transaction cancelled. Refunded amount: " + refundedAmount);
        vendingMachine.setState(vendingMachine.getNoCoinInsertedState());
    }
}
