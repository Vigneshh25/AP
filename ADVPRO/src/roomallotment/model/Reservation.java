package roomallotment.model;

public class Reservation {
    private int reservationId;
    private Hall hall;
    private Student student;
    private int slotNumber;
    private int rowNumber;
    private int benchNumber;
    private Exam exam;

    public Reservation(int reservationId, Hall hall, Student student, int slotNumber, int rowNumber, int benchNumber, Exam exam) {
        this.reservationId = reservationId;
        this.hall = hall;
        this.student = student;
        this.slotNumber = slotNumber;
        this.rowNumber = rowNumber;
        this.benchNumber = benchNumber;
        this.exam = exam;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getBenchNumber() {
        return benchNumber;
    }

    public void setBenchNumber(int benchNumber) {
        this.benchNumber = benchNumber;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
// Getters and setters
}
