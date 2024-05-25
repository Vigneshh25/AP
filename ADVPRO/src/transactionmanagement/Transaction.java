package transactionmanagement;

public class Transaction {
    private String transactionId;
    private double amount;
    private String description;
    private String status;

    public Transaction(String transactionId, double amount, String description, String status) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
