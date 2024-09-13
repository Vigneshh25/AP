package designpatterns.solid.lsp.correct;

/*
that objects of a superclass should be able to be replaced with objects of a subclass without affecting
the correctness of the program

Can you provide an example of LSP violation and its consequences in a real-world scenario?

Suppose we have a base class Vehicle with a method startEngine().A derived class ElectricVehicle
violates LSP by not implementing this method,leading to runtime errors when trying to start
the engine of an electric vehicle.

What are the common signs of a violation of LSP?

Common signs of a violation include derived classes overriding methods in ways that are inconsistent
with the base class, throwing unexpected exceptions, or requiring additional conditions not specified
in the base class.
*/
public class Main {
    public static void main(String[] args) {
        BankAccount basicAccount = new BankAccount(500);
        basicAccount.deposit(200);
        basicAccount.withdraw(100);
        System.out.println("Basic Account balance: " + basicAccount.getBalance());

        BankAccount minBalanceAccount = new MinimumBalanceAccount(500);
        minBalanceAccount.deposit(200);
        minBalanceAccount.withdraw(600); // Withdraw should be checked with minimum balance
        System.out.println("Minimum Balance Account balance: " + minBalanceAccount.getBalance());

        BankAccount savingsAccount = new SavingsAccount(500, 5);
        savingsAccount.deposit(200);
        savingsAccount.withdraw(100);
        savingsAccount.applyMonthlyInterest();
        System.out.println("Savings Account balance: " + savingsAccount.getBalance());
    }
}
