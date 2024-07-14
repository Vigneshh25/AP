package googlemap.entity;

public class POI {
    private String name;
    private Location location;
    private String category;
    private double rating;

    public POI(String name, Location location, String category, double rating) {
        this.name = name;
        this.location = location;
        this.category = category;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
// Getters and setters
}
