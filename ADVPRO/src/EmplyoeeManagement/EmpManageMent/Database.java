package EmplyoeeManagement.EmpManageMent;

import java.util.*;

class Database {
    private static Map<Integer, Employee> employees = new HashMap<>();
    private static Map<Integer, EmployeeLeave> leaveRecords = new HashMap<>();
    private static Map<Integer, EmployeeSalary> salaryRecords = new HashMap<>();

    static {
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

    public static Employee getEmployeeById(int employeeId) {
        return employees.get(employeeId);
    }

    public static void saveLeaveRecord(EmployeeLeave leaveRecord) {
        leaveRecords.put(leaveRecord.getLeaveId(), leaveRecord);
    }

    public static List<EmployeeLeave> getLeaveRecordsForEmployee(Employee employee) {
        List<EmployeeLeave> result = new ArrayList<>();
        for (EmployeeLeave leave : leaveRecords.values()) {
            if (leave.getEmployee().equals(employee)) {
                result.add(leave);
            }
        }
        return result;
    }

    public static void saveSalaryRecord(EmployeeSalary salaryRecord) {
        salaryRecords.put(salaryRecord.getSalaryId(), salaryRecord);
    }

    public static List<EmployeeSalary> getSalaryRecordsForEmployee(Employee employee) {
        List<EmployeeSalary> result = new ArrayList<>();
        for (EmployeeSalary salary : salaryRecords.values()) {
            if (salary.getEmployee().equals(employee)) {
                result.add(salary);
            }
        }
        return result;
    }
}