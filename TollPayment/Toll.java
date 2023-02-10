import java.util.ArrayList;
import java.util.HashMap;

class Toll {
    private String name;
    private HashMap<String, Integer> chargingScheme;
    private ArrayList<Vehicle> vehiclesPassed;

    public Toll(String name) {
        this.name = name;
        this.chargingScheme = new HashMap<String, Integer>();
        this.vehiclesPassed = new ArrayList<Vehicle>();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Integer> getChargingScheme() {
        return chargingScheme;
    }

    public ArrayList<Vehicle> getVehiclesPassed() {
        return vehiclesPassed;
    }

    public void addChargingScheme(String vehicleType, int amount) {
        this.chargingScheme.put(vehicleType, amount);
    }

    public void addVehiclePassed(Vehicle vehicle) {
        this.vehiclesPassed.add(vehicle);
    }
}
