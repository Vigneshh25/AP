package splitwise.controller;// SplitwiseController.java
import splitwise.Repositories.UserRepository;
import splitwise.Repositories.*;
import splitwise.entities.EqualSplitExpense;
import splitwise.entities.Expense;
import splitwise.entities.Group;
import splitwise.entities.User;
import splitwise.service.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SplitwiseController {
    private UserService userService;
    private GroupService groupService;
    private ExpenseService expenseService;

    public SplitwiseController(UserService userService, GroupService groupService, ExpenseService expenseService) {
        this.userService = userService;
        this.groupService = groupService;
        this.expenseService = expenseService;
    }

    public void runDemo() {
        // Create users
        User user1 = new User(1, "Alice", "alice@example.com");
        User user2 = new User(2, "Bob", "bob@example.com");

        userService.createUser(user1);
        userService.createUser(user2);

        // Create a group
        Group group = new Group(1, "Friends");
        group.addMember(user1);
        group.addMember(user2);

        groupService.createGroup(group);

        // Create an expense (example with equal split)
        Expense equalSplitExpense = new EqualSplitExpense(1, 1, user1, BigDecimal.valueOf(100), "Dinner", Arrays.asList(user1, user2        ));

        expenseService.addExpense(equalSplitExpense);

        // Retrieve and print group details
        Group retrievedGroup = groupService.getGroupById(1);
        System.out.println("Group: " + retrievedGroup.getGroupName());
        System.out.println("Members:");
        for (User member : retrievedGroup.getMembers()) {
            System.out.println(member.getUsername());
        }

        // Retrieve and print expenses for the group
        System.out.println("Expenses:");
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
            System.out.println("-----------------------");
        }
    }
}

