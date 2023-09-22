package BankingApplication;

public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, double interestRate, String pin) {
        super(accountNumber, balance, "Savings", pin);
        this.interestRate = interestRate;
    }


    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void addInterest(double rate) {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
        super.addInterest(rate);
    }

}