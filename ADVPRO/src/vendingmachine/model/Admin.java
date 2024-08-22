package vendingmachine.model;

public class Admin implements VendingMachineObserver {
    @Override
    public void update(String product, int quantity) {
        if (quantity == 0) {
            System.out.println("Monitoring Alert: " + product + " is out of stock!");
        }
    }
}