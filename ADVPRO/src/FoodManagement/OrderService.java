package FoodManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class OrderService {
    private static final OrderService INSTANCE = new OrderService(2); // Example pool size
    private final Map<String, List<Order>> orderHistory = new ConcurrentHashMap<>();
    private final ExecutorService orderExecutor;
    private final BlockingQueue<Runnable> waitingQueue;

    private OrderService(int threadPoolSize) {
        orderExecutor = new ThreadPoolExecutor(threadPoolSize, threadPoolSize,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        waitingQueue = new LinkedBlockingQueue<>();
    }

    public static OrderService getInstance() {
        return INSTANCE;
    }

    public void placeOrder(User currentUser, Restaurant restaurant, String foodItemName, int quantity) {
        if (restaurant == null) {
            System.out.println("Restaurant not found.");
            return;
        }
        FoodItem foodItem = restaurant.getFoodItemByName(foodItemName);
        if (foodItem == null) {
            System.out.println("Food item not found.");
            return;
        }
        if (foodItem.getQuantity() < quantity) {
            System.out.println("Cannot place order");
            return;
        }
        foodItem.reduceQuantity(quantity);
        long startTime = System.currentTimeMillis();
        long endTime = startTime + foodItem.getPreparationTime() * quantity * 1000;
        Order order = new Order(restaurant.getName(), foodItemName, quantity, startTime, endTime);
        orderHistory.computeIfAbsent(currentUser.getPhoneNumber(), k -> new ArrayList<>()).add(order);

        Runnable orderTask = () -> {
            try {
                System.out.println("Order for " + restaurant.getName() + " is being prepared. It will take " + (foodItem.getPreparationTime() * quantity) + " seconds.");
                TimeUnit.SECONDS.sleep(foodItem.getPreparationTime() * quantity);
                System.out.println("Order for " + restaurant.getName() + " is ready!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Order preparation interrupted.");
            }
        };

        if (!orderExecutor.submit(orderTask).isCancelled()) {
            System.out.println("Order placed successfully.");
        } else {
            waitingQueue.offer(orderTask);
            System.out.println("All threads are busy. Your order is in the waiting queue.");
        }
    }

    public void checkOrderStatus(User currentUser) {
        List<Order> orders = orderHistory.get(currentUser.getPhoneNumber());
        if (orders == null || orders.isEmpty()) {
            System.out.println("No order history.");
            return;
        }
        for (Order order : orders) {
            long remainingTime = order.getRemainingTime();
            if (remainingTime > 0) {
                System.out.println("Order from " + order.getRestaurantName() + " is still being prepared. Remaining time: " + remainingTime / 1000 + " seconds.");
            } else {
                System.out.println("Order from " + order.getRestaurantName() + " is ready or already delivered.");
            }
        }
    }

    public void shutdown() {
        orderExecutor.shutdown();
        try {
            if (!orderExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                orderExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            orderExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
