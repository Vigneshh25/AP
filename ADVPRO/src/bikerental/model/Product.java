package bikerental.model;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // Product Abstract Class
public abstract class Product {
    private String productId;
    private String name;
    private boolean available;
    private double dailyRate;

    public Product(String id, String name,double dailyRate) {
        this.productId = id;
        this.name = name;
        this.available = true;
        this.dailyRate = dailyRate;
    }

    public void rent() {
        this.available = false;
    }

    public void returnProduct() {
        this.available = true;
    }

    public String getId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }
}
