package splitwise.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

// EqualSplitExpense.java
public class EqualSplitExpense extends Expense {
    public EqualSplitExpense(long expenseId, long groupId, User paidBy, BigDecimal amount, String description, List<User> participants) {
        super(expenseId, groupId, paidBy, amount, description, participants);
    }

    @Override
    public void splitExpense() {
        BigDecimal totalParticipants = BigDecimal.valueOf(getParticipants().size());
        BigDecimal share = getAmount().divide(totalParticipants, 2, RoundingMode.HALF_EVEN); // Scale of 2 and rounding mode

        for (User participant : getParticipants()) {
            // Logic to handle each participant's share
            System.out.println(participant.getUsername() + " shares " + share + " in equal split");
        }
    }

}
