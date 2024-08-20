package splitwise;

import splitwise.controller.SplitwiseController;
import splitwise.entities.Expense;
import splitwise.entities.Group;
import splitwise.entities.User;
import splitwise.factory.ExpenseFactory;
import splitwise.factory.GroupFactory;
import splitwise.factory.UserFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationBootstrapper {

    private final SplitwiseController splitwiseController;

    public ApplicationBootstrapper(SplitwiseController splitwiseController) {
        this.splitwiseController = splitwiseController;
    }

    public void bootstrap() {
        // Create users
        User user1 = UserFactory.createUser(1, "Alice", "alice@example.com");
        User user2 = UserFactory.createUser(2, "Bob", "bob@example.com");
        User user3 = UserFactory.createUser(3, "Charlie", "charlie@example.com");

        splitwiseController.createUser(user1);
        splitwiseController.createUser(user2);
        splitwiseController.createUser(user3);

        // Create group
        Group group = GroupFactory.createGroup(1, "Friends", Arrays.asList(user1, user2, user3));
        splitwiseController.createGroup(group);

        // Define expenses
        Map<User, BigDecimal> percentages = new HashMap<>();
        percentages.put(user1, BigDecimal.valueOf(40));
        percentages.put(user2, BigDecimal.valueOf(30));
        percentages.put(user3, BigDecimal.valueOf(30));


        Map<User, BigDecimal> exactAmounts = new HashMap<>();
        exactAmounts.put(user1, BigDecimal.valueOf(100));
        exactAmounts.put(user2, BigDecimal.valueOf(100));
        exactAmounts.put(user3, BigDecimal.valueOf(30));


        List<Expense> expenses = Arrays.asList(
                ExpenseFactory.createEqualSplitExpense(1, group.getGroupId(), user1, BigDecimal.valueOf(100), "Dinner", group.getMembers()),
                ExpenseFactory.createPercentageSplitExpense(2, group.getGroupId(), user2, BigDecimal.valueOf(200), "Trip", group.getMembers(), percentages),
                ExpenseFactory.createExactAmountExpense(3, group.getGroupId(), user3, BigDecimal.valueOf(230), "Movie", group.getMembers(), exactAmounts)
        );

        // Add expenses to the group
        expenses.forEach(splitwiseController::addExpense);

        // Display group and expense details
        displayGroupDetails(group.getGroupId());
        displayExpensesDetails(group.getGroupId());
    }

    private void displayGroupDetails(long groupId) {
        Group group = splitwiseController.getGroupById(groupId);
        System.out.println("Group Name: " + group.getGroupName());
        System.out.println("Members:");
        group.getMembers().forEach(member -> System.out.println("- " + member.getUsername()));
    }

    private void displayExpensesDetails(long groupId) {
        List<Expense> expenses = splitwiseController.getExpensesByGroupId(groupId);
        System.out.println("\nGroup Expenses:");
        expenses.forEach(expense -> {
            System.out.println("Expense ID: " + expense.getExpenseId());
            System.out.println("Paid by: " + expense.getPaidBy().getUsername());
            System.out.println("Amount: " + expense.getAmount());
            System.out.println("Description: " + expense.getDescription());
            System.out.println("Participants:");
            expense.getParticipants().forEach(participant -> System.out.println("- " + participant.getUsername()));
            System.out.println("Split Details:");
            expense.splitExpense();
            System.out.println("-----------------------");
        });
    }
}
