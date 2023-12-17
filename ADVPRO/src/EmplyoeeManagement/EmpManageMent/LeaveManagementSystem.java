package EmplyoeeManagement.EmpManageMent;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LeaveManagementSystem {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void requestLeave(Employee employee, Date startDate, Date endDate) {
        executorService.execute(() -> {
            int leaveId = new Random().nextInt(1000);
            EmployeeLeave leaveRecord = new EmployeeLeave(leaveId, employee, startDate, endDate);
            Database.saveLeaveRecord(leaveRecord);
            System.out.println(employee.getName() + " requested leave: " + leaveRecord);
        });
    }

    public static void shutdown() {
        executorService.shutdown();
    }
}

