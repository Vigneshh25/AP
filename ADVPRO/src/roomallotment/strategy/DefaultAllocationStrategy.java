package roomallotment.strategy;


import roomallotment.model.Exam;
import roomallotment.model.Hall;
import roomallotment.model.Reservation;
import roomallotment.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultAllocationStrategy implements AllocationStrategy {
    @Override
    public List<Reservation> allocateSeats(Exam exam, List<Student> students, Map<Integer, Hall> halls) {
        List<Reservation> reservations = new ArrayList<>();

        List<Student> remainingStudents = new ArrayList<>(students); // Create a copy of students list

        for (Hall hall : halls.values()) {
            String timeSlotKey = exam.getExamDate().toString() + "-" + exam.getTimeSlot();
            int currentSlot = 1;

            while (currentSlot <= 2 * hall.getTotalSlots() && !remainingStudents.isEmpty()) {
                int rowNumber = (currentSlot - 1) / hall.getBenchesPerRow() + 1;
                int benchNumber = (currentSlot - 1) % hall.getBenchesPerRow() + 1;

                if (hall.isSlotAvailable(timeSlotKey, currentSlot) && !isBenchOccupied(hall, timeSlotKey, rowNumber, benchNumber, exam.getSubject().getSubjectId(), reservations)) {

                    Student student = remainingStudents.remove(0); // Remove the first student from the remaining students list
                    Reservation reservation = new Reservation(reservations.size() + 1, hall, student, currentSlot, rowNumber, benchNumber, exam);
                    reservations.add(reservation);

                    hall.reserveSlot(timeSlotKey, currentSlot);
                }

                currentSlot++;
            }
        }

        return reservations;
    }

    private boolean isBenchOccupied(Hall hall, String timeSlotKey, int rowNumber, int benchNumber, int subjectId, List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            if (reservation.getHall().equals(hall) && reservation.getRowNumber() == rowNumber && reservation.getBenchNumber() == benchNumber && reservation.getExam().getSubject().getSubjectId() == subjectId) {
                return true;
            }
        }
        return false;
    }
}

