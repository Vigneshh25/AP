package transactionmanagement;

import java.util.*;

public class TransactionRepository {
    private Map<String, Transaction> transactionMap = new HashMap<>();
    public static TransactionRepository instance ;

    private TransactionRepository() {
    }

    public static synchronized TransactionRepository getInstance()
    {
        if(instance == null)
            instance = new TransactionRepository();
        return instance;
    }

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
