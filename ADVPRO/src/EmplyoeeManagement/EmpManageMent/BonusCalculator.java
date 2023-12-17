package EmplyoeeManagement.EmpManageMent;

class BonusCalculator {
    public static double calculateBonus(Employee employee) {
        double bonus = 0.0;

        // Example bonus calculation logic, you can customize this based on your requirements
        if (employee.getBasicSalary() > 100000) {
            bonus = employee.getBasicSalary() * 0.1;
        } else {
            bonus = employee.getBasicSalary() * 0.05;
        }

        return bonus;
    }
}