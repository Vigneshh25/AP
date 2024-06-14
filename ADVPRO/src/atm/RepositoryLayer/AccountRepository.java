package atm.RepositoryLayer;

import atm.ModelLayer.Account;

public interface AccountRepository {
    Account findAccountByNumber(String accountNumber);
    void updateAccount(Account account);
}
