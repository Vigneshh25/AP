package roomallotment;


import roomallotment.cofig.AppConfig;
import roomallotment.controller.AllocationController;
import roomallotment.service.AllocationService;
import roomallotment.service.ExamService;
import roomallotment.service.HallService;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        AllocationService allocationService = appConfig.allocationService();
        ExamService examService = appConfig.examService();
        HallService hallService = appConfig.hallService();

        AllocationController controller = new AllocationController(allocationService, examService, hallService);

        // Setup data
        controller.setup();

        // Allocate seats
        controller.allocateSeats();

        // Print reservations
        controller.printReservations();
    }
}
