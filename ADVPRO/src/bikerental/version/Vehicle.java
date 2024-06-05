package bikerental.version;

/**
 * Created by Vignesh.V on 05/06/24.
 */ // Vehicle and its concrete classes
abstract class Vehicle {
    private String barcode;
    private String licensePlate;
    private String make;
    private String model;
    private int year;
    private String parkingStallNumber;

    public Vehicle(String barcode, String licensePlate, String make, String model, int year, String parkingStallNumber) {
        this.barcode = barcode;
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.year = year;
        this.parkingStallNumber = parkingStallNumber;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getParkingStallNumber() {
        return parkingStallNumber;
    }

    // Additional getters as needed
}
