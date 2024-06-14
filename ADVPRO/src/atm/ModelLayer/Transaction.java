package atm.ModelLayer;

public class Transaction {
    private String transactionId;
    private String accountNumber;
    private double amount;
    private TransactionType type;

    public Transaction(String transactionId, String accountNumber, double amount, TransactionType type) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
    }

    // Getters and Setters
}
