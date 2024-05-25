package rideshare;

public class Ride {
    private Rider rider;
    private Driver driver;
    private Location startLocation;
    private Location endLocation;
    private String status;

    public Ride(Rider rider, Driver driver, Location startLocation, Location endLocation) {
        this.rider = rider;
        this.driver = driver;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.status = "ONGOING";
    }

    public Rider getRider() {
        return rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
