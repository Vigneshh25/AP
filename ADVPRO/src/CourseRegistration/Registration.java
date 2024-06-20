package CourseRegistration;

import java.time.LocalDateTime;

public class Registration {
    private Student student;
    private Course course;
    private LocalDateTime timestamp;

    public Registration(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.timestamp = LocalDateTime.now();
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
