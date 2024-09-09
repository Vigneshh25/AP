package quizmanagement;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Initialize services
        QuizService quizService = new QuizService();
        LeaderboardService leaderboardService = new LeaderboardService();
        AttemptService attemptService = new AttemptService();

        QuizController quizController = new QuizController(quizService, leaderboardService, attemptService);

        // Sample users
        Student student1 = new Student("1", "Alice", "alice@example.com");
        Student student2 = new Student("2", "Bob", "bob@example.com");
        Instructor instructor = new Instructor("3", "Prof. John", "john@example.com");

        // Create a quiz
        Quiz mathQuiz = new Quiz("quiz1", "Math Quiz");

        // Add questions to the quiz
        Question q1 = new Question("q1", "What is 2 + 2?", QuestionType.INTEGER, "4");
        Question q2 = new Question("q2", "Is the earth round?", QuestionType.TRUE_FALSE, "true");
        Question q3 = new Question("q3", "Which is a prime number?", QuestionType.MCQ, "3");

        mathQuiz.addQuestion(q1);
        mathQuiz.addQuestion(q2);
        mathQuiz.addQuestion(q3);

        // Instructor creates the quiz
        quizController.createQuiz(mathQuiz);

        // Students attempt the quiz
        List<Answer> answersForStudent1 = new ArrayList<>();
        answersForStudent1.add(new Answer("q1", "4")); // Correct
        answersForStudent1.add(new Answer("q2", "true")); // Correct
        answersForStudent1.add(new Answer("q3", "3")); // Correct

        quizController.submitAnswers(student1.getId(), "quiz1", answersForStudent1);

        List<Answer> answersForStudent2 = new ArrayList<>();
        answersForStudent2.add(new Answer("q1", "5")); // Incorrect
        answersForStudent2.add(new Answer("q2", "false")); // Incorrect
        answersForStudent2.add(new Answer("q3", "5")); // Incorrect

        quizController.submitAnswers(student2.getId(), "quiz1", answersForStudent2);

        // Show leaderboard for the quiz
        System.out.println("Leaderboard for Math Quiz:");
        Map<String, Integer> leaderboard = quizController.viewLeaderboard("quiz1");

        for (Map.Entry<String, Integer> entry : leaderboard.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + ", Score: " + entry.getValue());
        }

        // Show past attempts of a student
        System.out.println("\nStudent 1's past attempts:");
        List<QuizAttempt> attempts = attemptService.getAttempts(student1.getId());
        for (QuizAttempt attempt : attempts) {
            System.out.println("Quiz ID: " + attempt.getQuizId() + ", Score: " + attempt.getScore());
        }
    }
}
