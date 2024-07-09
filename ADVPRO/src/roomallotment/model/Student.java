package roomallotment.model;

public class Student {
    private int studentId;
    private String name;
    private Department department;
    private Subject subject;
    private int year;
    private boolean isOptional;
    private boolean specialAccommodation;

    public Student(int studentId, String name, Department department, Subject subject, int year, boolean isOptional, boolean specialAccommodation) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.subject = subject;
        this.year = year;
        this.isOptional = isOptional;
        this.specialAccommodation = specialAccommodation;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }

    public boolean isSpecialAccommodation() {
        return specialAccommodation;
    }

    public void setSpecialAccommodation(boolean specialAccommodation) {
        this.specialAccommodation = specialAccommodation;
    }

    // Getters and setters
}
