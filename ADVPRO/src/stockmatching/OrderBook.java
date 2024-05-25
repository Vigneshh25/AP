package stockmatching;

import java.util.ArrayList;
import java.util.List;

public class OrderBook {
    private List<Order> buyOrders = new ArrayList<>();
    private List<Order> sellOrders = new ArrayList<>();

    public void addOrder(Order order) {
        if (order.getType() == OrderType.BUY) {
            buyOrders.add(order);
        } else {
            sellOrders.add(order);
        }
    }

    public List<Order> getBuyOrders() {
        return buyOrders;
    }

    public List<Order> getSellOrders() {
        return sellOrders;
    }

    public void matchOrders(MatchingStrategy strategy) {
        strategy.match(this);
    }
}
