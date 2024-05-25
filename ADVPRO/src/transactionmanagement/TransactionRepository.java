package transactionmanagement;

import java.util.*;

public class TransactionRepository {
    private Map<String, Transaction> transactionMap = new HashMap<>();

    public void addTransaction(Transaction transaction) {
        transactionMap.put(transaction.getTransactionId(), transaction);
    }

    public Transaction getTransaction(String transactionId) {
        return transactionMap.get(transactionId);
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactionMap.values());
    }

    public void updateTransaction(Transaction transaction) {
        transactionMap.put(transaction.getTransactionId(), transaction);
    }
}
