package Codingplatform;

class Question {
    private static int idCounter = 0;
    private final int id;
    private final DifficultyLevel difficultyLevel;
    private final int score;

    public Question(DifficultyLevel difficultyLevel, int score) {
        this.id = ++idCounter;
        this.difficultyLevel = difficultyLevel;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public int getScore() {
        return score;
    }
}
