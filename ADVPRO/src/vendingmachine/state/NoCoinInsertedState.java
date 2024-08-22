package vendingmachine.state;

import vendingmachine.VendingMachine;

public class NoCoinInsertedState implements VendingMachineState {
    private final VendingMachine vendingMachine;
    private final String stateName;


    public NoCoinInsertedState(VendingMachine vendingMachine,String stateName) {
        this.vendingMachine = vendingMachine;
        this.stateName = stateName;
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

    @Override
    public String getState() {
        return stateName;
    }
}



