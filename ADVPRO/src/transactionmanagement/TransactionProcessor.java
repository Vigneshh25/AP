package transactionmanagement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionProcessor {
    private TransactionRepository transactionRepository;
    private TransactionLogger transactionLogger;
    private ExecutorService executorService;

    public TransactionProcessor(TransactionRepository transactionRepository, TransactionLogger transactionLogger) {
        this.transactionRepository = transactionRepository;
        this.transactionLogger = transactionLogger;
        this.executorService = Executors.newFixedThreadPool(10); // For asynchronous processing
    }

    public void processTransactions() {
        List<Transaction> transactions = transactionRepository.getAllTransactions();
        for (Transaction transaction : transactions) {
            if ("PENDING".equals(transaction.getStatus())) {
                executorService.submit(() -> processTransaction(transaction));
            }
        }
    }

    private void processTransaction(Transaction transaction) {
        try {
            // Simulate transaction processing
            Thread.sleep(1000);
            transaction.setStatus("COMPLETED");
            transactionRepository.updateTransaction(transaction);
            transactionLogger.log("Transaction " + transaction.getTransactionId() + " processed successfully.");
        } catch (InterruptedException e) {
            transactionLogger.log("Transaction " + transaction.getTransactionId() + " processing failed.");
        }
    }
}
