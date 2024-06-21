package bikerental;

import java.util.Date;
import java.util.List;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // Main Class for Testing
public class Main {
    public static void main(String[] args) {
        RentalController rentalController = new RentalController();

        // Create Customers
        Customer customer1 = new Customer("C001", "John Doe");
        Customer customer2 = new Customer("C002", "Jane Doe");
        rentalController.addCustomer(customer1);
        rentalController.addCustomer(customer2);

        // Create Products
        Bike bike1 = BikeFactory.createBike("B001", "Mountain Bike", Size.MEDIUM);
        Scooter scooter1 = ScooterFactory.createScooter("S001", "Electric Scooter", MotorType.ELECTRIC);

        ProductRepository productRepository = ProductRepository.getInstance();
        productRepository.addProduct(bike1);
        productRepository.addProduct(scooter1);

        // Rent Products
        rentalController.rentProduct(bike1.getId(), customer1.getId(), new Date(), new Date(System.currentTimeMillis() + 86400000L));
        rentalController.rentProduct(scooter1.getId(), customer2.getId(), new Date(), new Date(System.currentTimeMillis() + 86400000L));

        // Create Charges
        rentalController.createCharge(customer1.getId(), 15.0, new Date());
        rentalController.createCharge(customer2.getId(), 20.0, new Date());

        // Return Products
        Rental rental = productRepository.getRentalsByCustomer(customer1.getId()).get(0);
        rentalController.returnProduct(rental.getRentalId(), new Date());

        // Print Available Products
        System.out.println("Available Products:");
        for (Product product : rentalController.getAvailableProducts()) {
            System.out.println(product.getName());
        }

        // Print Customer Balances
        System.out.println("Customer Balances:");
        System.out.println(customer1.getName() + ": $" + rentalController.getCustomerBalance(customer1.getId()));
        System.out.println(customer2.getName() + ": $" + rentalController.getCustomerBalance(customer2.getId()));

        // Get and Print Overdue Rentals
        List<Rental> overdueRentals = rentalController.getOverdueRentals(new Date(System.currentTimeMillis() + 172800000L));
        System.out.println("Overdue Rentals:");
        for (Rental overdueRental : overdueRentals) {
            System.out.println("Rental ID: " + overdueRental.getRentalId() + ", Customer ID: " + overdueRental.getCustomerId());
        }
    }
}
