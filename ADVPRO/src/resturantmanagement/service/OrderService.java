package resturantmanagement.service;

import resturantmanagement.entity.MenuItem;
import resturantmanagement.entity.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderService {
    private Map<Integer, Order> orders = new HashMap<>();
    private int nextOrderId = 1;

    public Order createOrder(int tableId) {
        Order order = new Order(nextOrderId++, tableId);
        orders.put(order.getOrderId(), order);
        return order;
    }

    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    public double calculateBill(Order order) {
        double total = 0;
        for (Map.Entry<MenuItem, Integer> entry : order.getItems().entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}
