package splitwise.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

// ExactAmountExpense.java
public class ExactAmountExpense extends Expense {
    private final Map<User, BigDecimal> exactAmounts;

    public ExactAmountExpense(long expenseId, long groupId, User paidBy, BigDecimal amount, String description, List<User> participants, Map<User, BigDecimal> exactAmounts) {
        super(expenseId, groupId, paidBy, amount, description, participants);
        this.exactAmounts = exactAmounts;
        validateExactAmounts();
    }

    private void validateExactAmounts() {
        BigDecimal totalExactAmount = BigDecimal.ZERO;
        for (BigDecimal exactAmount : exactAmounts.values()) {
            totalExactAmount = totalExactAmount.add(exactAmount);
        }

        if (totalExactAmount.compareTo(getAmount()) != 0) {
            throw new IllegalArgumentException("The sum of exact amounts does not match the total expense amount.");
        }
    }

    @Override
    public void splitExpense() {
        for (User participant : getParticipants()) {
            BigDecimal participantAmount = exactAmounts.get(participant);
            System.out.println(participant.getUsername() + " owes " + participantAmount + " in exact amount split.");
        }
    }
}
