package EmplyoeeManagement.EmpManageMent;

import java.text.SimpleDateFormat;
import java.util.Date;

class EmployeeLeave {
    private int leaveId;
    private Employee employee;
    private Date startDate;
    private Date endDate;

    public EmployeeLeave(int leaveId, Employee employee, Date startDate, Date endDate) {
        this.leaveId = leaveId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "Leave Request #" + leaveId +
                " for " + employee.getName() +
                " from " + formatDate(startDate) +
                " to " + formatDate(endDate);
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}