package Codingplatform;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        QuestionManager questionManager = new QuestionManager();
        ContestManager contestManager = new ContestManager(questionManager);
        CommandProcessor commandProcessor = new CommandProcessor(userManager, questionManager, contestManager);

        commandProcessor.process("CreateUser Ross");
        commandProcessor.process("CreateUser Monica");
        commandProcessor.process("CreateUser Joey");
        commandProcessor.process("CreateUser Chandler");

        commandProcessor.process("CreateQuestion LOW 10");
        commandProcessor.process("CreateQuestion MEDIUM 20");
        commandProcessor.process("CreateQuestion HIGH 30");

        commandProcessor.process("ListQuestion");

        commandProcessor.process("CreateContest diwali_contest LOW Ross");
        commandProcessor.process("ListContest");

        commandProcessor.process("AttendContest 1 Monica");
        commandProcessor.process("AttendContest 1 Joey");

        commandProcessor.process("RunContest 1 Ross");
        commandProcessor.process("LeaderBoard desc");

        commandProcessor.process("ContestHistory 1");
    }
}
