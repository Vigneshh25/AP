package FoodManagement;

public class Order {
    private final String restaurantName;
    private final String foodItemName;
    private final int quantity;
    private final long startTime;
    private final long endTime;

    public Order(String restaurantName, String foodItemName, int quantity, long startTime, long endTime) {
        this.restaurantName = restaurantName;
        this.foodItemName = foodItemName;
        this.quantity = quantity;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getRemainingTime() {
        return endTime - System.currentTimeMillis();
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getFoodItemName() {
        return foodItemName;
    }
}
