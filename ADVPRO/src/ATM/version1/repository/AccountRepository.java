package ATM.version1.repository;

public interface AccountRepository {
    Account findAccountByNumber(String accountNumber);
    void updateAccount(Account account);
}
