package EmplyoeeManagement.EmpManageMent;

import java.text.SimpleDateFormat;
import java.util.Date;

class EmployeeSalary {
    private int salaryId;
    private Employee employee;
    private double amount;
    private Date date;

    public EmployeeSalary(int salaryId, Employee employee, double amount, Date date) {
        this.salaryId = salaryId;
        this.employee = employee;
        this.amount = amount;
        this.date = date;
    }

    public int getSalaryId() {
        return salaryId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Salary Record #" + salaryId +
                " for " + employee.getName() +
                " on " + formatDate(date) +
                " - Amount: $" + amount;
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}