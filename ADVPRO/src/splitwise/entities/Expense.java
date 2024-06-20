package splitwise.entities;

import java.math.BigDecimal;
import java.util.List;

public abstract class Expense {
    private long expenseId;
    private long groupId;
    private User paidBy;
    private BigDecimal amount;
    private String description;
    private List<User> participants;

    public Expense(long expenseId, long groupId, User paidBy, BigDecimal amount, String description, List<User> participants) {
        this.expenseId = expenseId;
        this.groupId = groupId;
        this.paidBy = paidBy;
        this.amount = amount;
        this.description = description;
        this.participants = participants;
    }

    public abstract void splitExpense();

    // getters and setters
    public long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(long expenseId) {
        this.expenseId = expenseId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
