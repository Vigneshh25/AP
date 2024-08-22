package bikerental.factory;

import bikerental.model.Bike;
import bikerental.model.Size;

public class BikeFactory {
    public static Bike createBike(String id, String name, Size size, double reat) {
        return new Bike(id, name, size,reat);
    }
}
