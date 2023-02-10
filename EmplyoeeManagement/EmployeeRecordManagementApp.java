package set1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Employee {
    private String empName;
    private int age;
    private String designation;
    private String department;
    private String reportingTo;

    public Employee(String empName, int age, String designation, String department, String reportingTo) {
        this.empName = empName;
        this.age = age;
        this.designation = designation;
        this.department = department;
        this.reportingTo = reportingTo;
    }

    public String getEmpName() {
        return empName;
    }

    public int getAge() {
        return age;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDepartment() {
        return department;
    }

    public String getReportingTo() {
        return reportingTo;
    }
}

public class EmployeeRecordManagementApp {
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Hardcoded Employee Records
        employees.add(new Employee("John Doe", 30, "Manager", "Sales", ""));
        employees.add(new Employee("JaneDoe", 25, "Associate", "Sales", "John Doe"));
        employees.add(new Employee("Bob Smith", 35, "Manager", "Marketing", ""));
        employees.add(new Employee("AliceJohnson", 28, "Associate", "Marketing", "Bob Smith"));
        employees.add(new Employee("CharlieBrown", 32, "Manager", "HR", ""));

        int choice = 0;
        while (choice != 6) {
            System.out.println("Employee Record Management App");
            System.out.println("1. Display all employee records");
            System.out.println("2. Search employee records");
            System.out.println("3. Print reporting tree of an employee");
            System.out.println("4. Print employees reporting to a manager");
            System.out.println("5. Print summary of departments, designations, and reporting");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayEmployeeRecords();
                    break;
                case 2:
                    searchEmployeeRecords();
                    break;
                case 3:
                    printReportingTree();
                    break;
                case 4:
                    printEmployeesByManager();
                    break;
                case 5:
                    printDepartmentSummary();
                    break;
                case 6:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void displayEmployeeRecords() {
        System.out.println("Employee Name\tAge\tDesignation\tDepartment\tReporting To");
        for (Employee employee : employees) {      System.out.println(employee.getEmpName() + "\t\t" + employee.getAge() + "\t" + employee.getDesignation() + "\t\t" + employee.getDepartment() + "\t\t" + employee.getReportingTo());
        }
    }

    private static void searchEmployeeRecords() {
        System.out.println("Search employee records");
        System.out.println("1. Search by employee name");
        System.out.println("2. Search by age");
        System.out.println("3. Search by designation");
        System.out.println("4. Search by department");
        System.out.println("5. Search by reporting to");
        System.out.print("Enter your choice: ");

        int searchChoice = scanner.nextInt();
        switch (searchChoice) {
            case 1:
                searchByEmployeeName();
                break;
            case 2:
                searchByAge();
                break;
            case 3:
                searchByDesignation();
                break;
            case 4:
                searchByDepartment();
                break;
            case 5:
                searchByReportingTo();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void searchByEmployeeName() {
        System.out.print("Enter employee name: ");
        String empName = scanner.next();
        System.out.println("Employee Name\tAge\tDesignation\tDepartment\tReporting To");
        for (Employee employee : employees) {
            if (employee.getEmpName().contains(empName)) {
                System.out.println(employee.getEmpName() + "\t\t" + employee.getAge() + "\t" + employee.getDesignation() + "\t\t" + employee.getDepartment() + "\t\t" + employee.getReportingTo());
            }
        }
    }

    private static void searchByAge() {
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        System.out.println("Employee Name\tAge\tDesignation\tDepartment\tReporting To");
        for (Employee employee : employees) {
            if (employee.getAge() == age) {
                System.out.println(employee.getEmpName() + "\t\t" + employee.getAge() + "\t" + employee.getDesignation() + "\t\t" + employee.getDepartment() + "\t\t" + employee.getReportingTo());
            }
        }
    }

    private static void searchByDesignation() {
        System.out.print("Enter designation: ");
        String designation = scanner.next();
        System.out.println("Employee Name\tAge\tDesignation\tDepartment\tReporting To");
        for (Employee employee : employees) {
            if (employee.getDesignation().contains(designation)) {
                System.out.println(employee.getEmpName() + "\t\t" + employee.getAge() + "\t" + employee.getDesignation() + "\t\t" +employee.getDepartment() + "\t\t" + employee.getReportingTo());
            }
        }
    }

    private static void searchByDepartment() {
        System.out.print("Enter department: ");
        String department = scanner.next();
        System.out.println("Employee Name\tAge\tDesignation\tDepartment\tReporting To");
        for (Employee employee : employees) {
            if (employee.getDepartment().contains(department)) {
                System.out.println(employee.getEmpName() + "\t\t" + employee.getAge() + "\t" + employee.getDesignation() + "\t\t" + employee.getDepartment() + "\t\t" + employee.getReportingTo());
            }
        }
    }

    private static void searchByReportingTo() {
        System.out.print("Enter reporting to: ");
        String reportingTo = scanner.next();
        System.out.println("Employee Name\tAge\tDesignation\tDepartment\tReporting To");
        for (Employee employee : employees) {
            if (employee.getReportingTo().contains(reportingTo)) {
                System.out.println(employee.getEmpName() + "\t\t" + employee.getAge() + "\t" + employee.getDesignation() + "\t\t" + employee.getDepartment() + "\t\t" + employee.getReportingTo());
            }
        }
    }

    private static void printReportingTree() {
        System.out.print("Enter employee name: ");
        String empName = scanner.next();
        Employee selectedEmployee = null;
        for (Employee employee : employees) {
            if (employee.getEmpName().equals(empName)) {
                selectedEmployee = employee;
                break;
            }
        }
        if (selectedEmployee == null) {
            System.out.println("Employee not found.");
        } else {
            System.out.println("Employee Name\tAge\tDesignation\tDepartment\tReporting To");
            System.out.println(selectedEmployee.getEmpName() + "\t\t" + selectedEmployee.getAge() + "\t" + selectedEmployee.getDesignation() + "\t\t" + selectedEmployee.getDepartment() + "\t\t" + selectedEmployee.getReportingTo());
            System.out.println();
            System.out.println("Employees reporting to " + empName);
            System.out.println("Employee Name\tAge\tDesignation\tDepartment\tReporting To");
            for (Employee employee : employees) {
                if (employee.getReportingTo().equals(empName)) {
                    System.out.println(employee.getEmpName() + "\t\t" + employee.getAge() + "\t" + employee.getDesignation() + "\t\t" + employee.getDepartment() + "\t\t" + employee.getReportingTo());
                }
            }
        }
    }

    private static void printEmployeesByManager() {
        System.out.print("Enter manager name: ");
        String managerName = scanner.next();
        System.out.println("Employees reporting to " + managerName);
        System.out.println("Employee Name\tAge\tDesignation\tDepartment\tReporting To");
        for (Employee employee : employees) {
            if (employee.getReportingTo().equals(managerName)) {
                System.out.println(employee.getEmpName() + "\t\t" + employee.getAge() + "\t" + employee.getDesignation() + "\t\t" + employee.getDepartment() + "\t\t" + employee.getReportingTo());
            }
        }
    }

    private static void printDepartmentSummary() {
        Map<String, Integer> departmentSummary = new HashMap<>();
        for (Employee employee : employees) {
            if (departmentSummary.containsKey(employee.getDepartment())) {
                departmentSummary.put(employee.getDepartment(), departmentSummary.get(employee.getDepartment()) + 1);
            } else {
                departmentSummary.put(employee.getDepartment(), 1);
            }
        }
        System.out.println("Department Summary");
        System.out.println("Department\tNumber of Employees");
        for (Map.Entry<String, Integer> entry : departmentSummary.entrySet()) {
            System.out.println(entry.getKey() + "\t\t" + entry.getValue());
        }
    }

    private static void printDesignationSummary() {
        Map<String, Integer> designationSummary = new HashMap<>();
        for (Employee employee : employees) {
            if (designationSummary.containsKey(employee.getDesignation())) {
                designationSummary.put(employee.getDesignation(), designationSummary.get(employee.getDesignation()) + 1);
            } else {
                designationSummary.put(employee.getDesignation(), 1);
            }
        }
        System.out.println("Designation Summary");
        System.out.println("Designation\tNumber of Employees");
        for (Map.Entry<String, Integer> entry : designationSummary.entrySet()) {
            System.out.println(entry.getKey() + "\t\t" + entry.getValue());
        }
    }

    private static void printManagerSummary() {
        Map<String, Integer> managerSummary = new HashMap<>();
        for (Employee employee : employees) {
            if (managerSummary.containsKey(employee.getReportingTo())) {
                managerSummary.put(employee.getReportingTo(), managerSummary.get(employee.getReportingTo()) + 1);
            } else {
                managerSummary.put(employee.getReportingTo(), 1);
            }
        }
        System.out.println("Manager Summary");
        System.out.println("Manager\tNumber of Employees Reporting");
        for (Map.Entry<String, Integer> entry : managerSummary.entrySet()) {
            System.out.println(entry.getKey() + "\t\t" + entry.getValue());
        }
    }
}


