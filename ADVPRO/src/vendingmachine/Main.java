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

        // Using Scanner for user input.txt
        Scanner scanner = new Scanner(System.in);

        // WebCrawlerWithSameHostnameMain loop to interact with vending machine
        while (true) {
            System.out.println("\nAvailable Products:");
            for (Product product : productInventory.getProducts()) {
                System.out.println(product.getProductCode() + " - " + product.getName() +
                        " | Price: " + product.getPrice() + " cents | Quantity: " + product.getQuantity());
            }

            System.out.println("\n1. Insert Coin");
            System.out.println("2. Select Product");
            System.out.println("3. Select Payment Method");
            System.out.println("4. Cancel Transaction");
            System.out.println("5. View Remaining Balance");
            System.out.println("6. Refill Products (Admin only)");
            System.out.println("7. View Sales Report");
            System.out.println("8. Set Discount (Admin only)");
            System.out.println("9. Exit");

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
                    System.out.println("Select Payment Method:");
                    System.out.println("1. Card");
                    System.out.println("2. UPI");
                    System.out.println("3. Cash");
                    int paymentChoice = scanner.nextInt();
                    switch (paymentChoice) {
                        case 1:
                            vendingMachine.setPaymentHandler(new CardPaymentHandler());
                            System.out.println("Card payment method selected.");
                            break;
                        case 2:
                            vendingMachine.setPaymentHandler(new UPIPaymentHandler());
                            System.out.println("UPI payment method selected.");
                            break;
                        case 3:
                            vendingMachine.setPaymentHandler(new CashPaymentHandler());
                            System.out.println("Cash payment method selected.");
                            break;
                        default:
                            System.out.println("Invalid payment method.");
                            break;
                    }
                    break;
                case 4:
                    vendingMachine.cancelTransaction();
                    break;
                case 5:
                    System.out.println("Remaining balance: " + vendingMachine.getBalance() + " cents");
                    break;
                case 6:
                    System.out.println("Enter admin password:");
                    String password = scanner.next();
                    if ("admin123".equals(password)) {
                        System.out.print("Enter product code to refill (e.g., A1, B1, C1): ");
                        String refillProductCode = scanner.next().toUpperCase();
                        System.out.print("Enter quantity to add: ");
                        int refillQuantity = scanner.nextInt();
                        Product refillProduct = productInventory.getProducts(refillProductCode);
                        if (refillProduct != null) {
                            refillProduct.decrementQuantity(-refillQuantity);
                            System.out.println("Refilled product " + refillProduct.getName() + " with quantity " + refillQuantity);
                        } else {
                            System.out.println("Invalid product code.");
                        }
                    } else {
                        System.out.println("Invalid password.");
                    }
                    break;
                case 7:
                    System.out.println("Total sales: " + vendingMachine.getTotalSales() + " cents");
                    break;
                case 8:
                    System.out.println("Enter admin password:");
                    password = scanner.next();
                    if ("admin123".equals(password)) {
                        System.out.print("Enter product code to set discount (e.g., A1, B1, C1): ");
                        String discountProductCode = scanner.next().toUpperCase();
                        System.out.print("Enter discount amount (in cents): ");
                        int discountAmount = scanner.nextInt();
                        try {
                            productInventory.setDiscount(discountProductCode, discountAmount);
                            System.out.println("Discount set for product " + discountProductCode + " to " + discountAmount + " cents");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid password.");
                    }
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 8.");
            }
        }
    }
}
