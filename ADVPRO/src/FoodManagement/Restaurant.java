package FoodManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {
    private final String name;
    private final Set<String> serviceablePincodes;
    private final List<FoodItem> foodItems;
    private final List<Review> reviews;
    private final ReentrantLock lock = new ReentrantLock();

    public Restaurant(String name, Set<String> serviceablePincodes) {
        this.name = name;
        this.serviceablePincodes = serviceablePincodes;
        this.foodItems = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public void addFoodItem(FoodItem foodItem) {
        lock.lock();
        try {
            this.foodItems.add(foodItem);
        } finally {
            lock.unlock();
        }
    }

    public void addReview(Review review) {
        lock.lock();
        try {
            this.reviews.add(review);
        } finally {
            lock.unlock();
        }
    }

    public double getAverageRating() {
        lock.lock();
        try {
            return this.reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
        } finally {
            lock.unlock();
        }
    }

    public FoodItem getFoodItemByName(String foodItemName) {
        lock.lock();
        try {
            return foodItems.stream()
                .filter(foodItem -> foodItem.getName().equals(foodItemName))
                .findFirst()
                .orElse(null);
        } finally {
            lock.unlock();
        }
    }

    public String getName() {
        return name;
    }

    public Set<String> getServiceablePincodes() {
        return serviceablePincodes;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }
}
