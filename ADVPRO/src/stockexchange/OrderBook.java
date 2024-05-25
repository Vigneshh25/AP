package stockexchange;

import java.util.PriorityQueue;
import java.util.Comparator;

public class OrderBook {
    private PriorityQueue<Order> buyOrders;
    private PriorityQueue<Order> sellOrders;

    public OrderBook() {
        buyOrders = new PriorityQueue<>(new BuyOrderComparator());
        sellOrders = new PriorityQueue<>(new SellOrderComparator());
    }

    public void addOrder(Order order) {
        if (order.getType().equals("buy")) {
            buyOrders.add(order);
        } else {
            sellOrders.add(order);
        }
    }

    public PriorityQueue<Order> getBuyOrders() {
        return buyOrders;
    }

    public PriorityQueue<Order> getSellOrders() {
        return sellOrders;
    }

    private class BuyOrderComparator implements Comparator<Order> {
        public int compare(Order o1, Order o2) {
            if (o1.getPrice() != o2.getPrice()) {
                return Double.compare(o2.getPrice(), o1.getPrice()); // Higher price priority
            }
            return o1.getTime().compareTo(o2.getTime()); // Earlier time priority
        }
    }

    private class SellOrderComparator implements Comparator<Order> {
        public int compare(Order o1, Order o2) {
            if (o1.getPrice() != o2.getPrice()) {
                return Double.compare(o1.getPrice(), o2.getPrice()); // Lower price priority
            }
            return o1.getTime().compareTo(o2.getTime()); // Earlier time priority
        }
    }
}
