package stockmatching;

import java.util.Iterator;
import java.util.List;

public class SimpleMatchingStrategy implements MatchingStrategy {
    @Override
    public void match(OrderBook orderBook) {
        List<Order> buyOrders = orderBook.getBuyOrders();
        List<Order> sellOrders = orderBook.getSellOrders();

        buyOrders.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));
        sellOrders.sort((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));

        Iterator<Order> buyIterator = buyOrders.iterator();
        while (buyIterator.hasNext()) {
            Order buyOrder = buyIterator.next();
            Iterator<Order> sellIterator = sellOrders.iterator();

            while (sellIterator.hasNext()) {
                Order sellOrder = sellIterator.next();
                if (buyOrder.getPrice() >= sellOrder.getPrice()) {
                    int matchedQuantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());

                    System.out.println("Matched: " + matchedQuantity + " shares of " + buyOrder.getSymbol() + 
                            " at price " + sellOrder.getPrice());

                    buyOrder = new Order(buyOrder.getSymbol(), buyOrder.getType(), buyOrder.getPrice(), buyOrder.getQuantity() - matchedQuantity);
                    sellOrder = new Order(sellOrder.getSymbol(), sellOrder.getType(), sellOrder.getPrice(), sellOrder.getQuantity() - matchedQuantity);

                    if (buyOrder.getQuantity() == 0) {
                        buyIterator.remove();
                        break;
                    } else {
                        buyOrders.set(buyOrders.indexOf(buyOrder), buyOrder);
                    }

                    if (sellOrder.getQuantity() == 0) {
                        sellIterator.remove();
                    } else {
                        sellOrders.set(sellOrders.indexOf(sellOrder), sellOrder);
                    }
                }
            }
        }
    }
}
