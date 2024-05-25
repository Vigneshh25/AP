package bikerental;

abstract class Product {
    String id;
    String name;
    boolean available;

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
