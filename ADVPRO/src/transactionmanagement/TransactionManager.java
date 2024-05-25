package transactionmanagement;

import java.util.List;
import java.util.UUID;

public class TransactionManager {
    private TransactionRepository transactionRepository;
    private TransactionLogger transactionLogger;
    private TransactionProcessor transactionProcessor;

    public TransactionManager(TransactionRepository transactionRepository, TransactionLogger transactionLogger, TransactionProcessor transactionProcessor) {
        this.transactionRepository = transactionRepository;
        this.transactionLogger = transactionLogger;
        this.transactionProcessor = transactionProcessor;
    }

    public String createTransaction(double amount, String description) {
        String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction(transactionId, amount, description, "PENDING");
        transactionRepository.addTransaction(transaction);
        transactionLogger.log("Transaction " + transactionId + " created.");
        return transactionId;
    }

    public Transaction getTransaction(String transactionId) {
        return transactionRepository.getTransaction(transactionId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.getAllTransactions();
    }

    public void processTransactions() {
        transactionProcessor.processTransactions();
    }
}
