package quizmanagement;

import java.util.*;

interface AuthenticationService {
    User authenticate(String email, String password);
}

class AuthenticationServiceImpl implements AuthenticationService {
    private Map<String, User> users;

    public AuthenticationServiceImpl() {
        users = new HashMap<>();
        // Initialize with some users for testing
        users.put("alice@example.com", new Student("1", "Alice", "alice@example.com"));
        users.put("bob@example.com", new Student("2", "Bob", "bob@example.com"));
        users.put("john@example.com", new Instructor("3", "Prof. John", "john@example.com"));
    }

    @Override
    public User authenticate(String email, String password) {
        // Simplified authentication without password check
        return users.get(email);
    }
}

class QuizService {
    private Map<String, Quiz> quizzes = new HashMap<>();

    public void createQuiz(Quiz quiz) {
        quizzes.put(quiz.getQuizId(), quiz);
    }

    public void deleteQuiz(String quizId) {
        quizzes.remove(quizId);
    }

    public Quiz getQuiz(String quizId) {
        return quizzes.get(quizId);
    }

    public List<Quiz> getAllQuizzes() {
        return new ArrayList<>(quizzes.values());
    }
}

class LeaderboardService {
    public Map<String, Integer> getLeaderboard(Quiz quiz) {
        Map<String, Integer> leaderboard = new HashMap<>();
        for (Map.Entry<String, List<Answer>> entry : quiz.getStudentAnswers().entrySet()) {
            int score = calculateScore(quiz, entry.getValue());
            leaderboard.put(entry.getKey(), score);
        }
        return sortByScoreDescending(leaderboard);
    }

    int calculateScore(Quiz quiz, List<Answer> answers) {
        int score = 0;
        for (Answer answer : answers) {
            for (Question question : quiz.getQuestions()) {
                if (question.getId().equals(answer.getQuestionId()) && question.getCorrectAnswer().equals(answer.getAnswerText())) {
                    score++;
                }
            }
        }
        return score;
    }

    private Map<String, Integer> sortByScoreDescending(Map<String, Integer> leaderboard) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(leaderboard.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        Map<String, Integer> sortedLeaderboard = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedLeaderboard.put(entry.getKey(), entry.getValue());
        }
        return sortedLeaderboard;
    }
}

class AttemptService {
    private Map<String, List<QuizAttempt>> attempts = new HashMap<>();

    public void addAttempt(String studentId, QuizAttempt attempt) {
        attempts.computeIfAbsent(studentId, k -> new ArrayList<>()).add(attempt);
    }

    public List<QuizAttempt> getAttempts(String studentId) {
        return attempts.getOrDefault(studentId, new ArrayList<>());
    }
}

class QuizAttempt {
    private String quizId;
    private int score;

    public QuizAttempt(String quizId, int score) {
        this.quizId = quizId;
        this.score = score;
    }

    public String getQuizId() {
        return quizId;
    }

    public int getScore() {
        return score;
    }
}
