package CourseRegistration;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CourseRegistrationSystem implements Observable {
    private static CourseRegistrationSystem instance;
    private ConcurrentHashMap<String, Course> courses;
    private ConcurrentHashMap<String, Student> students;
    private CopyOnWriteArrayList<Registration> registrations;
    private Lock registrationLock;
    private List<Observer> observers;

    private CourseRegistrationSystem() {
        this.courses = new ConcurrentHashMap<>();
        this.students = new ConcurrentHashMap<>();
        this.registrations = new CopyOnWriteArrayList<>();
        this.registrationLock = new ReentrantLock();
        this.observers = new ArrayList<>();
    }

    public static synchronized CourseRegistrationSystem getInstance() {
        if (instance == null) {
            instance = new CourseRegistrationSystem();
        }
        return instance;
    }

    public void addCourse(Course course) {
        courses.put(course.getCode(), course);
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public List<Course> searchCourses(String query) {
        List<Course> results = new ArrayList<>();
        for (Course course : courses.values()) {
            if (course.getCode().equalsIgnoreCase(query) || course.getName().equalsIgnoreCase(query)) {
                results.add(course);
            }
        }
        return results;
    }

    public boolean registerCourse(String studentId, String courseCode) {
        registrationLock.lock();
        try {
            Student student = students.get(studentId);
            Course course = courses.get(courseCode);

            if (student == null || course == null) {
                return false;
            }

            if (course.enrollStudent()) {
                student.registerCourse(course);
                registrations.add(new Registration(student, course));
                notifyObservers("Student " + student.getName() + " registered for course " + course.getName());
                return true;
            }
            return false;
        } finally {
            registrationLock.unlock();
        }
    }

    public List<Course> getRegisteredCourses(String studentId) {
        Student student = students.get(studentId);
        if (student != null) {
            return student.getRegisteredCourses();
        }
        return new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
