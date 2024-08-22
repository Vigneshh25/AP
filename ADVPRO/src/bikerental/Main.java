package bikerental;

import bikerental.controller.RentalController;
import bikerental.factory.BikeFactory;
import bikerental.factory.ScooterFactory;
import bikerental.model.*;
import bikerental.repository.ProductRepository;
import bikerental.service.ProductService;
import bikerental.service.UserService;

import java.util.Scanner;

/** * Created by Vignesh.V on 21/06/24. */ // Main Class for Testing
public class Main {
    public static void main(String[] args) {

        ProductService productService = new ProductService();
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        RentalController rentalController = new RentalController(productService,userService,scanner);

        // Create Customers
        Customer customer1 = new Customer("C001", "John Doe");
        Customer customer2 = new Customer("C002", "Jane Doe");
        rentalController.addCustomer(customer1);
        rentalController.addCustomer(customer2);

        // Create Products
        Bike bike1 = BikeFactory.createBike("B001", "Mountain Bike", Size.MEDIUM,1000);
        Scooter scooter1 = ScooterFactory.createScooter("S001", "Electric Scooter", MotorType.ELECTRIC,1200);

        rentalController.addProduct(bike1);
        rentalController.addProduct(scooter1);

        // Simulate User Interaction
        rentalController.simulateUserInteraction();
    }
}
