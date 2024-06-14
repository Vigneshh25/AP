package vendingmachine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize vending machine and product inventory
        VendingMachine vendingMachine = VendingMachine.getInstance();
        ProductInventory productInventory = ProductInventory.getInstance();
        vendingMachine.setProductInventory(productInventory);

        // Adding initial products to inventory
        Product a1 = ProductFactory.createProduct("A1", ProductType.CHIPS, 150, 3);
        Product b1 = ProductFactory.createProduct("B1", ProductType.NACHOS, 150, 10);
        Product c1 = ProductFactory.createProduct("C1", ProductType.COOKIES, 150, 10);

        productInventory.addProduct(a1);
        productInventory.addProduct(b1);
        productInventory.addProduct(c1);

        // Registering admin as observer (for monitoring purposes)
        Admin admin = new Admin();
        vendingMachine.registerObserver(admin);

        // Using Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Main loop to interact with vending machine
        while (true) {
            // Display available products with their quantity and price
            System.out.println("\nAvailable Products:");
            for (Product product : productInventory.getProducts()) {
                System.out.println(product.getProductCode() + " - " + product.getName() +
                        " | Price: " + product.getPrice() + " cents | Quantity: " + product.getQuantity());
            }

            System.out.println("\n1. Insert Coin");
            System.out.println("2. Select Product");
            System.out.println("3. Cancel Transaction");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter coin value (in cents): ");
                    int coinValue = scanner.nextInt();
                    vendingMachine.insertCoin(coinValue);
                    break;
                case 2:
                    System.out.print("Enter product code (e.g., A1, B1, C1): ");
                    String productCode = scanner.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    vendingMachine.selectProduct(productCode, quantity);
                    break;
                case 3:
                    vendingMachine.cancelTransaction();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 4.");
            }
        }
    }
}
