package splitwise.entities;

import java.math.BigDecimal;
import java.util.List;

// PercentageSplitExpense.java
public class PercentageSplitExpense extends Expense {
    public PercentageSplitExpense(long expenseId, long groupId, User paidBy, BigDecimal amount, String description, List<User> participants) {
        super(expenseId, groupId, paidBy, amount, description, participants);
    }

    @Override
    public void splitExpense() {
        BigDecimal totalPercentage = BigDecimal.ZERO;
        for (User participant : getParticipants()) {
            // Example: Assume participants have set their percentages in their profiles
            // Simulating percentages here, adjust as per real logic
            BigDecimal participantPercentage = BigDecimal.valueOf(25); // Example percentage
            totalPercentage = totalPercentage.add(participantPercentage);
        }

        BigDecimal totalAmount = getAmount();
        for (User participant : getParticipants()) {
            // Calculate each participant's share based on their percentage
            BigDecimal share = totalAmount.multiply(BigDecimal.valueOf(0.01)); // 0.01 represents percentage
            System.out.println(participant.getUsername() + " shares " + share + " in percentage split");
        }
    }
}
