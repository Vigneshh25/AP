package bikerental.version;

/**
 * Created by Vignesh.V on 05/06/24.
 */ // Vehicle Factory
class VehicleFactory {
    public static Vehicle createVehicle(String type, String barcode, String licensePlate, String make, String model, int year, String parkingStallNumber) {
        switch (type.toLowerCase()) {
            case "car":
                return new Car(barcode, licensePlate, make, model, year, parkingStallNumber);
            case "truck":
                return new Truck(barcode, licensePlate, make, model, year, parkingStallNumber);
            case "suv":
                return new SUV(barcode, licensePlate, make, model, year, parkingStallNumber);
            case "van":
                return new Van(barcode, licensePlate, make, model, year, parkingStallNumber);
            case "motorcycle":
                return new Motorcycle(barcode, licensePlate, make, model, year, parkingStallNumber);
            default:
                throw new IllegalArgumentException("Invalid vehicle type.");
        }
    }
}
