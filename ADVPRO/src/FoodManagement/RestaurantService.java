package FoodManagement;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class RestaurantService {
    private static final RestaurantService INSTANCE = new RestaurantService();
    private final Map<String, Restaurant> restaurants = new ConcurrentHashMap<>();

    private RestaurantService() {}

    public static RestaurantService getInstance() {
        return INSTANCE;
    }

    public void registerRestaurant(User currentUser, String name, String pincodes) {
        if (currentUser == null) {
            System.out.println("Please login to register a restaurant.");
            return;
        }
        Set<String> serviceablePincodes = new HashSet<>(Arrays.asList(pincodes.split("/")));
        Restaurant restaurant = new Restaurant(name, serviceablePincodes);
        restaurants.put(name, restaurant);
        System.out.println("Restaurant registered successfully.");
    }

    public void addFoodItemToRestaurant(String restaurantName, String foodItemName, double foodItemPrice, int initialQuantity, int preparationTime) {
        Restaurant restaurant = restaurants.get(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurant not found.");
            return;
        }
        restaurant.addFoodItem(new FoodItem(foodItemName, foodItemPrice, initialQuantity, preparationTime));
        System.out.println("Food item added successfully to " + restaurantName);
    }

    public void updateFoodItemQuantity(String restaurantName, String foodItemName, int quantityToAdd) {
        Restaurant restaurant = restaurants.get(restaurantName);
        if (restaurant == null) {
            System.out.println("Restaurant not found.");
            return;
        }
        FoodItem foodItem = restaurant.getFoodItemByName(foodItemName);
        if (foodItem == null) {
            System.out.println("Food item not found.");
            return;
        }
        foodItem.addQuantity(quantityToAdd);
        System.out.println(foodItemName + " quantity updated to " + foodItem.getQuantity());
    }

    public void rateRestaurant(String name, int rating, String comment) {
        Restaurant restaurant = restaurants.get(name);
        if (restaurant == null) {
            System.out.println("Restaurant not found.");
            return;
        }
        Review review = new Review(rating, comment);
        restaurant.addReview(review);
        System.out.println("Review added successfully.");
    }

    public void showRestaurants(User currentUser, String orderBy) {
        if (currentUser == null) {
            System.out.println("Please login to view restaurants.");
            return;
        }

        List<Restaurant> serviceableRestaurants = restaurants.values().stream()
                .filter(r -> r.getServiceablePincodes().contains(currentUser.getPincode()))
                .collect(Collectors.toList());

        if (orderBy.equals("price")) {
            serviceableRestaurants.sort(Comparator.comparingDouble(
                    r -> r.getFoodItems().stream().mapToDouble(FoodItem::getPrice).min().orElse(Double.MAX_VALUE)));
        } else if (orderBy.equals("rating")) {
            serviceableRestaurants.sort(Comparator.comparingDouble(Restaurant::getAverageRating).reversed());
        }

        for (Restaurant restaurant : serviceableRestaurants) {
            System.out.println(restaurant.getName());
            for (FoodItem foodItem : restaurant.getFoodItems()) {
                System.out.println("  - " + foodItem.getName() + ": " + foodItem.getPrice());
            }
        }
    }

    public Restaurant getRestaurantByName(String name) {
        return restaurants.get(name);
    }

    public Collection<Restaurant> getAllRestaurants() {
        return restaurants.values();
    }
}
