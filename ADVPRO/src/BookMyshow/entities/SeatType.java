package BookMyshow.entities;

public class SeatType {
    private String type;
    private double price;

    public SeatType(String type, double price) {
        this.type = type;
        this.price = price;
    }

    // Getters and setters
    public double getPrice() {
        return price;
    }
}
