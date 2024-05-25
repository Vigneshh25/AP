package stockmatching;

public class Main {
    public static void main(String[] args) {
        StockExchange stockExchange = new StockExchange();

        // Set the matching strategy
        stockExchange.setMatchingStrategy(new SimpleMatchingStrategy());

        // Add some orders
        stockExchange.addOrder(new Order("AAPL", OrderType.BUY, 150.0, 10));
        stockExchange.addOrder(new Order("AAPL", OrderType.SELL, 145.0, 5));
        stockExchange.addOrder(new Order("AAPL", OrderType.SELL, 150.0, 10));
        stockExchange.addOrder(new Order("AAPL", OrderType.BUY, 155.0, 5));
        stockExchange.addOrder(new Order("AAPL", OrderType.SELL, 152.0, 5));
        
        // Match orders
        stockExchange.matchOrders();
    }
}
