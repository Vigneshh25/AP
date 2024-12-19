package Codingplatform;

import java.util.*;

class ContestManager {
    private final Map<Integer, Contest> contests;
    private final QuestionManager questionManager;

    public ContestManager(QuestionManager questionManager) {
        this.contests = new HashMap<>();
        this.questionManager = questionManager;
    }

    public void createContest(String name, DifficultyLevel difficultyLevel, User creator) {
        List<Question> questions = questionManager.getQuestionsByDifficulty(difficultyLevel);
        Contest contest = new Contest(name, difficultyLevel, creator, questions);
        contests.put(contest.getId(), contest);
    }

    public void listContests(DifficultyLevel difficultyLevel) {
        for (Contest contest : contests.values()) {
            if (difficultyLevel == null || contest.getDifficultyLevel() == difficultyLevel) {
                System.out.println("Contest ID: " + contest.getId() + ", Name: " + contest.getName() + ", Level: " + contest.getDifficultyLevel());
            }
        }
    }

    public void attendContest(int contestId, User user) {
        Contest contest = contests.get(contestId);
        if (contest != null && !contest.getCreator().equals(user)) {
            contest.addParticipant(user);
        }
    }

    public void runContest(int contestId, User creator) {
        Contest contest = contests.get(contestId);
        if (contest != null && contest.getCreator().equals(creator)) {
            Random random = new Random();
            for (User user : contest.getParticipants()) {
                int totalScore = 0;
                List<Integer> solvedQuestions = new ArrayList<>();
                for (Question question : contest.getQuestions()) {
                    if (random.nextBoolean()) {
                        totalScore += question.getScore();
                        solvedQuestions.add(question.getId());
                    }
                }
                contest.addUserScore(user, totalScore, solvedQuestions);
            }
        }
    }

    public void contestHistory(int contestId) {
        Contest contest = contests.get(contestId);
        if (contest != null) {
            System.out.println("Contest History for: " + contest.getName());
            for (Map.Entry<User, List<Integer>> entry : contest.getUserScores().entrySet()) {
                User user = entry.getKey();
                List<Integer> questions = entry.getValue();
                int score = questions.stream().mapToInt(questionId -> questionManager.getQuestionById(questionId).getScore()).sum();
                System.out.println(user.getUserName() + ": " + score + " " + questions);
            }
        }
    }

    public void withdrawContest(int contestId, User user) {
        Contest contest = contests.get(contestId);
        if (contest != null && !contest.getCreator().equals(user)) {
            contest.getParticipants().remove(user);
        }
    }
}
