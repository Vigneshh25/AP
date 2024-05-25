package stockmatching;

import java.util.HashMap;
import java.util.Map;

public class StockExchange {
    private Map<String, OrderBook> orderBooks = new HashMap<>();
    private MatchingStrategy matchingStrategy;

    public void addOrder(Order order) {
        orderBooks.computeIfAbsent(order.getSymbol(), k -> new OrderBook()).addOrder(order);
    }

    public void setMatchingStrategy(MatchingStrategy strategy) {
        this.matchingStrategy = strategy;
    }

    public void matchOrders() {
        for (OrderBook orderBook : orderBooks.values()) {
            orderBook.matchOrders(matchingStrategy);
        }
    }
}
