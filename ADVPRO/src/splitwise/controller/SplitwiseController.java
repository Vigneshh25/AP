package splitwise.controller;// SplitwiseController.java

import splitwise.entities.Expense;
import splitwise.entities.Group;
import splitwise.entities.User;
import splitwise.service.ExpenseService;
import splitwise.service.GroupService;
import splitwise.service.UserService;

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
}

