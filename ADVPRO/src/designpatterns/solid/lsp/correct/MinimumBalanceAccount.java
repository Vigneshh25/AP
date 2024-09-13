package designpatterns.solid.lsp.correct;

// New class for minimum balance behavior
public class MinimumBalanceAccount extends BankAccount {
    private static final double MINIMUM_BALANCE = 100;

    public MinimumBalanceAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance - amount) >= MINIMUM_BALANCE) {
            super.withdraw(amount);
        }
    }
}
