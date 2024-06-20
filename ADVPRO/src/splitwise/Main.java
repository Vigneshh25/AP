package splitwise;

import splitwise.Repositories.*;
import splitwise.controller.SplitwiseController;
import splitwise.entities.*;
import splitwise.service.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Initialize repositories (Mock implementation for demo)
        UserRepository userRepository = new UserRepositoryImpl();
        GroupRepository groupRepository = new GroupRepositoryImpl();
        ExpenseRepository expenseRepository = new ExpenseRepositoryImpl();

        // Initialize services
        UserService userService = new UserService(userRepository);
        GroupService groupService = new GroupService(groupRepository);
        ExpenseService expenseService = new ExpenseService(expenseRepository);

        // Initialize controller
        SplitwiseController splitwiseController = new SplitwiseController(userService, groupService, expenseService);

        // Create users
        User user1 = new User(1, "Alice", "alice@example.com");
        User user2 = new User(2, "Bob", "bob@example.com");
        User user3 = new User(3, "Charlie", "charlie@example.com");

        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);

        // Create a group
        Group group = new Group(1, "Friends");
        group.addMember(user1);
        group.addMember(user2);
        group.addMember(user3);

        groupService.createGroup(group);

        // Create expenses
        Expense equalSplitExpense = new EqualSplitExpense(1, 1, user1, BigDecimal.valueOf(100), "Dinner", Arrays.asList(user1, user2, user3));
        Expense percentageSplitExpense = new PercentageSplitExpense(2, 1, user2, BigDecimal.valueOf(200), "Trip", Arrays.asList(user1, user2, user3));
        Expense exactAmountExpense = new ExactAmountExpense(3, 1, user3, BigDecimal.valueOf(150), "Movie", Arrays.asList(user1, user2, user3));

        // Add expenses
        expenseService.addExpense(equalSplitExpense);
        expenseService.addExpense(percentageSplitExpense);
        expenseService.addExpense(exactAmountExpense);

        // Display group details and expenses
        System.out.println("Group Details:");
        Group retrievedGroup = groupService.getGroupById(1);
        System.out.println("Group Name: " + retrievedGroup.getGroupName());
        System.out.println("Members:");
        for (User member : retrievedGroup.getMembers()) {
            System.out.println("- " + member.getUsername());
        }

        System.out.println("\nGroup Expenses:");
        List<Expense> groupExpenses = expenseService.getExpensesByGroupId(1);
        for (Expense expense : groupExpenses) {
            System.out.println("Expense ID: " + expense.getExpenseId());
            System.out.println("Paid by: " + expense.getPaidBy().getUsername());
            System.out.println("Amount: " + expense.getAmount());
            System.out.println("Description: " + expense.getDescription());
            System.out.println("Participants:");
            for (User participant : expense.getParticipants()) {
                System.out.println("- " + participant.getUsername());
            }
            System.out.println("Split Details:");
            expense.splitExpense(); // Display how the expense is split among participants
            System.out.println("-----------------------");
        }
    }
}
