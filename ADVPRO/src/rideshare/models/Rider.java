package rideshare.models;


import java.util.List;

public class Rider extends User {
    private Location location;
    private Location destination;

    public Rider(String id, String name, Location location, Location destination) {
        super(id, name, null);
        this.location = location;
        this.destination = destination;
    }

    public Location getLocation() {
        return location;
    }

    public Location getDestination() {
        return destination;
    }

    @Override
    public void addFeedback(Feedback feedback) {
        super.addFeedback(feedback);
    }

    @Override
    public List<Ride> getRideHistory() {
        return super.getRideHistory();
    }
}

