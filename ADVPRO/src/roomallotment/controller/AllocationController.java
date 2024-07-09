package roomallotment.controller;


import roomallotment.model.*;
import roomallotment.service.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AllocationController {
    private AllocationService allocationService;
    private ExamService examService;
    private HallService hallService;

    public AllocationController(AllocationService allocationService, ExamService examService, HallService hallService) {
        this.allocationService = allocationService;
        this.examService = examService;
        this.hallService = hallService;
    }

    public void setup() {
        // Add halls
        allocationService.addHall(new Hall(1, "Hall A", 10, 4, 1));
        allocationService.addHall(new Hall(2, "Hall B", 10, 4, 1));
        allocationService.addHall(new Hall(3, "Hall C", 10, 4, 1));
        allocationService.addHall(new Hall(4, "Hall D", 10, 4, 1));
        allocationService.addHall(new Hall(5, "Hall E", 10, 4, 1));

        // Add exams
        try {
            Date examDate1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-01");
            allocationService.addExam(new Exam(1, new Subject(1, "CS101"), examDate1, "am"));
            allocationService.addExam(new Exam(2, new Subject(2, "CS102"), examDate1, "am"));
            allocationService.addExam(new Exam(3, new Subject(3, "EE101"), examDate1, "pm"));
            allocationService.addExam(new Exam(4, new Subject(4, "EE102"), examDate1, "pm"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Read students from file
        allocationService.readStudentsFromFile("/home/admin/a/vicky/AP/ADVPRO/src/roomallotment/students.txt");
    }

    public void allocateSeats() {
        allocationService.allocateSeats();
    }

    public void printReservations() {
        allocationService.printReservations();
    }
}
