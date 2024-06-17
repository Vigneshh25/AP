package EmplyoeeManagement.EmpManageMent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.*;

public class InteractiveEmployeeManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<Integer, Employee> employees = new HashMap<>();


    public static void main(String[] args) {
        try {
            initializeEmployees();

            while (true) {
                displayMenu();
                int choice = getUserChoice();
                processUserChoice(choice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initializeEmployees() {
        // Initialize with some data
        Employee ceo = new Employee(1, "CEO", 120000.0);
        Employee manager1 = new Employee(2, "Manager1", 100000.0);
        Employee manager2 = new Employee(3, "Manager2", 100000.0);
        Employee employee1 = new Employee(4, "Employee1", 60000.0);
        Employee employee2 = new Employee(5, "Employee2", 60000.0);

        ceo.setManager(null);
        manager1.setManager(ceo);
        manager2.setManager(ceo);
        employee1.setManager(manager1);
        employee2.setManager(manager2);

        employees.put(1, ceo);
        employees.put(2, manager1);
        employees.put(3, manager2);
        employees.put(4, employee1);
        employees.put(5, employee2);
    }

    private static void displayMenu() {
        System.out.println("\nEmployee Management System Menu:");
        System.out.println("1. View Employee Hierarchy");
        System.out.println("2. Request Leave");
        System.out.println("3. View Leave Information");
        System.out.println("4. Calculate Bonus");
        System.out.println("5. View Salary Information");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input.txt. Please enter a number: ");
            scanner.next(); // consume the invalid input.txt
        }
        return scanner.nextInt();
    }

    private static void processUserChoice(int choice) {
        try {
            switch (choice) {
                case 1:
                    viewEmployeeHierarchy();
                    break;
                case 2:
                    requestLeave();
                    break;
                case 3:
                    viewLeaveInformation();
                    break;
                case 4:
                    calculateBonus();
                    break;
                case 5:
                    viewSalaryInformation();
                    break;
                case 6:
                    System.out.println("Exiting the Employee Management System. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void viewEmployeeHierarchy() {
        System.out.println("Employee Hierarchy:");
        Employee ceo = employees.get(1);
        displayEmployeeHierarchy(ceo, 0);
    }

    private static void displayEmployeeHierarchy(Employee employee, int level) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indentation.append("  ");
        }
        System.out.println(indentation + employee.getName());
        for (Employee directReport : getDirectReports(employee)) {
            displayEmployeeHierarchy(directReport, level + 1);
        }
    }

    private static void requestLeave() {
        System.out.print("Enter your employee ID: ");
        int employeeId = getUserChoice();

        Employee employee = Database.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found with ID: " + employeeId);
            return;
        }

        try {
            System.out.print("Enter start date (yyyy-MM-dd): ");
            Date startDate = parseDate(scanner.next());
            System.out.print("Enter end date (yyyy-MM-dd): ");
            Date endDate = parseDate(scanner.next());

            LeaveManagementSystem.requestLeave(employee, startDate, endDate);
            System.out.println("Leave request submitted successfully for " + employee.getName());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private static void viewLeaveInformation() {
        System.out.print("Enter your employee ID: ");
        int employeeId = getUserChoice();

        Employee employee = Database.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found with ID: " + employeeId);
            return;
        }

        List<EmployeeLeave> leaveRecords = Database.getLeaveRecordsForEmployee(employee);

        System.out.println("Leave Information for " + employee.getName() + ":");
        for (EmployeeLeave leave : leaveRecords) {
            System.out.println(leave);
        }
    }

    private static void calculateBonus() {
        System.out.print("Enter your employee ID: ");
        int employeeId = getUserChoice();

        Employee employee = Database.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found with ID: " + employeeId);
            return;
        }

        double bonus = BonusCalculator.calculateBonus(employee);
        System.out.println("Bonus for " + employee.getName() + ": $" + bonus);
    }

    private static void viewSalaryInformation() {
        System.out.print("Enter your employee ID: ");
        int employeeId = getUserChoice();

        Employee employee = Database.getEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found with ID: " + employeeId);
            return;
        }

        List<EmployeeSalary> salaryRecords = Database.getSalaryRecordsForEmployee(employee);

        System.out.println("Salary Information for " + employee.getName() + ":");
        for (EmployeeSalary salary : salaryRecords) {
            System.out.println(salary);
        }
    }

    private static List<Employee> getDirectReports(Employee manager) {
        List<Employee> directReports = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (employee.getManager() != null && employee.getManager().equals(manager)) {
                directReports.add(employee);
            }
        }
        return directReports;
    }

    private static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }
}