package rideshare;




import rideshare.models.*;
import rideshare.services.PaymentProcessor;
import rideshare.services.RideManager;
import rideshare.utils.DatabaseManager;

import java.util.List;


import java.util.List;
import java.util.UUID;

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

        // Adding some dummy passengers to the database
        Passenger passenger1 = new Passenger("p1", "Jane Doe", "jane@example.com");
        Passenger passenger2 = new Passenger("p2", "Michael Smith", "michael@example.com");

        dbManager.addPassenger(passenger1);
        dbManager.addPassenger(passenger2);

        // RideManager instance
        RideManager rideManager = RideManager.getInstance();

        try {
            Location origin = new Location(15.0, 25.0);
            Location destination = new Location(20.0, 30.0);

            // Test case 1: Book a ride successfully
            System.out.println("\nTest Case 1: Booking a Ride");
            Ride ride = rideManager.bookRide(passenger1, origin, destination);
            System.out.println("Ride ID: " + ride.getRideId() + ", Fare: " + ride.getFare());

            // Test case 2: Process payment
            System.out.println("\nTest Case 2: Processing Payment");
            PaymentProcessor.processPayment(passenger1.getId(), ride.getFare());

            // Adding feedback for the ride
            Feedback driverFeedback = new Feedback(UUID.randomUUID().toString(), driver1, "Great ride!", 4.5);
            Feedback passengerFeedback = new Feedback(UUID.randomUUID().toString(), passenger1, "Thank you for the ride!", 4.0);

            // Complete the ride with feedback
            rideManager.completeRide(ride, driverFeedback, passengerFeedback);

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
            PaymentProcessor.processRefund(passenger1.getId(), ride.getFare());

            // Test case 7: Fetch and print driver and passenger ratings and feedback
            System.out.println("\nTest Case 7: Fetch Driver and Passenger Ratings and Feedback");
            Driver fetchedDriver = dbManager.getDriver("d1");
            Passenger fetchedPassenger = dbManager.getPassenger("p1");

            System.out.println("Driver Ratings: " + fetchedDriver.getAverageRating());
            System.out.println("Driver Feedbacks: ");
            for (Feedback feedback : fetchedDriver.getFeedbacks()) {
                System.out.println("Feedback from " + feedback.getUser().getName() + ": " + feedback.getComments() + " - Rating: " + feedback.getRating());
            }

            System.out.println("Passenger Ratings: " + fetchedPassenger.getAverageRating());
            System.out.println("Passenger Feedbacks: ");
            for (Feedback feedback : fetchedPassenger.getFeedbacks()) {
                System.out.println("Feedback from " + feedback.getUser().getName() + ": " + feedback.getComments() + " - Rating: " + feedback.getRating());
            }

            // Print ride history for the passenger
            System.out.println("\nPassenger Ride History:");
            for (Ride r : fetchedPassenger.getRideHistory()) {
                System.out.println("Ride ID: " + r.getRideId() + ", Status: " + r.getStatus());
            }

            // Print ride history for the driver
            System.out.println("\nDriver Ride History:");
            for (Ride r : fetchedDriver.getRideHistory()) {
                System.out.println("Ride ID: " + r.getRideId() + ", Status: " + r.getStatus());
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
