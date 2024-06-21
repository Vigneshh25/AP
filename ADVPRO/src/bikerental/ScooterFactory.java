package bikerental;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // ScooterFactory Class
class ScooterFactory {
    public static Scooter createScooter(String id, String name, MotorType motorType) {
        return new Scooter(id, name, motorType);
    }
}
