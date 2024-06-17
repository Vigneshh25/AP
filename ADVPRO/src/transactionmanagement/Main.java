package transactionmanagement;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TransactionManager transactionManager = new TransactionManager();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create Transaction");
            System.out.println("2. Get Transaction by ID");
            System.out.println("3. Get All Transactions");
            System.out.println("4. Process Transactions");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();  // consume newline
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    String transactionId = transactionManager.createTransaction(amount, description);
                    System.out.println("Transaction created with ID: " + transactionId);
                    break;
                case 2:
                    System.out.print("Enter transaction ID: ");
                    String id = scanner.nextLine();
                    Transaction transaction = transactionManager.getTransaction(id);
                    if (transaction != null) {
                        System.out.println("Transaction ID: " + transaction.getTransactionId());
                        System.out.println("Amount: " + transaction.getAmount());
                        System.out.println("Description: " + transaction.getDescription());
                        System.out.println("Processed: " + transaction.getStatus());
                    } else {
                        System.out.println("Transaction not found.");
                    }
                    break;
                case 3:
                    System.out.println("All Transactions:");
                    for (Transaction t : transactionManager.getAllTransactions()) {
                        System.out.println("ID: " + t.getTransactionId() + ", Amount: " + t.getAmount() + ", Description: " + t.getDescription() + ", Processed: " + t.getStatus());
                    }
                    break;
                case 4:
                    transactionManager.processTransactions();
                    System.out.println("Transactions processed.");
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


