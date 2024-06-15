package atm.version2.utils;

import atm.version1.repository.Account;
import atm.version1.repository.AccountRepository;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean authenticate(String cardNumber, String pin) {
        Account account = accountRepository.findAccountByNumber(cardNumber);
        return account != null; // Assume Account class has validatePIN method
    }

    public double checkBalance(String cardNumber) {
        Account account = accountRepository.findAccountByNumber(cardNumber);
        return account.getBalance();
    }

    public void deposit(String cardNumber, double amount) {
        Account account = accountRepository.findAccountByNumber(cardNumber);
        account.deposit(amount);
        accountRepository.updateAccount(account);
    }

    public void withdraw(String cardNumber, double amount) {
        Account account = accountRepository.findAccountByNumber(cardNumber);
        account.withdraw(amount);
        accountRepository.updateAccount(account);
    }

    public void transfer(String fromCardNumber, String toCardNumber, double amount) {
        Account fromAccount = accountRepository.findAccountByNumber(fromCardNumber);
        Account toAccount = accountRepository.findAccountByNumber(toCardNumber);
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        accountRepository.updateAccount(fromAccount);
        accountRepository.updateAccount(toAccount);
    }
}
