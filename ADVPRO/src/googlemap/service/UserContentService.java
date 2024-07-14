package googlemap.service;

import googlemap.entity.Review;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class UserContentService {
    private Map<String, List<Review>> reviews = new HashMap<>();

    private static UserContentService instance;

    private UserContentService() {}

    public static synchronized UserContentService getInstance() {
        if (instance == null) {
            instance = new UserContentService();
        }
        return instance;
    }

    public void addReview(String poiId, Review review) {
        reviews.computeIfAbsent(poiId, k -> new ArrayList<>()).add(review);
    }

    public List<Review> getReviews(String poiId) {
        return reviews.getOrDefault(poiId, new ArrayList<>());
    }
}
