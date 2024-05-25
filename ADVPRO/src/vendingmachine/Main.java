package vendingmachine;

public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.addProduct(new Product("A1", "Soda", 150, 10));
        vendingMachine.addProduct(new Product("B1", "Chips", 100, 5));
        vendingMachine.addProduct(new Product("C1", "Candy", 200, 8));

        vendingMachine.insertCoin(100);
        vendingMachine.insertCoin(50);
        vendingMachine.selectProduct("A1");
        vendingMachine.dispenseProduct();

        vendingMachine.insertCoin(100);
        vendingMachine.cancelTransaction();
    }
}
