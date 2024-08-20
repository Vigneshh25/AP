package splitwise;

import splitwise.controller.SplitwiseController;
import splitwise.service.ExpenseService;
import splitwise.service.GroupService;
import splitwise.service.UserService;

public class Main {

    public static void main(String[] args) {

        // Initialize Services
        UserService userService = new UserService();
        GroupService groupService = new GroupService();
        ExpenseService expenseService = new ExpenseService();

        // Initialize Controller
        SplitwiseController splitwiseController = new SplitwiseController(userService, groupService, expenseService);

        // Bootstrap application
        ApplicationBootstrapper bootstrapper = new ApplicationBootstrapper(splitwiseController);
        bootstrapper.bootstrap();
    }
}

