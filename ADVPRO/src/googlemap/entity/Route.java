package googlemap.entity;

import java.util.List;

public class Route {
    private List<Location> path;
    private double distance;
    private double duration;

    public Route(List<Location> path, double distance, double duration) {
        this.path = path;
        this.distance = distance;
        this.duration = duration;
    }

    public List<Location> getPath() {
        return path;
    }

    public void setPath(List<Location> path) {
        this.path = path;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
// Getters and setters
}
