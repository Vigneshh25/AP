package roomallotment.model;

import java.util.Date;

public class Exam {
    private int examId;
    private Subject subject;
    private Date examDate;
    private String timeSlot; // "am" or "pm"

    public Exam(int examId, Subject subject, Date examDate, String timeSlot) {
        this.examId = examId;
        this.subject = subject;
        this.examDate = examDate;
        this.timeSlot = timeSlot;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
    // Getters and setters
}
