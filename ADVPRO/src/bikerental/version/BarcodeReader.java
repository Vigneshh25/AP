package bikerental.version;

/**
 * Created by Vignesh.V on 05/06/24.
 */ // Barcode Reader
class BarcodeReader {
    public static String readBarcode(Vehicle vehicle) {
        return vehicle.getBarcode();
    }
}
