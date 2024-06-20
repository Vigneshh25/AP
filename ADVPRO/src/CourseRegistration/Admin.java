package CourseRegistration;

public class Admin implements Observer {
    private String name;

    public Admin(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("Admin " + name + " received update: " + message);
    }
}

class StudentObserver implements Observer {
    private String studentId;

    public StudentObserver(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public void update(String message) {
        System.out.println("Student " + studentId + " received update: " + message);
    }
}
