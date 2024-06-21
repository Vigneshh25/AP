package bikerental;

/**
 * Created by Vignesh.V on 21/06/24.
 */ // Scooter Class
public class Scooter extends Product {
    private MotorType motorType;

    public Scooter(String id, String name, MotorType motorType) {
        super(id, name);
        this.motorType = motorType;
    }

    public MotorType getMotorType() {
        return motorType;
    }
}
