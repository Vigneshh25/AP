package stockexchange;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class StockExchange {
    private OrderBook orderBook;

    public StockExchange() {
        orderBook = new OrderBook();
    }

    public List<String> processOrder(Order order) {
        List<String> trades = new ArrayList<>();
        if (order.getType().equals("buy")) {
            trades.addAll(matchOrder(order, orderBook.getSellOrders(), orderBook.getBuyOrders()));
        } else {
            trades.addAll(matchOrder(order, orderBook.getBuyOrders(), orderBook.getSellOrders()));
        }
        return trades;
    }

    private List<String> matchOrder(Order incomingOrder, PriorityQueue<Order> oppositeOrders, PriorityQueue<Order> sameTypeOrders) {
        List<String> trades = new ArrayList<>();
        while (!oppositeOrders.isEmpty()) {
            Order oppositeOrder = oppositeOrders.peek();
            if ((incomingOrder.getType().equals("buy") && incomingOrder.getPrice() >= oppositeOrder.getPrice()) ||
                (incomingOrder.getType().equals("sell") && incomingOrder.getPrice() <= oppositeOrder.getPrice())) {
                int tradeQuantity = Math.min(incomingOrder.getQuantity(), oppositeOrder.getQuantity());
                trades.add(String.format("#%d %.2f %d #%d", incomingOrder.getId(), oppositeOrder.getPrice(), tradeQuantity, oppositeOrder.getId()));

                incomingOrder.setQuantity(incomingOrder.getQuantity() - tradeQuantity);
                oppositeOrder.setQuantity(oppositeOrder.getQuantity() - tradeQuantity);

                if (oppositeOrder.getQuantity() == 0) {
                    oppositeOrders.poll();
                }
                if (incomingOrder.getQuantity() == 0) {
                    break;
                }
            } else {
                break;
            }
        }
        if (incomingOrder.getQuantity() > 0) {
            sameTypeOrders.add(incomingOrder);
        }
        return trades;
    }
}
