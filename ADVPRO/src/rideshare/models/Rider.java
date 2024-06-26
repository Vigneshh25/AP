package rideshare.models;



public class Rider extends User {
    private Location location;
    private Location destination;

    public Rider(String id, String name, Location location, Location destination) {
        super(id, name,null);
        this.location = location;
        this.destination = destination;
    }

    public Location getLocation() {
        return location;
    }

    public Location getDestination() {
        return destination;
    }
}
