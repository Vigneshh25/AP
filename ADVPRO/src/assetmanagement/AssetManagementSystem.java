package assetmanagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vignesh.V on 05/06/24.
 */
class AssetManagementSystem {
    private List<Vendor> vendors;
    private List<Software> softwares;
    private List<Employee> employees;

    public AssetManagementSystem() {
        this.vendors = new ArrayList<>();
        this.softwares = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public void addVendor(Vendor vendor) {
        vendors.add(vendor);
    }

    public void addSoftware(Software software) {
        softwares.add(software);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Vendor findVendorByName(String name) {
        return vendors.stream()
                .filter(vendor -> vendor.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Software findSoftwareByName(String name) {
        return softwares.stream()
                .filter(software -> software.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Employee findEmployeeById(String employeeId) {
        return employees.stream()
                .filter(employee -> employee.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    public int getNumberOfInstallations(String softwareName, LocalDate startDate, LocalDate endDate) {
        return (int) employees.stream()
                .flatMap(employee -> employee.getDevices().stream())
                .flatMap(device -> device.getLicenses().stream())
                .filter(license -> license.getSoftware().getName().equals(softwareName) &&
                        !license.getInstallationDate().isBefore(startDate) &&
                        !license.getInstallationDate().isAfter(endDate))
                .count();
    }

    public int getNumberOfSoftwareInDevice(String deviceId) {
        for (Employee employee : employees) {
            for (Device device : employee.getDevices()) {
                if (device.getDeviceId().equals(deviceId)) {
                    return device.getLicenses().size();
                }
            }
        }
        return 0;
    }

    public int getNumberOfSoftwareForEmployee(String employeeId) {
        Employee employee = findEmployeeById(employeeId);
        if (employee != null) {
            return employee.getDevices().stream()
                    .flatMap(device -> device.getLicenses().stream())
                    .collect(Collectors.toSet())
                    .size();
        }
        return 0;
    }

    public double getAmountSpentOnSoftware(String softwareName) {
        return employees.stream()
                .flatMap(employee -> employee.getDevices().stream())
                .flatMap(device -> device.getLicenses().stream())
                .filter(license -> license.getSoftware().getName().equals(softwareName))
                .mapToDouble(license -> license.getSoftware().getPricePerLicense())
                .sum();
    }

    public double getAmountSpentOnEmployee(String employeeId) {
        Employee employee = findEmployeeById(employeeId);
        if (employee != null) {
            return employee.getDevices().stream()
                    .flatMap(device -> device.getLicenses().stream())
                    .mapToDouble(license -> license.getSoftware().getPricePerLicense())
                    .sum();
        }
        return 0;
    }

    public double getAmountSpentOnVendor(String vendorName) {
        Vendor vendor = findVendorByName(vendorName);
        if (vendor != null) {
            return employees.stream()
                    .flatMap(employee -> employee.getDevices().stream())
                    .flatMap(device -> device.getLicenses().stream())
                    .filter(license -> license.getSoftware().getVendor().equals(vendor))
                    .mapToDouble(license -> license.getSoftware().getPricePerLicense())
                    .sum();
        }
        return 0;
    }

    public int getNumberOfInstallationsFromVendor(String vendorName) {
        Vendor vendor = findVendorByName(vendorName);
        if (vendor != null) {
            return (int) employees.stream()
                    .flatMap(employee -> employee.getDevices().stream())
                    .flatMap(device -> device.getLicenses().stream())
                    .filter(license -> license.getSoftware().getVendor().equals(vendor))
                    .count();
        }
        return 0;
    }

    public List<String> getDevicesWithExpiredSoftware(LocalDate currentDate) {
        return employees.stream()
                .flatMap(employee -> employee.getDevices().stream())
                .filter(device -> device.getLicenses().stream().anyMatch(license -> license.isExpired(currentDate)))
                .map(Device::getDeviceId)
                .collect(Collectors.toList());
    }
}
