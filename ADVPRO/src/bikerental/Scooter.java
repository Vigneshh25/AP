package bikerental;

class Scooter extends Product {
    MotorType motorType;

    public Scooter(String id, String name, MotorType motorType) {
        super(id, name);
        this.motorType = motorType;
    }
}
