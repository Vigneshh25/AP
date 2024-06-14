package atm.ServiceLayer;

import atm.RepositoryLayer.AccountRepository;
import atm.ModelLayer.Account;

public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public double checkBalance(String accountNumber) {
        Account account = accountRepository.findAccountByNumber(accountNumber);
        return account.getBalance();
    }

    public void deposit(String accountNumber, double amount) {
        Account account = accountRepository.findAccountByNumber(accountNumber);
        account.deposit(amount);
        accountRepository.updateAccount(account);
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = accountRepository.findAccountByNumber(accountNumber);
        account.withdraw(amount);
        accountRepository.updateAccount(account);
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
        Account sourceAccount = accountRepository.findAccountByNumber(fromAccount);
        Account targetAccount = accountRepository.findAccountByNumber(toAccount);
        sourceAccount.withdraw(amount);
        targetAccount.deposit(amount);
        accountRepository.updateAccount(sourceAccount);
        accountRepository.updateAccount(targetAccount);
    }
}
