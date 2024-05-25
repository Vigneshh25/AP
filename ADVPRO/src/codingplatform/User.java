package codingplatform;

class User {
    private final String userName;
    private int score;

    public User(String userName) {
        this.userName = userName;
        this.score = 1500;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public void updateScore(int scoreDelta) {
        this.score += scoreDelta;
    }
}
