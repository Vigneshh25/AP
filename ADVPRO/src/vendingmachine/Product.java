package vendingmachine;

public class Product {
    private final String productCode;
    private final String name;
    private final int price;
    private int quantity;

    public Product(String productCode, String name, int price, int quantity) {
        this.productCode = productCode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }
}
