package bikerental;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // Bike Class
public class Bike extends Product {
    private Size size;

    public Bike(String id, String name, Size size,double dailyRate) {
        super(id, name,dailyRate);
        this.size = size;
    }

    public Size getSize() {
        return size;
    }
}
