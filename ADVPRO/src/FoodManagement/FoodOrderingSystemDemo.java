package FoodManagement;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class FoodOrderingSystemDemo {
    public static void main(String[] args) {
        UserService userService = UserService.getInstance();
        RestaurantService restaurantService = RestaurantService.getInstance();
        OrderService orderService = OrderService.getInstance();

        // Test case as given in the prompt
        userService.registerUser("Pralove", "M", "phoneNumber-1", "HSR");
        userService.registerUser("Nitesh", "M", "phoneNumber-2", "BTM");
        userService.registerUser("Vatsal", "M", "phoneNumber-3", "BTM");

        userService.loginUser("phoneNumber-1");
        restaurantService.registerRestaurant(userService.getCurrentUser(), "Food Court-1", "BTM/HSR");
        restaurantService.addFoodItemToRestaurant("Food Court-1", "NI Thali", 100, 5, 2);
        restaurantService.registerRestaurant(userService.getCurrentUser(), "Food Court-2", "BTM");
        restaurantService.addFoodItemToRestaurant("Food Court-2", "Burger", 120, 3, 5);

        userService.loginUser("phoneNumber-2");
        restaurantService.registerRestaurant(userService.getCurrentUser(), "Food Court-3", "HSR");
        restaurantService.addFoodItemToRestaurant("Food Court-3", "SI Thali", 150, 1, 3);

        userService.loginUser("phoneNumber-3");
        restaurantService.showRestaurants(userService.getCurrentUser(), "price");

        orderService.placeOrder(userService.getCurrentUser(), restaurantService.getRestaurantByName("Food Court-1"), "NI Thali", 2);
        orderService.placeOrder(userService.getCurrentUser(), restaurantService.getRestaurantByName("Food Court-2"), "Burger", 1);

        restaurantService.rateRestaurant("Food Court-2", 3, "Good Food");
        restaurantService.rateRestaurant("Food Court-1", 5, "Nice Food");
        restaurantService.showRestaurants(userService.getCurrentUser(), "rating");

        userService.loginUser("phoneNumber-1");
        restaurantService.updateFoodItemQuantity("Food Court-2", "Burger", 5);

        // Check order status
        orderService.checkOrderStatus(userService.getCurrentUser());

        // Interactive functions
        System.out.println("Registered Users:");
        for (User user : userService.getAllUsers()) {
            System.out.println("Name: " + user.getName() + ", Phone: " + user.getPhoneNumber() + ", Pincode: " + user.getPincode());
        }

        System.out.println("Registered Restaurants:");
        for (Restaurant restaurant : restaurantService.getAllRestaurants()) {
            System.out.println("Name: " + restaurant.getName());
            for (FoodItem foodItem : restaurant.getFoodItems()) {
                System.out.println("  - " + foodItem.getName() + ": " + foodItem.getPrice() + ", Quantity: " + foodItem.getQuantity() + ", Preparation Time: " + foodItem.getPreparationTime() + "s");
            }
        }

        // Shutdown the service
        orderService.shutdown();
    }
}
