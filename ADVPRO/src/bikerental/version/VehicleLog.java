package bikerental.version;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignesh.V on 05/06/24.
 */ // Singleton Vehicle Log
class VehicleLog {
    private static VehicleLog instance;
    private List<String> logs;

    private VehicleLog() {
        logs = new ArrayList<>();
    }

    public static synchronized VehicleLog getInstance() {
        if (instance == null) {
            instance = new VehicleLog();
        }
        return instance;
    }

    public void log(String message) {
        logs.add(message);
    }

    public List<String> getLogs() {
        return logs;
    }
}
