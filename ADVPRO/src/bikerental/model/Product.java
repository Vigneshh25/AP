package bikerental.model;

public abstract class Product {
    public String id;
    public String name;
    public boolean available;

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
}
