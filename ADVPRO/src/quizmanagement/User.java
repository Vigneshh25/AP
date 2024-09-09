package quizmanagement;

import java.util.*;

abstract class User {
    private String id;
    private String name;
    private String email;
    private Role role;

    public User(String id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }
}

class Student extends User {
    public Student(String id, String name, String email) {
        super(id, name, email, Role.STUDENT);
    }
}

class Instructor extends User {
    public Instructor(String id, String name, String email) {
        super(id, name, email, Role.INSTRUCTOR);
    }
}

class Admin extends User {
    public Admin(String id, String name, String email) {
        super(id, name, email, Role.ADMIN);
    }
}

class Subject {
    private String subjectId;
    private String name;
    private List<Quiz> quizzes;

    public Subject(String subjectId, String name) {
        this.subjectId = subjectId;
        this.name = name;
        this.quizzes = new ArrayList<>();
    }

    public void addQuiz(Quiz quiz) {
        this.quizzes.add(quiz);
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }
}

class Question {
    private String id;
    private String text;
    private QuestionType type;
    private String correctAnswer;

    public Question(String id, String text, QuestionType type, String correctAnswer) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.correctAnswer = correctAnswer;
    }

    public String getId() {
        return id;
    }

    public QuestionType getType() {
        return type;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

class Answer {
    private String questionId;
    private String answerText;

    public Answer(String questionId, String answerText) {
        this.questionId = questionId;
        this.answerText = answerText;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getAnswerText() {
        return answerText;
    }
}

class Quiz {
    private String quizId;
    private String name;
    private List<Question> questions;
    private Map<String, List<Answer>> studentAnswers;

    public Quiz(String quizId, String name) {
        this.quizId = quizId;
        this.name = name;
        this.questions = new ArrayList<>();
        this.studentAnswers = new HashMap<>();
    }

    public String getQuizId() {
        return quizId;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addStudentAnswers(String studentId, List<Answer> answers) {
        studentAnswers.put(studentId, answers);
    }

    public Map<String, List<Answer>> getStudentAnswers() {
        return studentAnswers;
    }
}
