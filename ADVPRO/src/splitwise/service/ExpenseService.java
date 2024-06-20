package splitwise.service;

import splitwise.Repositories.ExpenseRepository;
import splitwise.Repositories.ExpenseRepositoryImpl;
import splitwise.entities.Expense;

import java.util.List;

// ExpenseService.java
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService() {
        expenseRepository = new ExpenseRepositoryImpl();
    }

    public void addExpense(Expense expense) {
        expense.splitExpense();
        expenseRepository.save(expense);
    }

    public Expense getExpenseById(long expenseId) {
        return expenseRepository.findById(expenseId);
    }

    public List<Expense> getExpensesByGroupId(long groupId) {
        return expenseRepository.findByGroupId(groupId);
    }

    public void deleteExpense(Expense expense) {
        expenseRepository.delete(expense);
    }
}
