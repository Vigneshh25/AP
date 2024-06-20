package splitwise.entities;

import java.math.BigDecimal;
import java.util.List;

// ExactAmountExpense.java
public class ExactAmountExpense extends Expense {
    public ExactAmountExpense(long expenseId, long groupId, User paidBy, BigDecimal amount, String description, List<User> participants) {
        super(expenseId, groupId, paidBy, amount, description, participants);
    }

    @Override
    public void splitExpense() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (User participant : getParticipants()) {
            // Example: Assume participants specify exact amounts they owe
            BigDecimal participantAmount = BigDecimal.valueOf(100); // Example amount
            totalAmount = totalAmount.add(participantAmount);
        }

        BigDecimal amountPerParticipant = getAmount().divide(BigDecimal.valueOf(getParticipants().size()));
        for (User participant : getParticipants()) {
            // Logic to handle each participant's specific amount
            System.out.println(participant.getUsername() + " shares " + amountPerParticipant + " in exact amount split");
        }
    }
}
