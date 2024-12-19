package Codingplatform;

import java.util.*;

class QuestionManager {
    private final Map<Integer, Question> questions;

    public QuestionManager() {
        this.questions = new HashMap<>();
    }

    public void createQuestion(DifficultyLevel difficultyLevel, int score) {
        Question question = new Question(difficultyLevel, score);
        questions.put(question.getId(), question);
    }

    public List<Question> getQuestionsByDifficulty(DifficultyLevel difficultyLevel) {
        List<Question> result = new ArrayList<>();
        for (Question question : questions.values()) {
            if (difficultyLevel == null || question.getDifficultyLevel() == difficultyLevel) {
                result.add(question);
            }
        }
        return result;
    }

    public void listQuestions(DifficultyLevel difficultyLevel) {
        for (Question question : questions.values()) {
            if (difficultyLevel == null || question.getDifficultyLevel() == difficultyLevel) {
                System.out.println("Question ID: " + question.getId() + ", Difficulty: " + question.getDifficultyLevel() + ", Score: " + question.getScore());
            }
        }
    }

    public Question getQuestionById(int questionId) {
        return questions.get(questionId);
    }
}
