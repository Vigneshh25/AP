package rideshare.models;

import java.util.*;

import java.util.List;

public abstract class User {
    private String id;
    private String name;
    private String email;
    private List<Feedback> feedbacks; // To store feedback
    private List<Ride> rideHistory; // To store ride history

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.feedbacks = new ArrayList<>();
        this.rideHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public double getAverageRating() {
        if (feedbacks.isEmpty()) return 0.0;
        return feedbacks.stream().mapToDouble(Feedback::getRating).average().orElse(0.0);
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void addRideToHistory(Ride ride) {
        rideHistory.add(ride);
    }

    public List<Ride> getRideHistory() {
        return rideHistory;
    }
}

