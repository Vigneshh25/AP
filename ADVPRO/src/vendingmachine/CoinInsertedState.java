package vendingmachine;

public class CoinInsertedState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public CoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int value) {
        vendingMachine.addBalance(value);
    }

    @Override
    public void selectProduct(String productCode) {
        Product product = vendingMachine.getProduct(productCode);
        if (product != null) {
            if (product.getQuantity() > 0 && vendingMachine.getBalance() >= product.getPrice()) {
                vendingMachine.setSelectedProduct(product);
                vendingMachine.setState(vendingMachine.getProductSelectedState());
            } else if (product.getQuantity() == 0) {
                System.out.println("Product out of stock.");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Select a product first.");
    }

    @Override
    public void cancelTransactions() {
        int refundedAmount = vendingMachine.cancelTransactions();
        System.out.println("Transaction cancelled. Refunded amount: " + refundedAmount);
        vendingMachine.setState(vendingMachine.getNoCoinInsertedState());
    }
}
