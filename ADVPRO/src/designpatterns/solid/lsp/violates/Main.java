package designpatterns.solid.lsp.violates;

// Code that demonstrates the violation
public class Main {
    public static void main(String[] args) {
        BankAccount account = new SavingsAccount(500, 5);
        account.deposit(200);
        account.withdraw(600); // This will fail in SavingsAccount due to minimum balance constraint

        System.out.println("Balance after operations: " + account.getBalance());
    }
}
