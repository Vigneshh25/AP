package bikerental.factory;

import bikerental.model.MotorType;
import bikerental.model.Scooter;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // ScooterFactory Class
public class ScooterFactory {
    public static Scooter createScooter(String id, String name, MotorType motorType, double rate) {
        return new Scooter(id, name, motorType,rate);
    }
}
