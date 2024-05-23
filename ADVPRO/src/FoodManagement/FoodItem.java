package FoodManagement;

public class FoodItem {
    private final String name;
    private final double price;
    private int quantity;
    private final int preparationTime; // in seconds

    public FoodItem(String name, double price, int quantity, int preparationTime) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.preparationTime = preparationTime;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void reduceQuantity(int quantity) {
        if (this.quantity >= quantity) {
            this.quantity -= quantity;
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPreparationTime() {
        return preparationTime;
    }
}
