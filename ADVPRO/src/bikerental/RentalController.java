package bikerental;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/** * Created by Vignesh.V on 21/06/24. */ // RentalController Class
public class RentalController {
    private final ProductService productService = new ProductService();
    private final UserService userService = new UserService();
    private final Scanner scanner = new Scanner(System.in);

    public void rentProduct(String productId, String customerId, Date rentalDate, Date dueDate) {
        try {
            productService.rentProduct(productId, customerId, rentalDate, dueDate);
            System.out.println("Successfully rented product.");
        } catch (RentalException e) {
            System.out.println("Failed to rent product: " + e.getMessage());
        }
    }

    public void returnProduct(String rentalId, Date returnDate) {
        try {
            productService.returnProduct(rentalId, returnDate);
            System.out.println("Successfully returned product.");
        } catch (RentalException e) {
            System.out.println("Failed to return product: " + e.getMessage());
        }
    }

    public List<Product> getAvailableProducts() {
        return productService.getAvailableProducts();
    }

    public void createCharge(String customerId, double amount, Date date) {
        try {
            productService.createCharge(customerId, amount, date);
            System.out.println("Charge created successfully.");
        } catch (ChargeException e) {
            System.out.println("Failed to create charge: " + e.getMessage());
        }
    }

    public List<Rental> getOverdueRentals(Date currentDate) {
        return productService.getOverdueRentals(currentDate);
    }

    public List<Rental> getRentalsByCustomer(String customerId) {
        return productService.getRentalsByCustomer(customerId);
    }

    public double getCustomerBalance(String customerId) {
        return userService.getCustomerBalance(customerId);
    }

    public void addCustomer(Customer customer) {
        userService.addCustomer(customer);
    }

    public void simulateUserInteraction() {
        System.out.println("Welcome to the Rental Management System!");
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Rent a product");
            System.out.println("2. Return a product");
            System.out.println("3. Create a charge");
            System.out.println("4. Check overdue rentals");
            System.out.println("5. Check customer balance");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter Product ID:");
                    String productId = scanner.nextLine();
                    System.out.println("Enter Customer ID:");
                    String customerId = scanner.nextLine();
                    Date rentalDate = new Date();
                    Date dueDate = new Date(System.currentTimeMillis() + 86400000L); // Default due date: tomorrow
                    rentProduct(productId, customerId, rentalDate, dueDate);
                    break;
                case 2:
                    System.out.println("Enter Rental ID:");
                    String rentalId = scanner.nextLine();
                    System.out.println("Enter Return Date (YYYY-MM-DD HH:MM:SS):");
                    String returnDateString = scanner.nextLine();
                    try {
                        Date returnDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(returnDateString);
                        returnProduct(rentalId, returnDate);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD HH:MM:SS.");
                    }
                    break;
                case 3:
                    System.out.println("Enter Customer ID:");
                    String chargeCustomerId = scanner.nextLine();
                    System.out.println("Enter Amount:");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    System.out.println("Enter Date (YYYY-MM-DD HH:MM:SS):");
                    String dateString = scanner.nextLine();
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
                        createCharge(chargeCustomerId, amount, date);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD HH:MM:SS.");
                    }
                    break;
                case 4:
                    System.out.println("Enter Current Date (YYYY-MM-DD HH:MM:SS):");
                    String currentDateStr = scanner.nextLine();
                    try {
                        Date currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(currentDateStr);
                        List<Rental> overdueRentals = getOverdueRentals(currentDate);
                        System.out.println("Overdue Rentals:");
                        for (Rental overdueRental : overdueRentals) {
                            System.out.println("Rental ID: " + overdueRental.getRentalId() +
                                    ", Customer ID: " + overdueRental.getCustomerId() +
                                    ", Due Date: " + overdueRental.getDueDate());
                        }
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD HH:MM:SS.");
                    }
                    break;
                case 5:
                    System.out.println("Enter Customer ID:");
                    String balanceCustomerId = scanner.nextLine();
                    double balance = getCustomerBalance(balanceCustomerId);
                    System.out.println("Customer Balance: $" + balance);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }
}
