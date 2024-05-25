package rideshare;

public class Driver extends User {
    private Location location;
    private boolean availability;
    private String vehicle;

    public Driver(String id, String name, Location location, String vehicle) {
        super(id, name);
        this.location = location;
        this.vehicle = vehicle;
        this.availability = true;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getVehicle() {
        return vehicle;
    }
}
