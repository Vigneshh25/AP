package lld.crypto;

class Order {
    private String orderId;
    private User user;
    private String tradingPair;
    private String type;
    private double price;
    private double quantity;

    public Order(String orderId, User user, String tradingPair, String type, double price, double quantity) {
        this.orderId = orderId;
        this.user = user;
        this.tradingPair = tradingPair;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public String getTradingPair() {
        return tradingPair;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }
}
