package bikerental;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // Product Abstract Class
public abstract class Product {
    protected String id;
    protected String name;
    protected boolean available;

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
        this.available = true;
    }

    public void rent() {
        this.available = false;
    }

    public void returnProduct() {
        this.available = true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }
}
