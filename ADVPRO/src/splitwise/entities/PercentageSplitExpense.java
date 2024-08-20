package splitwise.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

// PercentageSplitExpense.java
public class PercentageSplitExpense extends Expense {
    private final Map<User, BigDecimal> percentages;

    public PercentageSplitExpense(long expenseId, long groupId, User paidBy, BigDecimal amount, String description, List<User> participants, Map<User, BigDecimal> percentages) {
        super(expenseId, groupId, paidBy, amount, description, participants);
        this.percentages = percentages;
        validatePercentages();
    }

    private void validatePercentages() {
        BigDecimal totalPercentage = BigDecimal.ZERO;
        for (BigDecimal percentage : percentages.values()) {
            totalPercentage = totalPercentage.add(percentage);
        }

        // Ensure that total percentage equals 100%
        if (totalPercentage.compareTo(BigDecimal.valueOf(100)) != 0) {
            throw new IllegalArgumentException("The total percentage does not equal 100%.");
        }
    }

    @Override
    public void splitExpense() {
        BigDecimal totalAmount = getAmount();

        for (User participant : getParticipants()) {
            BigDecimal participantPercentage = percentages.get(participant);
            BigDecimal share = totalAmount.multiply(participantPercentage).divide(BigDecimal.valueOf(100));

            System.out.println(participant.getUsername() + " owes " + share + " based on a percentage of " + participantPercentage + "%.");
        }
    }
}
