package vendingmachine;

public abstract class Product {
    private final String productCode;
    private final ProductType name;
    private final int price;
    private int quantity;

    public Product(String productCode, ProductType name, int price, int quantity) {
        this.productCode = productCode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public ProductType getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decrementQuantity(int quantity) {
        this.quantity -= quantity;
    }
}
