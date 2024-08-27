package rideshare.models;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Ride {
    private String rideId;
    private Passenger passenger;
    private Driver driver;
    private Location origin;
    private Location destination;
    private double fare;
    private RideStatus status;
    private List<Ride> rideHistory;

    public enum RideStatus {
        REQUESTED, ACCEPTED, STARTED, COMPLETED, CANCELED
    }

    public Ride(String rideId, Passenger passenger, Driver driver, Location origin, Location destination, double fare) {
        this.rideId = rideId;
        this.passenger = passenger;
        this.driver = driver;
        this.origin = origin;
        this.destination = destination;
        this.fare = fare;
        this.status = RideStatus.REQUESTED;
        this.rideHistory = new ArrayList<>();
    }

    public void addToHistory(Ride ride) {
        this.rideHistory.add(ride);
    }

    // Getters and setters
    public String getRideId() {
        return rideId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }

    public List<Ride> getRideHistory() {
        return rideHistory;
    }
}
