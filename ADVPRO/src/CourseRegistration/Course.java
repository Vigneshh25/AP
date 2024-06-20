package CourseRegistration;

import java.util.concurrent.atomic.AtomicInteger;

public class Course {
    private String code;
    private String name;
    private String instructor;
    private int maxCapacity;
    private AtomicInteger enrolledStudents;

    public Course(String code, String name, String instructor, int maxCapacity) {
        this.code = code;
        this.name = name;
        this.instructor = instructor;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new AtomicInteger(0);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents.get();
    }

    public boolean enrollStudent() {
        if (enrolledStudents.get() < maxCapacity) {
            enrolledStudents.incrementAndGet();
            return true;
        }
        return false;
    }
}
