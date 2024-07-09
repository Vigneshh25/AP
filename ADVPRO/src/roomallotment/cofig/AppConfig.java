package roomallotment.cofig;


import roomallotment.strategy.AllocationStrategy;
import roomallotment.strategy.DefaultAllocationStrategy;
import roomallotment.service.*;

public class AppConfig {
    public AllocationService allocationService() {
        AllocationStrategy strategy = new DefaultAllocationStrategy();
        return new AllocationService(strategy);
    }

    public ExamService examService() {
        return new ExamService();
    }

    public HallService hallService() {
        return new HallService();
    }
}
