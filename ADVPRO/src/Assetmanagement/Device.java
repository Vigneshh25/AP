package Assetmanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignesh.V on 05/06/24.
 */
class Device {
    private String deviceId;
    private Employee owner;
    private List<License> licenses;

    public Device(String deviceId, Employee owner) {
        this.deviceId = deviceId;
        this.owner = owner;
        this.licenses = new ArrayList<>();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Employee getOwner() {
        return owner;
    }

    public void installSoftware(License license) {
        licenses.add(license);
    }

    public List<License> getLicenses() {
        return licenses;
    }
}
