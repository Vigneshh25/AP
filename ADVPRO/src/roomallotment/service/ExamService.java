package roomallotment.service;


import roomallotment.model.Exam;

import java.util.HashMap;
import java.util.Map;

public class ExamService {
    private Map<Integer, Exam> exams = new HashMap<>();

    public void addExam(Exam exam) {
        exams.put(exam.getExamId(), exam);
    }

    public Map<Integer, Exam> getExams() {
        return exams;
    }
}
