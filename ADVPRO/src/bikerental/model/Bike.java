package bikerental.model;


public class Bike extends Product {
    public Size size;

    public Bike(String id, String name, Size size) {
        super(id, name);
        this.size = size;
    }
}
