package assetmanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignesh.V on 05/06/24.
 */
class Employee {
    private String employeeId;
    private String name;
    private List<Device> devices;

    public Employee(String employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
        this.devices = new ArrayList<>();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public List<Device> getDevices() {
        return devices;
    }
}
