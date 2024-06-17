package rideshare.models;


public class RideRequest {
    private Rider rider;
    private String status;

    public RideRequest(Rider rider) {
        this.rider = rider;
        this.status = "PENDING";
    }

    public Rider getRider() {
        return rider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
