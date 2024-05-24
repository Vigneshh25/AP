package ParkingLot.Parking;


import java.util.HashMap;
import java.util.Map;

public class CarRegistrationModule {
    private final Map<String, Vehicle> registeredCars;

    public CarRegistrationModule() {
        this.registeredCars = new HashMap<>();
    }

    public void registerCar(String registrationNumber, String color, VehicleType type) {
        Vehicle car = new Vehicle(registrationNumber, color, type);
        registeredCars.put(registrationNumber, car);
    }

    public Vehicle getCar(String registrationNumber) {
        return registeredCars.get(registrationNumber);
    }
}
