package quizmanagement;

import java.util.List;
import java.util.Map;

class QuizController {
    private QuizService quizService;
    private LeaderboardService leaderboardService;
    private AttemptService attemptService;

    public QuizController(QuizService quizService, LeaderboardService leaderboardService, AttemptService attemptService) {
        this.quizService = quizService;
        this.leaderboardService = leaderboardService;
        this.attemptService = attemptService;
    }

    public void createQuiz(Quiz quiz) {
        quizService.createQuiz(quiz);
    }

    public void deleteQuiz(String quizId) {
        quizService.deleteQuiz(quizId);
    }

    public Map<String, Integer> viewLeaderboard(String quizId) {
        Quiz quiz = quizService.getQuiz(quizId);
        return leaderboardService.getLeaderboard(quiz);
    }

    public void submitAnswers(String studentId, String quizId, List<Answer> answers) {
        Quiz quiz = quizService.getQuiz(quizId);
        quiz.addStudentAnswers(studentId, answers);
        int score = calculateScore(quiz, answers);
        attemptService.addAttempt(studentId, new QuizAttempt(quizId, score));
    }

    private int calculateScore(Quiz quiz, List<Answer> answers) {
        return leaderboardService.calculateScore(quiz, answers);
    }
}
