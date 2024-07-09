import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Department {
    private int departmentId;
    private String departmentName;

    public Department(int departmentId) {
        this.departmentId = departmentId;
    }

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
// Constructors, getters, and setters
}

class Student {
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
// Constructors, getters, and setters
}

class Subject {
    private int subjectId;
    private String subjectName;

    public Subject(int subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
// Constructors, getters, and setters
}


class Hall {
    private int hallId;
    private String hallName;
    private int totalSlots;
    private int rows;
    private int benchesPerRow;
    private Map<String, boolean[]> slotAvailability; // Key: "date-am/pm", Value: Array of slots

    public Hall(int hallId, String hallName, int totalSlots, int rows, int benchesPerRow) {
        this.hallId = hallId;
        this.hallName = hallName;
        this.totalSlots = totalSlots;
        this.rows = rows;
        this.benchesPerRow = benchesPerRow;
        this.slotAvailability = new HashMap<>();
    }

    public boolean isSlotAvailable(String timeSlot, int slotNumber) {
        return !slotAvailability.containsKey(timeSlot) || !slotAvailability.get(timeSlot)[slotNumber - 1];
    }

    public void reserveSlot(String timeSlot, int slotNumber) {
        slotAvailability.putIfAbsent(timeSlot, new boolean[totalSlots]);
        slotAvailability.get(timeSlot)[slotNumber - 1] = true;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getBenchesPerRow() {
        return benchesPerRow;
    }

    public void setBenchesPerRow(int benchesPerRow) {
        this.benchesPerRow = benchesPerRow;
    }

    public Map<String, boolean[]> getSlotAvailability() {
        return slotAvailability;
    }

    public void setSlotAvailability(Map<String, boolean[]> slotAvailability) {
        this.slotAvailability = slotAvailability;
    }
// Getters and setters
}

class Exam {
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

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
// Constructors, getters, and setters
}

class Reservation {
    private int reservationId;
    private Hall hall;
    private Student student;
    private int slotNumber;
    private int rowNumber;
    private int benchNumber;
    private Exam exam;

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
// Constructors, getters, and setters
}


class AllocationService {
    private final Map<Integer, Student> students = new HashMap<>();
    private final Map<Integer, Hall> halls = new HashMap<>();
    private final Map<Integer, Exam> exams = new HashMap<>();
    private final List<Reservation> reservations = new ArrayList<>();

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
                if (student.getSubject().getSubjectId() == exam.getSubject().getSubjectId()) {
                    examStudents.add(student);
                }
            }
            allocateExamSeats(exam, examStudents);
        }
    }

    private void allocateExamSeats(Exam exam, List<Student> examStudents) {
        Collections.sort(examStudents, Comparator.comparingInt(Student::getStudentId)); // Sort students for consistent allocation
        int currentSlot = 1;

        for (Hall hall : halls.values()) {
            String timeSlotKey = exam.getExamDate().toString() + "-" + exam.getTimeSlot();
            currentSlot = getNextAvailableSlot(hall, timeSlotKey, currentSlot);

            for (Student student : examStudents) {
                if (currentSlot > hall.getTotalSlots()) {
                    break;
                }

                int rowNumber = (currentSlot - 1) / hall.getBenchesPerRow() + 1;
                int benchNumber = (currentSlot - 1) % hall.getBenchesPerRow() + 1;

                if (isBenchOccupied(hall, timeSlotKey, rowNumber, benchNumber, exam.getSubject().getSubjectId())) {
                    currentSlot++;
                    continue;
                }

                Reservation reservation = new Reservation();
                reservation.setHall(hall);
                reservation.setStudent(student);
                reservation.setSlotNumber(currentSlot);
                reservation.setRowNumber(rowNumber);
                reservation.setBenchNumber(benchNumber);
                reservation.setExam(exam);
                reservations.add(reservation);

                hall.reserveSlot(timeSlotKey, currentSlot);
                currentSlot++;
            }
        }
    }

    private int getNextAvailableSlot(Hall hall, String timeSlotKey, int startSlot) {
        for (int i = startSlot; i <= hall.getTotalSlots(); i++) {
            if (hall.isSlotAvailable(timeSlotKey, i)) {
                return i;
            }
        }
        return hall.getTotalSlots() + 1;
    }

    private boolean isBenchOccupied(Hall hall, String timeSlotKey, int rowNumber, int benchNumber, int subjectId) {
        for (Reservation reservation : reservations) {
            if (reservation.getHall().equals(hall) && reservation.getRowNumber() == rowNumber && reservation.getBenchNumber() == benchNumber && reservation.getExam().getSubject().getSubjectId() == subjectId) {
                return true;
            }
        }
        return false;
    }

    public void printReservations() {
        for (Reservation reservation : reservations) {
            System.out.println("Reservation: Hall " + reservation.getHall().getHallName() + ", Student " + reservation.getStudent().getName() + ", Slot " + reservation.getSlotNumber() + ", Exam " + reservation.getExam().getSubject().getSubjectName() + ", Date " + reservation.getExam().getExamDate() + ", Time " + reservation.getExam().getTimeSlot());
        }
    }
}

class ExamHallApplication {
    public static void main(String[] args) {
        AllocationService allocationService = new AllocationService();

        // Add departments
        Department csDept = new Department(1, "Computer Science");
        Department eeDept = new Department(2, "Electrical Engineering");

        // Add subjects
        Subject csSubject = new Subject(1, "CS101");
        Subject eeSubject = new Subject(2, "EE101");

        // Add students
        readStudentsFromFile("/home/admin/a/vicky/AP/ADVPRO/src/roomallotment/students.txt",allocationService);
        // Add halls
        allocationService.addHall(new Hall(1, "Hall A", 30, 15, 2));
        allocationService.addHall(new Hall(2, "Hall B",30, 15, 2));
        allocationService.addHall(new Hall(2, "Hall C", 30, 15, 2));

        // Add exams
        try {
            Date examDate1 = new SimpleDateFormat("yyyy-MM-dd").parse("2023-12-01");
            allocationService.addExam(new Exam(1, csSubject, examDate1, "am"));
            allocationService.addExam(new Exam(2, eeSubject, examDate1, "pm"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Allocate seats
        allocationService.allocateSeats();

        // Print reservations
        allocationService.printReservations();
    }
    public static void readStudentsFromFile(String filename, AllocationService allocationService) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                int studentId = Integer.parseInt(details[0]);
                String name = details[1];
                Student student = getStudent(details, studentId, name);
                allocationService.addStudent(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Student getStudent(String[] details, int studentId, String name) {
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
        return student;
    }
}
