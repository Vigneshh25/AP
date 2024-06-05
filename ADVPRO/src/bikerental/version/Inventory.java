package bikerental.version;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Vignesh.V on 05/06/24.
 */ // Inventory (Singleton pattern)
class Inventory {
    private static Inventory instance;
    private Map<String, Vehicle> vehicles;

    private Inventory() {
        vehicles = new HashMap<>();
    }

    public static synchronized Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getBarcode(), vehicle);
    }

    public Vehicle getVehicleByBarcode(String barcode) {
        return vehicles.get(barcode);
    }

    public List<Vehicle> searchVehicles(String make, String model) {
        return vehicles.values().stream()
                .filter(vehicle -> vehicle.getBarcode().equalsIgnoreCase(make) && vehicle.getBarcode().equalsIgnoreCase(model))
                .collect(Collectors.toList());
    }
}
