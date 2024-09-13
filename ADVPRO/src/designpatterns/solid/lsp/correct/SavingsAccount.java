package designpatterns.solid.lsp.correct;

// SavingsAccount with interest
public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(double initialBalance, double interestRate) {
        super(initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void applyMonthlyInterest() {
        balance += balance * (interestRate / 100);
    }
}
