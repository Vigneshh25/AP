package roomallotment.service;


import roomallotment.model.Exam;
import roomallotment.model.*;
import roomallotment.model.Student;
import roomallotment.strategy.AllocationStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Map;

public class AllocationService {
    private Map<Integer, Student> students = new HashMap<>();
    private Map<Integer, Hall> halls = new HashMap<>();
    private Map<Integer, Exam> exams = new HashMap<>();
    private List<Reservation> reservations = new ArrayList<>();
    private AllocationStrategy allocationStrategy;

    public AllocationService(AllocationStrategy allocationStrategy) {
        this.allocationStrategy = allocationStrategy;
    }

    public void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public void addHall(Hall hall) {
        halls.put(hall.getHallId(), hall);
    }

    public void addExam(Exam exam) {
        exams.put(exam.getExamId(), exam);
    }

    public void allocateSeats() {
        for (Exam exam : exams.values()) {
            List<Student> examStudents = new ArrayList<>();
            for (Student student : students.values()) {
                if (student.getSubject().getSubjectName().equals(exam.getSubject().getSubjectName())) {
                    examStudents.add(student);
                }
            }
            List<Reservation> examReservations = allocationStrategy.allocateSeats(exam, examStudents, halls);
            reservations.addAll(examReservations);
        }
    }

    public void printReservations() {
        for (Reservation reservation : reservations) {
            System.out.println("Reservation: Hall " + reservation.getHall().getHallName() +
                    ", Student " + reservation.getStudent().getName() +
                    ", Slot " + reservation.getSlotNumber() +
                    ", Exam " + reservation.getExam().getSubject().getSubjectName() +
                    ", Date " + reservation.getExam().getExamDate() +
                    ", Time " + reservation.getExam().getTimeSlot());
        }
    }

    public void readStudentsFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                int studentId = Integer.parseInt(details[0]);
                String name = details[1];
                int departmentId = Integer.parseInt(details[2]);
                String departmentName = details[3];
                Department department = new Department(departmentId, departmentName);
                int subjectId = Integer.parseInt(details[4]);
                String subjectName = details[5];
                Subject subject = new Subject(subjectId, subjectName);
                int year = Integer.parseInt(details[6]);
                boolean isOptional = Boolean.parseBoolean(details[7]);
                boolean specialAccommodation = Boolean.parseBoolean(details[8]);

                Student student = new Student(studentId, name, department, subject, year, isOptional, specialAccommodation);
                addStudent(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
