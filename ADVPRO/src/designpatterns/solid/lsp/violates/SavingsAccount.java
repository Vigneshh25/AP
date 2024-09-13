package designpatterns.solid.lsp.violates;

// Subclass that violates LSP
public class SavingsAccount extends BankAccount {
    private double interestRate;
    private static final double MINIMUM_BALANCE = 100; // Minimum balance constraint

    public SavingsAccount(double initialBalance, double interestRate) {
        super(initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
       //violateion // Overrides withdraw to enforce minimum balance constraint
        if (amount > 0 && (balance - amount) >= MINIMUM_BALANCE) {
            super.withdraw(amount);
        }
    }

    @Override
    public void applyMonthlyInterest() {
        balance += balance * (interestRate / 100);
    }
}
