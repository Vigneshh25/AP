package splitwise.Repositories;

import splitwise.entities.Expense;

import java.util.List;

// ExpenseRepository.java
public interface ExpenseRepository {
    void save(Expense expense);
    Expense findById(long expenseId);
    void delete(Expense expense);
    List<Expense> findByGroupId(long groupId);
}
