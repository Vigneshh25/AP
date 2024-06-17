package rideshare;




import rideshare.models.*;
import rideshare.services.PaymentProcessor;
import rideshare.services.RideManager;
import rideshare.utils.DatabaseManager;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = DatabaseManager.getInstance();

        // Adding some dummy drivers to the database
        Driver driver1 = new Driver("d1", "John Doe", "john@example.com", new Location(10.0, 20.0), "Car Model XYZ");
        Driver driver2 = new Driver("d2", "Alice Smith", "alice@example.com", new Location(12.0, 22.0), "Car Model ABC");
        Driver driver3 = new Driver("d3", "Bob Brown", "bob@example.com", new Location(15.0, 25.0), "Car Model DEF");
        dbManager.addDriver(driver1);
        dbManager.addDriver(driver2);
        dbManager.addDriver(driver3);

        // Passenger trying to book a ride
        Passenger passenger = new Passenger("p1", "Jane Doe", "jane@example.com");
        RideManager rideManager = RideManager.getInstance();

        try {
            Location origin = new Location(15.0, 25.0);
            Location destination = new Location(20.0, 30.0);

            // Test case 1: Book a ride successfully
            System.out.println("\nTest Case 1: Booking a Ride");
            Ride ride = rideManager.bookRide(passenger, origin, destination);
            System.out.println("Ride ID: " + ride.getRideId() + ", Fare: " + ride.getFare());

            // Test case 2: Process payment
            System.out.println("\nTest Case 2: Processing Payment");
            PaymentProcessor.processPayment(passenger.getId(), ride.getFare());

            rideManager.completeRide(ride);

            // Test case 3: Find matching drivers for a ride request
            System.out.println("\nTest Case 3: Finding Matching Drivers");
            RideRequest rideRequest = new RideRequest(new Rider("r1", "Jack Black", origin, destination));
            List<Driver> matchingDrivers = rideManager.findMatchingDrivers(rideRequest);
            if (!matchingDrivers.isEmpty()) {
                System.out.println("Matching Drivers:");
                for (Driver driver : matchingDrivers) {
                    System.out.println(driver.getName() + " - " + driver.getVehicleDetails());
                }
            } else {
                System.out.println("No matching drivers found for the ride request.");
            }

            // Test case 4: Assign a driver to the ride request
            if (!matchingDrivers.isEmpty()) {
                System.out.println("\nTest Case 4: Assigning Driver to Ride Request");
                Driver assignedDriver = matchingDrivers.get(0); // Select the first matching driver
                rideManager.assignDriver(rideRequest, assignedDriver);
                System.out.println("Assigned driver: " + assignedDriver.getName());
            }

            // Test case 5: Cancel a ride
            System.out.println("\nTest Case 5: Canceling Ride");
            rideManager.cancelRide(ride);

            // Test case 6: Process refund for canceled ride
            System.out.println("\nTest Case 6: Processing Refund for Canceled Ride");
            PaymentProcessor.processRefund(passenger.getId(), ride.getFare());

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
