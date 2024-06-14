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
    public void selectProduct(String productCode, int quantity) {
        Product product = vendingMachine.getProduct(productCode);
        if (product != null) {
            if (product.getQuantity() >= quantity && vendingMachine.getBalance() >= product.getPrice() * quantity) {
                vendingMachine.setState(vendingMachine.getProductSelectedState());
                vendingMachine.selectProduct(productCode,quantity);
                vendingMachine.dispenseProduct();
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
        System.out.println("Select a product and quantity first.");
    }

    @Override
    public void cancelTransactions() {
        int refundedAmount = vendingMachine.cancelTransactions();
        System.out.println("Transaction cancelled. Refunded amount: " + refundedAmount);
        vendingMachine.setState(vendingMachine.getNoCoinInsertedState());
    }
}
