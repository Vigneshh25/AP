package codingplatform;

import java.util.*;

class CommandProcessor {
    private final UserManager userManager;
    private final QuestionManager questionManager;
    private final ContestManager contestManager;

    public CommandProcessor(UserManager userManager, QuestionManager questionManager, ContestManager contestManager) {
        this.userManager = userManager;
        this.questionManager = questionManager;
        this.contestManager = contestManager;
    }

    public void process(String command) {
        String[] tokens = command.split(" ");
        String action = tokens[0];

        switch (action) {
            case "CreateUser":
                userManager.createUser(tokens[1]);
                break;
            case "CreateQuestion":
                questionManager.createQuestion(DifficultyLevel.valueOf(tokens[1]), Integer.parseInt(tokens[2]));
                break;
            case "ListQuestion":
                questionManager.listQuestions(tokens.length > 1 ? DifficultyLevel.valueOf(tokens[1]) : null);
                break;
            case "CreateContest":
                contestManager.createContest(tokens[1], DifficultyLevel.valueOf(tokens[2]), userManager.getUser(tokens[3]));
                break;
            case "ListContest":
                contestManager.listContests(tokens.length > 1 ? DifficultyLevel.valueOf(tokens[1]) : null);
                break;
            case "AttendContest":
                contestManager.attendContest(Integer.parseInt(tokens[1]), userManager.getUser(tokens[2]));
                break;
            case "RunContest":
                contestManager.runContest(Integer.parseInt(tokens[1]), userManager.getUser(tokens[2]));
                break;
            case "LeaderBoard":
                userManager.listUsers(SortOrder.valueOf(tokens[1].toUpperCase()));
                break;
            case "ContestHistory":
                contestManager.contestHistory(Integer.parseInt(tokens[1]));
                break;
            case "WithdrawContest":
                contestManager.withdrawContest(Integer.parseInt(tokens[1]), userManager.getUser(tokens[2]));
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }
    }
}
