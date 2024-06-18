package Ecommerce.version;// Order.java
import java.util.ArrayList;
import java.util.List;

public class Order {
    private User user;
    private List<Product> products;
    private double totalAmount;

    public Order(User user) {
        this.user = user;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        totalAmount += product.getPrice();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        totalAmount -= product.getPrice();
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Getters and Setters
}
