package rideshare.models;

public class Feedback {
    private String id;
    private User user; // This can be a Driver or Passenger
    private String comments;
    private double rating;

    public Feedback(String id, User user, String comments, double rating) {
        this.id = id;
        this.user = user;
        this.comments = comments;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getComments() {
        return comments;
    }

    public double getRating() {
        return rating;
    }
}
