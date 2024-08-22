package bikerental.controller;

import bikerental.exception.ChargeException;
import bikerental.exception.RentalException;
import bikerental.model.Customer;
import bikerental.model.Product;
import bikerental.model.Rental;
import bikerental.service.ProductService;
import bikerental.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/** * Created by Vignesh.V on 21/06/24. */ // RentalController Class
import java.util.concurrent.TimeUnit;

public class RentalController {
    private final ProductService productService;
    private final UserService userService;
    private final Scanner scanner;

    public RentalController(ProductService productService, UserService userService, Scanner scanner) {
        this.productService = productService;
        this.userService = userService;
        this.scanner = scanner;
    }

    public void rentProduct(String productId, String customerId, Date rentalDate, int rentalDurationDays) {
        try {
            Date dueDate = new Date(rentalDate.getTime() + TimeUnit.DAYS.toMillis(rentalDurationDays));
            productService.rentProduct(productId, customerId, rentalDate, dueDate);
            double chargeAmount = productService.calculateCharge(productId, rentalDurationDays);
            createCharge(customerId, chargeAmount, rentalDate);
            System.out.println("Successfully rented product.");
        } catch (RentalException e) {
            System.out.println("Failed to rent product: " + e.getMessage());
        }
    }
    public List<Rental> viewRentalsByCustomer(String customerId) {
        List<Rental> rentals = productService.getRentalsByCustomer(customerId);
        if (rentals.isEmpty()) {
            System.out.println("No rentals found for customer with ID: " + customerId);
        } else {
            System.out.println("Rentals for customer with ID " + customerId + ":");
            for (Rental rental : rentals) {
                System.out.println("Rental ID: " + rental.getRentalId() +
                        ", Product ID: " + rental.getProductId() +
                        ", Rental Date: " + rental.getRentalDate() +
                        ", Due Date: " + rental.getDueDate() +
                        ", Return Date: " + rental.getReturnDate());
            }
        }
        return rentals;
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

    public void viewAvailableProducts() {
        List<Product> products = productService.getAvailableProducts();
        if (products.isEmpty()) {
            System.out.println("No products available for rent.");
        } else {
            System.out.println("Available Products:");
            for (Product product : products) {
                System.out.println("Product ID: " + product.getId() +
                        ", Name: " + product.getName() +
                        ", Daily Rate: $" + product.getDailyRate());
            }
        }
    }
    public void addProduct(Product product)
    {
        productService.addProduct(product);
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
            System.out.println("6. View rentals by customer");
            System.out.println("7. View available products");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter Product ID:");
                    String productId = scanner.nextLine();
                    System.out.println("Enter Customer ID:");
                    String customerId = scanner.nextLine();
                    System.out.println("Enter Rental Duration (days):");
                    int rentalDurationDays = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    Date rentalDate = new Date();
                    rentProduct(productId, customerId, rentalDate, rentalDurationDays);
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
                    System.out.println("Enter Customer ID:");
                    customerId = scanner.nextLine();
                    viewRentalsByCustomer(customerId);
                    break;
                case 7:
                    viewAvailableProducts();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }
}
