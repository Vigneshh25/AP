package bikerental;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        BikeRentalService bikeRentalService = new BikeRentalService();

        // Adding products to inventory
        bikeRentalService.addProduct(new Bike("1", "Mountain Bike", Size.SMALL));
        bikeRentalService.addProduct(new Bike("2", "Road Bike", Size.MEDIUM));
        bikeRentalService.addProduct(new Scooter("3", "Electric Scooter", MotorType.ELECTRIC));
        bikeRentalService.addProduct(new Scooter("4", "Gas Scooter", MotorType.GAS));

        // Adding customers
        bikeRentalService.addCustomer(new Customer("c1", "John Doe"));
        bikeRentalService.addCustomer(new Customer("c2", "Jane Smith"));

        // Renting a product
        bikeRentalService.rentProduct("1", "c1", new Date(), getFutureDate(7));
        bikeRentalService.rentProduct("3", "c2", new Date(), getFutureDate(3));

        // Creating a charge for a customer
        bikeRentalService.createCharge("c1", 100.0, new Date());
        bikeRentalService.createCharge("c2", 50.0, new Date());

        // Displaying customer balances
        System.out.println("Customer c1 balance: " + bikeRentalService.getCustomerBalance("c1"));
        System.out.println("Customer c2 balance: " + bikeRentalService.getCustomerBalance("c2"));

        // Displaying available products
        System.out.println("Available products:");
        for (Product product : bikeRentalService.getAvailableProducts()) {
            System.out.println(product.name);
        }

        // Displaying overdue rentals
        System.out.println("Overdue rentals:");
        for (Rental rental : bikeRentalService.getOverdueRentals(new Date())) {
            System.out.println("Rental ID: " + rental.rentalId + ", Product ID: " + rental.productId + ", Customer ID: " + rental.customerId);
        }

        // Displaying rentals by customer
        System.out.println("Rentals by customer c1:");
        for (Rental rental : bikeRentalService.getRentalsByCustomer("c1")) {
            System.out.println("Rental ID: " + rental.rentalId + ", Product ID: " + rental.productId);
        }
    }

    private static Date getFutureDate(int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }
}
