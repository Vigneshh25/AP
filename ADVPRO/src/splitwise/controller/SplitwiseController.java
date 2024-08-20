package splitwise.controller;// SplitwiseController.java

import splitwise.entities.EqualSplitExpense;
import splitwise.entities.Expense;
import splitwise.entities.Group;
import splitwise.entities.User;
import splitwise.service.ExpenseService;
import splitwise.service.GroupService;
import splitwise.service.UserService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class SplitwiseController {
    private final UserService userService;
    private final GroupService groupService;
    private final ExpenseService expenseService;

    public SplitwiseController(UserService userService, GroupService groupService, ExpenseService expenseService) {
        this.userService = userService;
        this.groupService = groupService;
        this.expenseService = expenseService;
    }

    public void createUser(User user) {
        userService.createUser(user);
    }

    public Group getGroupById(long groupId) {
        return groupService.getGroupById(groupId);
    }

    public void addExpense(Expense expense) {
        expenseService.addExpense(expense);
    }

    public List<Expense> getExpensesByGroupId(long groupId) {
        return expenseService.getExpensesByGroupId(groupId);
    }

    public void createGroup(Group group) {
        groupService.createGroup(group);
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
        Expense equalSplitExpense = new EqualSplitExpense(1, 1, user1, BigDecimal.valueOf(100), "Dinner", Arrays.asList(user1, user2));

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

