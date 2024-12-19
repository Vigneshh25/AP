package Bikerental.factory;

import Bikerental.model.Bike;
import Bikerental.model.Size;

public class BikeFactory {
    public static Bike createBike(String id, String name, Size size, double reat) {
        return new Bike(id, name, size,reat);
    }
}
