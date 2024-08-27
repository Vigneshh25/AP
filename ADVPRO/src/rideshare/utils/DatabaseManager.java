package rideshare.utils;



import rideshare.models.Driver;
import rideshare.models.Passenger;
import rideshare.models.Ride;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Map<String, Ride> rideDatabase;
    private Map<String, Driver> driverDatabase;
    private Map<String, Passenger> passengerDatabase;

    private DatabaseManager() {
        rideDatabase = new HashMap<>();
        driverDatabase = new HashMap<>();
        passengerDatabase = new HashMap<>();
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void saveRide(Ride ride) {
        rideDatabase.put(ride.getRideId(), ride);
    }

    public void updateRide(Ride ride) {
        rideDatabase.put(ride.getRideId(), ride);
    }

    public void addDriver(Driver driver) {
        driverDatabase.put(driver.getId(), driver);
    }

    public void updateDriver(Driver driver) {
        driverDatabase.put(driver.getId(), driver);
    }

    public Driver getDriver(String driverId) {
        return driverDatabase.get(driverId);
    }

    public void addPassenger(Passenger passenger) {
        passengerDatabase.put(passenger.getId(), passenger);
    }

    public void updatePassenger(Passenger passenger) {
        passengerDatabase.put(passenger.getId(), passenger);
    }

    public Passenger getPassenger(String passengerId) {
        return passengerDatabase.get(passengerId);
    }

    public List<Driver> getAvailableDrivers() {
        return driverDatabase.values().stream().filter(Driver::isAvailable).collect(Collectors.toList());
    }
}