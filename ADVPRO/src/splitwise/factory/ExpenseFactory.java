package splitwise.factory;

import splitwise.entities.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ExpenseFactory {

    public static Expense createEqualSplitExpense(long id, long groupId, User paidBy, BigDecimal amount, String description, List<User> participants) {
        return new EqualSplitExpense(id, groupId, paidBy, amount, description, participants);
    }

    public static Expense createPercentageSplitExpense(long id, long groupId, User paidBy, BigDecimal amount, String description, List<User> participants, Map<User, BigDecimal> percentages) {
        return new PercentageSplitExpense(id, groupId, paidBy, amount, description, participants, percentages);
    }

    public static Expense createExactAmountExpense(long id, long groupId, User paidBy, BigDecimal amount, String description, List<User> participants, Map<User, BigDecimal> exactAmount) {
        return new ExactAmountExpense(id, groupId, paidBy, amount, description, participants, exactAmount);
    }
}
