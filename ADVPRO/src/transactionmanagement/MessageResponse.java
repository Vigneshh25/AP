package transactionmanagement;

class MessageResponse {
    private String message;
    private String transactionId;

    public MessageResponse(String message, String transactionId) {
        this.message = message;
        this.transactionId = transactionId;
    }

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
