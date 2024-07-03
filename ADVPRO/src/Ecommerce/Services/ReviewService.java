package Ecommerce.Services;

import Ecommerce.Entities.Review;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ReviewService {
    private static volatile ReviewService instance;
    private List<Review> reviews;

    private ReviewService() {
        reviews = new CopyOnWriteArrayList<>();
    }

    public static ReviewService getInstance() {
        if (instance == null) {
            synchronized (ReviewService.class) {
                if (instance == null) {
                    instance = new ReviewService();
                }
            }
        }
        return instance;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviewsByProductId(String productId) {
        return reviews.stream()
                .filter(review -> review.getProductId().equals(productId))
                .collect(Collectors.toList());
    }
}
