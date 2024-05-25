package stockmatching;

import java.util.Date;

public class Order {
    private String symbol;
    private OrderType type;
    private double price;
    private int quantity;
    private Date timestamp;

    public Order(String symbol, OrderType type, double price, int quantity) {
        this.symbol = symbol;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.timestamp = new Date();
    }

    public String getSymbol() {
        return symbol;
    }

    public OrderType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
