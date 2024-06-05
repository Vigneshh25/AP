package assetmanagement;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        AssetManagementSystem system = new AssetManagementSystem();

        // Add vendors
        Vendor microsoft = new Vendor("Microsoft");
        Vendor adobe = new Vendor("Adobe");
        system.addVendor(microsoft);
        system.addVendor(adobe);

        // Add software
        Software windows = new Software("Windows", microsoft, 120.0);
        Software photoshop = new Software("Photoshop", adobe, 150.0);
        system.addSoftware(windows);
        system.addSoftware(photoshop);

        // Add employees
        Employee emp1 = new Employee("E001", "Alice");
        Employee emp2 = new Employee("E002", "Bob");
        system.addEmployee(emp1);
        system.addEmployee(emp2);

        // Add devices and install software
        Device device1 = new Device("D001", emp1);
        device1.installSoftware(new License(windows, LocalDate.of(2023, 1, 1), LocalDate.of(2024, 1, 1)));
        device1.installSoftware(new License(photoshop, LocalDate.of(2023, 1, 1), LocalDate.of(2024, 1, 1)));
        emp1.addDevice(device1);

        Device device2 = new Device("D002", emp2);
        device2.installSoftware(new License(windows, LocalDate.of(2023, 1, 1), LocalDate.of(2024, 1, 1)));
        emp2.addDevice(device2);

        // Generate reports
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 1);

        System.out.println("Number of installations of Windows: " + system.getNumberOfInstallations("Windows", startDate, endDate));
        System.out.println("Number of software installed in device D001: " + system.getNumberOfSoftwareInDevice("D001"));
        System.out.println("Number of software installed for employee E001: " + system.getNumberOfSoftwareForEmployee("E001"));
        System.out.println("Amount spent on Windows: " + system.getAmountSpentOnSoftware("Windows"));
        System.out.println("Amount spent on employee E001: " + system.getAmountSpentOnEmployee("E001"));
        System.out.println("Amount spent on vendor Microsoft: " + system.getAmountSpentOnVendor("Microsoft"));
        System.out.println("Number of installations from vendor Microsoft: " + system.getNumberOfInstallationsFromVendor("Microsoft"));
        System.out.println("Devices with expired software: " + system.getDevicesWithExpiredSoftware(LocalDate.now()));
    }
}
