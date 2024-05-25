package stockexchange;

import java.time.LocalTime;

public class Order {
    private int id;
    private LocalTime time;
    private String stock;
    private String type; // "buy" or "sell"
    private double price;
    private int quantity;

    public Order(int id, LocalTime time, String stock, String type, double price, int quantity) {
        this.id = id;
        this.time = time;
        this.stock = stock;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getStock() {
        return stock;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
