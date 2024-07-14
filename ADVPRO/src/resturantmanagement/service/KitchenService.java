package resturantmanagement.service;

import resturantmanagement.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class KitchenService {
    private List<Order> pendingOrders = new ArrayList<>();

    public void addOrder(Order order) {
        pendingOrders.add(order);
    }

    public List<Order> getPendingOrders() {
        return pendingOrders;
    }

    public void markOrderPrepared(int orderId) {
        for (Order order : pendingOrders) {
            if (order.getOrderId() == orderId) {
                order.markPrepared();
                pendingOrders.remove(order);
                break;
            }
        }
    }
}
