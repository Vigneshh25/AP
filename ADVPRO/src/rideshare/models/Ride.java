package rideshare.models;

public class Ride {
    private String rideId;
    private Passenger passenger;
    private Driver driver;
    private Location origin;
    private Location destination;
    private double fare;
    private RideStatus status;

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
    }

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
}
