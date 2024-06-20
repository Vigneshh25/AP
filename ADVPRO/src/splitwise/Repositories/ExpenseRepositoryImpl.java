package splitwise.Repositories;

import splitwise.entities.Expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseRepositoryImpl implements ExpenseRepository {
    private Map<Long, Expense> expenseDatabase = new HashMap<>();

    @Override
    public void save(Expense expense) {
        expenseDatabase.put(expense.getExpenseId(), expense);
    }

    @Override
    public Expense findById(long expenseId) {
        return expenseDatabase.get(expenseId);
    }

    @Override
    public void delete(Expense expense) {
        expenseDatabase.remove(expense.getExpenseId());
    }

    @Override
    public List<Expense> findByGroupId(long groupId) {
        List<Expense> groupExpenses = new ArrayList<>();
        for (Expense expense : expenseDatabase.values()) {
            if (expense.getGroupId() == groupId) {
                groupExpenses.add(expense);
            }
        }
        return groupExpenses;
    }
}
