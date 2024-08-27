package rideshare.services;


import rideshare.models.*;
import rideshare.utils.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.util.List;
import java.util.UUID;

public class RideManager {
    private static RideManager instance;
    private PathService pathService;

    private RideManager() {
        this.pathService = new PathService();
    }

    public static synchronized RideManager getInstance() {
        if (instance == null) {
            instance = new RideManager();
        }
        return instance;
    }

    public Ride bookRide(Passenger passenger, Location origin, Location destination) {
        List<Driver> availableDrivers = DatabaseManager.getInstance().getAvailableDrivers();
        if (availableDrivers.isEmpty()) {
            throw new RuntimeException("No drivers available");
        }
        Driver driver = availableDrivers.get(0); // For simplicity, pick the first available driver
        if (driver.isAvailabilityLocked()) {
            throw new RuntimeException("Driver's availability is locked");
        }
        driver.lockAvailabilityForDuration(30); // Lock availability for 30 minutes after booking
        double fare = FareCalculator.calculateFare(origin, destination);
        Ride ride = new Ride(UUID.randomUUID().toString(), passenger, driver, origin, destination, fare);
        ride.setStatus(Ride.RideStatus.ACCEPTED);
        DatabaseManager.getInstance().saveRide(ride);
        driver.addRideToHistory(ride);
        passenger.addRideToHistory(ride);
        NotificationService.notifyRideBooked(passenger, driver, ride);
        return ride;
    }

    public void completeRide(Ride ride, Feedback driverFeedback, Feedback passengerFeedback) {
        ride.setStatus(Ride.RideStatus.COMPLETED);
        ride.getDriver().setAvailable(true);
        ride.getDriver().addFeedback(driverFeedback);
        ride.getPassenger().addFeedback(passengerFeedback);
        DatabaseManager.getInstance().updateRide(ride);
        DatabaseManager.getInstance().updateDriver(ride.getDriver());
        DatabaseManager.getInstance().updatePassenger(ride.getPassenger());
        NotificationService.notifyRideCompleted(ride.getPassenger(), ride);
    }

    public List<Driver> findMatchingDrivers(RideRequest request) {
        List<Driver> availableDrivers = DatabaseManager.getInstance().getAvailableDrivers();
        List<Driver> matchingDrivers = new ArrayList<>();
        for (Driver driver : availableDrivers) {
            if (driver.isAvailable() && pathService.isPathOverlap(driver.getLocation(), driver.getLocation(), request.getRider().getLocation(), request.getRider().getDestination())) {
                matchingDrivers.add(driver);
            }
        }
        return matchingDrivers;
    }

    public void assignDriver(RideRequest request, Driver driver) {
        request.setStatus("ASSIGNED");
        driver.setAvailable(false);
        // Create a new Ride object, update status, etc.
    }

    public void cancelRide(Ride ride) {
        ride.setStatus(Ride.RideStatus.CANCELED);
        ride.getDriver().setAvailable(true);
        DatabaseManager.getInstance().updateRide(ride);
        NotificationService.notifyRideCanceled(ride.getPassenger(), ride);
    }
}
