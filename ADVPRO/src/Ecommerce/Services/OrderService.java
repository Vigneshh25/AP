package Ecommerce.Services;

import Ecommerce.Entities.Order;
import Ecommerce.Entities.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class OrderService {
    private static volatile OrderService instance;
    private CopyOnWriteArrayList<Order> orders;
    private InventoryService inventoryService;

    private OrderService() {
        orders = new CopyOnWriteArrayList<>();
        inventoryService = InventoryService.getInstance();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            synchronized (OrderService.class) {
                if (instance == null) {
                    instance = new OrderService();
                }
            }
        }
        return instance;
    }

    public boolean placeOrder(Order order) {
        boolean allItemsAvailable = order.getItems().entrySet().stream()
                .allMatch(entry -> inventoryService.reserveStock(entry.getKey(), entry.getValue()));

        if (allItemsAvailable) {
            order.setOrderDate(new Date());
            double totalAmount = order.getItems().entrySet().stream()
                    .mapToDouble(entry -> {
                        Product product = ProductService.getInstance().getAllProducts().stream()
                                .filter(p -> p.getId().equals(entry.getKey()))
                                .findFirst().orElse(null);
                        return product != null ? product.getPrice() * entry.getValue() : 0.0;
                    }).sum();
            order.setTotalAmount(totalAmount);
            orders.add(order);
            return true;
        } else {
            // Release any reserved stock if the order cannot be fulfilled
            order.getItems().forEach((productId, quantity) -> inventoryService.releaseStock(productId, quantity));
            return false;
        }
    }

    public List<Order> getOrdersByUserId(String userId) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUserId().equals(userId)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    public Order getOrderById(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }
}
