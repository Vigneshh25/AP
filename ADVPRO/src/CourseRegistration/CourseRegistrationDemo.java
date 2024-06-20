package CourseRegistration;

public class CourseRegistrationDemo {
    public static void main(String[] args) {
        CourseRegistrationSystem system = CourseRegistrationSystem.getInstance();

        // Create admin and student observers
        Admin admin = new Admin("Admin1");
        StudentObserver studentObserver1 = new StudentObserver("S001");
        StudentObserver studentObserver2 = new StudentObserver("S002");

        // Register observers
        system.addObserver(admin);
        system.addObserver(studentObserver1);
        system.addObserver(studentObserver2);

        // Create courses
        Course course1 = new Course("CS101", "Introduction to Computer Science", "Dr. Smith", 2);
        Course course2 = new Course("MA101", "Calculus I", "Prof. Johnson", 1);
        system.addCourse(course1);
        system.addCourse(course2);

        // Create students
        Student student1 = new Student("S001", "Alice", "alice@example.com");
        Student student2 = new Student("S002", "Bob", "bob@example.com");
        Student student3 = new Student("S003", "Charlie", "charlie@example.com");
        system.addStudent(student1);
        system.addStudent(student2);
        system.addStudent(student3);

        // Search for courses
        System.out.println("Search for 'CS101': " + system.searchCourses("CS101"));
        System.out.println("Search for 'Calculus': " + system.searchCourses("Calculus"));

        // Register students for courses
        System.out.println("Registering Alice for CS101: " + system.registerCourse("S001", "CS101"));
        System.out.println("Registering Bob for CS101: " + system.registerCourse("S002", "CS101"));
        System.out.println("Registering Charlie for CS101 (should fail): " + system.registerCourse("S003", "CS101"));
        System.out.println("Registering Charlie for MA101: " + system.registerCourse("S003", "MA101"));
        System.out.println("Registering Alice for MA101 (should fail): " + system.registerCourse("S001", "MA101"));

        // Get registered courses for a student
        System.out.println("Alice's registered courses: " + student1.getRegisteredCourses());
        System.out.println("Bob's registered courses: " + student2.getRegisteredCourses());
        System.out.println("Charlie's registered courses: " + student3.getRegisteredCourses());

        // Unregister observers
        system.removeObserver(admin);
        system.removeObserver(studentObserver1);
        system.removeObserver(studentObserver2);
    }
}
