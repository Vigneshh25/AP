package bikerental;

/** * Created by Vignesh.V on 21/06/24. */ // Main Class for Testing
public class Main {
    public static void main(String[] args) {
        RentalController rentalController = new RentalController();

        // Create Customers
        Customer customer1 = new Customer("C001", "John Doe");
        Customer customer2 = new Customer("C002", "Jane Doe");
        rentalController.addCustomer(customer1);
        rentalController.addCustomer(customer2);

        // Create Products
        Bike bike1 = BikeFactory.createBike("B001", "Mountain Bike", Size.MEDIUM,1000);
        Scooter scooter1 = ScooterFactory.createScooter("S001", "Electric Scooter", MotorType.ELECTRIC,1200);

        ProductRepository productRepository = ProductRepository.getInstance();
        productRepository.addProduct(bike1);
        productRepository.addProduct(scooter1);

        // Simulate User Interaction
        rentalController.simulateUserInteraction();
    }
}
