package vendingmachine;

public class NoCoinInsertedState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public NoCoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int value) {
        vendingMachine.addBalance(value);
        vendingMachine.setState(vendingMachine.getCoinInsertedState());
    }

    @Override
    public void selectProduct(String productCode, int quantity) {
        System.out.println("Insert coin first.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Insert coin first.");
    }

    @Override
    public void cancelTransactions() {
        System.out.println("No transaction to cancel.");
    }
}



