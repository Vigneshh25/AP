package codingplatform;

import java.util.*;

class Contest {
    private static int idCounter = 0;
    private final int id;
    private final String name;
    private final DifficultyLevel difficultyLevel;
    private final User creator;
    private final Set<User> participants;
    private final List<Question> questions;
    private final Map<User, List<Integer>> userScores;

    public Contest(String name, DifficultyLevel difficultyLevel, User creator, List<Question> questions) {
        this.id = ++idCounter;
        this.name = name;
        this.difficultyLevel = difficultyLevel;
        this.creator = creator;
        this.participants = new HashSet<>();
        this.questions = questions;
        this.userScores = new HashMap<>();
        this.participants.add(creator);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public User getCreator() {
        return creator;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void addParticipant(User user) {
        participants.add(user);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addUserScore(User user, int score, List<Integer> solvedQuestions) {
        userScores.put(user, solvedQuestions);
        int scoreDelta = calculateScore(score);
        user.updateScore(scoreDelta);
    }

    private int calculateScore(int score) {
        switch (difficultyLevel) {
            case LOW:
                return score - 50;
            case MEDIUM:
                return score - 30;
            case HIGH:
                return score;
            default:
                return 0;
        }
    }

    public Map<User, List<Integer>> getUserScores() {
        return userScores;
    }
}
