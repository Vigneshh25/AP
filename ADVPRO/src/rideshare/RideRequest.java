package rideshare;

public class RideRequest {
    private Rider rider;
    private Location startLocation;
    private Location endLocation;
    private String status;

    public RideRequest(Rider rider, Location startLocation, Location endLocation) {
        this.rider = rider;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.status = "PENDING";
    }

    public Rider getRider() {
        return rider;
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
