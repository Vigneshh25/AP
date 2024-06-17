package transactionmanagement;

public class TransactionLogger {

    private static class innerClass
    {
        public static TransactionLogger TransactionLogger  = new TransactionLogger();
    }

    public static TransactionLogger getInstance()
    {
        return innerClass.TransactionLogger;
    }

    public void log(String message) {
        // For simplicity, just print the log message
        System.out.println("LOG: " + message);
    }
}
