package bikerental;

class Bike extends Product {
    Size size;

    public Bike(String id, String name, Size size) {
        super(id, name);
        this.size = size;
    }
}
