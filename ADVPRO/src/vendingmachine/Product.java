package vendingmachine;

public abstract class Product {
    private final String productCode;
    private final ProductType name;
    private final int price;
    private int quantity;
    private int discount;

    public Product(String productCode, ProductType name, int price, int quantity) {
        this.productCode = productCode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = 0;  // Default discount is 0
    }

    public String getProductCode() {
        return productCode;
    }

    public ProductType getName() {
        return name;
    }

    public int getPrice() {
        return price - discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decrementQuantity(int quantity) {
        if (this.quantity - quantity >= 0) {
            this.quantity -= quantity;
        } else {
            System.out.println("Not enough stock to dispense.");
        }
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
}
