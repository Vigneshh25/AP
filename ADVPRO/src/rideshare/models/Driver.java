package rideshare.models;


import java.time.LocalDateTime;

public class Driver extends User {
    private String vehicleDetails;
    private boolean available;
    private Location location;
    private boolean availabilityLocked;
    private LocalDateTime availabilityLockUntil;

    public Driver(String id, String name, String email, Location location, String vehicle) {
        super(id, name, email);
        this.location = location;
        this.vehicleDetails = vehicle;
        this.available = true;
        this.availabilityLocked = false;
        this.availabilityLockUntil = null;
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return available && !isAvailabilityLocked();
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailabilityLocked() {
        return availabilityLocked && LocalDateTime.now().isBefore(availabilityLockUntil);
    }

    public void lockAvailabilityForDuration(int minutes) {
        this.availabilityLocked = true;
        this.availabilityLockUntil = LocalDateTime.now().plusMinutes(minutes);
    }
}
